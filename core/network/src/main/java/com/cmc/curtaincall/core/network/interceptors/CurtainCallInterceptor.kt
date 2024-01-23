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

// private data class TokenInfo(
//    val accessToken: String,
//    val accessTokenExpiresAt: String,
//    val idToken: String
// )

class CurtainCallInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : Interceptor {

    @SuppressLint("SimpleDateFormat")
    override fun intercept(chain: Interceptor.Chain): Response {
        val (accessToken, accessTokenExpiresAt) = runBlocking {
            combine(
                tokenRepository.getAccessToken(),
                tokenRepository.getAccessTokenExpiresAt()
            ) { accessToken, accessTokenExpiresAt ->
                accessToken to accessTokenExpiresAt
            }.first()
        }
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(newRequest)
    }
}
