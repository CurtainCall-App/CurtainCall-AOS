package com.cmc.curtaincall.core.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
    val ACCESS_TOKEN_EXPIRESAT = stringPreferencesKey("ACCESS_TOKEN_EXPIRESAT")
    val ID_TOKEN = stringPreferencesKey("ID_TOKEN")

    val MEMBER_ID = intPreferencesKey("MEMBER_ID")
    val MEMBER_NICKNAME = stringPreferencesKey("MEMBER_NICKNAME")

    val IS_FIRST_ENTRY_SHOW_LIST = booleanPreferencesKey("IS_FIRST_ENTRY_SHOW_LIST")

    val IS_SHOW_PARTY_TOOLTIPS = booleanPreferencesKey("IS_SHOW_PARTY_TOOLTIPS")
}
