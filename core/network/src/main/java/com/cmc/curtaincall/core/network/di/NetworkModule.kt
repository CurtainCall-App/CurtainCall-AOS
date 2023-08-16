package com.cmc.curtaincall.core.network.di

import com.cmc.curtaincall.core.network.BuildConfig
import com.cmc.curtaincall.core.network.interceptors.CurtainCallInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = BuildConfig.CURTAIN_CALL_BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideClient(
        curtainCallInterceptor: CurtainCallInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(curtainCallInterceptor)
            .build()
}
