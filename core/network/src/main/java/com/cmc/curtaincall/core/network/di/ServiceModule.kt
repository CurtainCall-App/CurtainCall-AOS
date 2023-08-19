package com.cmc.curtaincall.core.network.di

import com.cmc.curtaincall.core.network.service.auth.AuthService
import com.cmc.curtaincall.core.network.service.member.MemberService
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
        retrofit: Retrofit
    ): AuthService = retrofit.create()

    @Provides
    @Singleton
    fun provideMemberService(
        retrofit: Retrofit
    ): MemberService = retrofit.create()
}
