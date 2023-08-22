package com.cmc.curtaincall.core.network.di

import com.cmc.curtaincall.core.network.qualifiers.LoggingRetrofit
import com.cmc.curtaincall.core.network.qualifiers.RefreshTokenRetrofit
import com.cmc.curtaincall.core.network.service.auth.AuthService
import com.cmc.curtaincall.core.network.service.member.MemberService
import com.cmc.curtaincall.core.network.service.show.ShowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(
        @LoggingRetrofit retrofit: Retrofit
    ): AuthService = retrofit.create()

    @Provides
    @Singleton
    fun provideMemberService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): MemberService = retrofit.create()

    @Provides
    @Singleton
    fun provideShowService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): ShowService = retrofit.create()
}
