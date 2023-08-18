package com.cmc.curtaincall.core.local

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
    val ACCESS_TOKEN_EXPIRESAT = stringPreferencesKey("ACCESS_TOKEN_EXPIRESAT")
    val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
    val REFRESH_TOKEN_EXPIRESAT = stringPreferencesKey("REFRESH_TOKEN_EXPIRESAT")
}
