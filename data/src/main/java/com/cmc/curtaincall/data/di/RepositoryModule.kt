package com.cmc.curtaincall.data.di

import com.cmc.curtaincall.data.repository.AuthRepositoryImpl
import com.cmc.curtaincall.data.repository.MemberRepositoryImpl
import com.cmc.curtaincall.data.repository.ReviewRepositoryImpl
import com.cmc.curtaincall.data.repository.ShowRepositoryImpl
import com.cmc.curtaincall.data.repository.TokenRepositoryImpl
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ReviewRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    fun bindTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository

    @Binds
    @Singleton
    fun bindMemberRepository(
        memberRepositoryImpl: MemberRepositoryImpl
    ): MemberRepository

    @Binds
    @Singleton
    fun bindShowRepository(
        showRepositoryImpl: ShowRepositoryImpl
    ): ShowRepository

    @Binds
    @Singleton
    fun bindReviewRepository(
        reviewRepositoryImpl: ReviewRepositoryImpl
    ): ReviewRepository
}
