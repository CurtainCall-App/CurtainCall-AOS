package com.cmc.curtaincall.core.network.di

import com.cmc.curtaincall.core.network.qualifiers.LoggingRetrofit
import com.cmc.curtaincall.core.network.qualifiers.RefreshTokenRetrofit
import com.cmc.curtaincall.core.network.service.auth.AuthService
import com.cmc.curtaincall.core.network.service.favorite.FavoriteService
import com.cmc.curtaincall.core.network.service.image.ImageService
import com.cmc.curtaincall.core.network.service.lostitem.LostItemService
import com.cmc.curtaincall.core.network.service.member.MemberService
import com.cmc.curtaincall.core.network.service.notice.NoticeService
import com.cmc.curtaincall.core.network.service.party.PartyService
import com.cmc.curtaincall.core.network.service.report.ReportService
import com.cmc.curtaincall.core.network.service.review.ReviewService
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

    @Provides
    @Singleton
    fun provideReviewService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): ReviewService = retrofit.create()

    @Provides
    @Singleton
    fun provideFavoriteService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): FavoriteService = retrofit.create()

    @Provides
    @Singleton
    fun providePartyService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): PartyService = retrofit.create()

    @Provides
    @Singleton
    fun provideNoticeService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): NoticeService = retrofit.create()

    @Provides
    @Singleton
    fun provideReportService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): ReportService = retrofit.create()

    @Provides
    @Singleton
    fun provideLostItemService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): LostItemService = retrofit.create()

    @Provides
    @Singleton
    fun provideImageService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): ImageService = retrofit.create()
}
