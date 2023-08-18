package com.cmc.curtaincall.core.local

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
    val ACCESS_TOKEN_EXPIRATION = stringPreferencesKey("ACCESS_TOKEN_EXPIRATION")
    val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
    val REFRESH_TOKEN_EXPIRATION = stringPreferencesKey("REFRESH_TOKEN_EXPIRATION")
}
