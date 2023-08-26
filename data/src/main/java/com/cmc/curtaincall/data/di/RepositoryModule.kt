package com.cmc.curtaincall.data.di

import com.cmc.curtaincall.data.repository.AuthRepositoryImpl
import com.cmc.curtaincall.data.repository.FavoriteRepositoryImpl
import com.cmc.curtaincall.data.repository.MemberRepositoryImpl
import com.cmc.curtaincall.data.repository.NoticeRepositoryImpl
import com.cmc.curtaincall.data.repository.PartyRepositoryImpl
import com.cmc.curtaincall.data.repository.ReportRepositoryImpl
import com.cmc.curtaincall.data.repository.ReviewRepositoryImpl
import com.cmc.curtaincall.data.repository.ShowRepositoryImpl
import com.cmc.curtaincall.data.repository.TokenRepositoryImpl
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import com.cmc.curtaincall.domain.repository.LostItemRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.NoticeRepository
import com.cmc.curtaincall.domain.repository.PartyRepository
import com.cmc.curtaincall.domain.repository.ReportRepository
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

    @Binds
    @Singleton
    fun bindFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository

    @Binds
    @Singleton
    fun bindPartyRepository(
        partyRepositoryImpl: PartyRepositoryImpl
    ): PartyRepository

    @Binds
    @Singleton
    fun bindNoticeRepository(
        noticeRepositoryImpl: NoticeRepositoryImpl
    ): NoticeRepository

    @Binds
    @Singleton
    fun bindReportRepository(
        reportRepositoryImpl: ReportRepositoryImpl
    ): ReportRepository

    @Binds
    @Singleton
    fun bindLostItemRepository(
        lostItemRepository: LostItemRepository
    ): LostItemRepository
}
