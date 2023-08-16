package com.cmc.curtaincall.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CurtainCallInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}
