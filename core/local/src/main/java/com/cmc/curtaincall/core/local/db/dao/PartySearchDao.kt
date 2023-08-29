package com.cmc.curtaincall.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cmc.curtaincall.core.local.db.entity.PartySearchWordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface PartySearchDao {
    @Query("SELECT * FROM PartySearchWordEntity ORDER BY searchAt DESC")
    fun getAll(): Flow<List<PartySearchWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartyEntity(partySearchWordEntity: PartySearchWordEntity)

    @Delete
    suspend fun deletePartySearch(partySearchWordEntity: PartySearchWordEntity)

    @Query("DELETE FROM PartySearchWordEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM PartySearchWordEntity WHERE searchAt=(SELECT min(searchAt) FROM PartySearchWordEntity)")
    fun findOldestSearch(): Flow<PartySearchWordEntity>

    @Transaction
    suspend fun insertPartySearch(partySearchWordEntity: PartySearchWordEntity) {
        val searchList = getAll().first()
        if (searchList.size >= 10) {
            deletePartySearch(findOldestSearch().first())
        }
        insertPartyEntity(partySearchWordEntity)
    }
}
