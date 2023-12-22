package com.cmc.curtaincall.core.network.interceptors

import android.annotation.SuppressLint
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private data class TokenInfo(
    val accessToken: String,
    val accessTokenExpiresAt: String,
    val idToken: String
)

class CurtainCallInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : Interceptor {

    @SuppressLint("SimpleDateFormat")
    override fun intercept(chain: Interceptor.Chain): Response {
        val tokenInfo = runBlocking {
            combine(
                tokenRepository.getAccessToken(),
                tokenRepository.getAccessTokenExpiresAt(),
                tokenRepository.getIdToken()
            ) { accessToken, accessTokenExpiresAt, idToken ->
                TokenInfo(
                    accessToken = accessToken,
                    accessTokenExpiresAt = accessTokenExpiresAt,
                    idToken = idToken
                )
            }.first()
        }
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${tokenInfo.accessToken.ifEmpty { tokenInfo.idToken }}")
            .build()
        return chain.proceed(newRequest)
    }
}
