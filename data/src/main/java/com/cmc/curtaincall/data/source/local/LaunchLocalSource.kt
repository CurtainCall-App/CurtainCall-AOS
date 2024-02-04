package com.cmc.curtaincall.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.cmc.curtaincall.core.local.PreferenceKeys.IS_FIRST_ENTRY_SHOW_LIST
import com.cmc.curtaincall.core.local.qualifiers.LaunchDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LaunchLocalSource @Inject constructor(
    @LaunchDataStore private val dataStore: DataStore<Preferences>
) {
    fun getIsFirstEntryShowList(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[IS_FIRST_ENTRY_SHOW_LIST] ?: true
        }

    suspend fun setIsFirstEntryShowList() {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_ENTRY_SHOW_LIST] = false
        }
    }
}
