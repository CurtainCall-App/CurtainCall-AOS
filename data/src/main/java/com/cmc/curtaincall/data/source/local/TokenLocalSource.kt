package com.cmc.curtaincall.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.cmc.curtaincall.core.local.PreferenceKeys.ACCESS_TOKEN
import com.cmc.curtaincall.core.local.PreferenceKeys.ACCESS_TOKEN_EXPIRATION
import com.cmc.curtaincall.core.local.PreferenceKeys.REFRESH_TOKEN
import com.cmc.curtaincall.core.local.PreferenceKeys.REFRESH_TOKEN_EXPIRATION
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenLocalSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    fun getAccessToken(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN] ?: ""
        }

    fun getAccessTokenExpiration(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_EXPIRATION] ?: ""
        }

    fun getRefreshToken(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN] ?: ""
        }

    fun getRefreshTokenExpiration(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN_EXPIRATION] ?: ""
        }
}
