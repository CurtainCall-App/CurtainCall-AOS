package com.cmc.curtaincall.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.cmc.curtaincall.core.local.PreferenceKeys.MEMBER_ID
import com.cmc.curtaincall.core.local.PreferenceKeys.MEMBER_NICKNAME
import com.cmc.curtaincall.core.local.qualifiers.MemberDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemberLocalSource @Inject constructor(
    @MemberDataStore private val dataStore: DataStore<Preferences>
) {
    suspend fun saveMemberId(id: Int) {
        dataStore.edit { preferences ->
            preferences[MEMBER_ID] = id
        }
    }

    suspend fun saveMemberNickname(nickname: String) {
        dataStore.edit { preferences ->
            preferences[MEMBER_NICKNAME] = nickname
        }
    }

    fun getMemberId(): Flow<Int> =
        dataStore.data.map { preferences ->
            preferences[MEMBER_ID] ?: Int.MIN_VALUE
        }

    fun getMemberNickname(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[MEMBER_NICKNAME] ?: ""
        }
}
