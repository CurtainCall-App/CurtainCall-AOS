package com.cmc.curtaincall.core.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.cmc.curtaincall.core.local.qualifiers.LaunchDataStore
import com.cmc.curtaincall.core.local.qualifiers.MemberDataStore
import com.cmc.curtaincall.core.local.qualifiers.TokenDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val TOKEN_DATASTORE = "TOKEN_DATASTORE"
    private const val MEMBER_DATASTORE = "MEMBER_DATASTORE"
    private const val LAUNCH_DATASTORE = "LAUNCH_DATASTORE"

    @Provides
    @Singleton
    @TokenDataStore
    fun provideTokenDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(TOKEN_DATASTORE)
        }

    @Provides
    @Singleton
    @MemberDataStore
    fun provideMemberDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(MEMBER_DATASTORE)
        }

    @Provides
    @Singleton
    @LaunchDataStore
    fun provideLocalDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(LAUNCH_DATASTORE)
        }
}
