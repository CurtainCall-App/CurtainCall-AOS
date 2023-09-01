package com.cmc.curtaincall.core.network.interceptors

import android.annotation.SuppressLint
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

private data class TokenInfo(
    val accessToken: String,
    val accessTokenExpiresAt: String,
    val refreshToken: String,
    val refreshTokenExpiresAt: String
)

class CurtainCallInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : Interceptor {

    @SuppressLint("SimpleDateFormat")
    override fun intercept(chain: Interceptor.Chain): Response {
        var tokenInfo = runBlocking {
            combine(
                tokenRepository.getAccessToken(),
                tokenRepository.getAccessTokenExpiresAt(),
                tokenRepository.getRefreshToken(),
                tokenRepository.getRefreshTokenExpiresAt()
            ) { accessToken, accessTokenExpiresAt, refreshToken, refreshTokenExpiresAt ->
                TokenInfo(
                    accessToken = accessToken,
                    accessTokenExpiresAt = accessTokenExpiresAt,
                    refreshToken = refreshToken,
                    refreshTokenExpiresAt = refreshTokenExpiresAt
                )
            }.first()
        }

        val today = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().time)
        if (tokenInfo.accessTokenExpiresAt < today) {
            Timber.d("refreshToken start")
            try {
                if (tokenInfo.refreshTokenExpiresAt > today) {
                    tokenInfo = runBlocking {
                        val result = authRepository.requestReissue(tokenInfo.refreshToken).first()
                        tokenRepository.saveToken(result)
                        TokenInfo(
                            accessToken = result.accessToken,
                            accessTokenExpiresAt = result.accessTokenExpiresAt,
                            refreshToken = result.refreshToken,
                            refreshTokenExpiresAt = result.refreshTokenExpiresAt
                        )
                    }
                    Timber.d("refreshToken end accessToken: ${tokenInfo.accessToken} accessTokenExpiresAt: ${tokenInfo.accessTokenExpiresAt}")
                }
            } catch (e: Exception) {
                return chain.proceed(chain.request())
            }
        }
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${tokenInfo.accessToken}")
            .build()
        return chain.proceed(newRequest)
    }
}
