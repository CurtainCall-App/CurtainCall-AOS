package com.cmc.curtaincall.core.local

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
    val ACCESS_TOKEN_EXPIRESAT = stringPreferencesKey("ACCESS_TOKEN_EXPIRESAT")
    val ID_TOKEN = stringPreferencesKey("ID_TOKEN")

    val MEMBER_ID = intPreferencesKey("MEMBER_ID")
    val MEMBER_NICKNAME = stringPreferencesKey("MEMBER_NICKNAME")
}
