package com.cmc.curtaincall.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cmc.curtaincall.core.local.db.entity.LostItemSearchWordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface LostItemSearchDao {
    @Query("SELECT * FROM LostItemSearchWordEntity ORDER BY searchAt DESC")
    fun getAll(): Flow<List<LostItemSearchWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLostItemSearchEntity(lostItemSearchWordEntity: LostItemSearchWordEntity)

    @Delete
    suspend fun deleteLostItemSearchEntity(lostItemSearchWordEntity: LostItemSearchWordEntity)

    @Query("DELETE FROM LostItemSearchWordEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM LostItemSearchWordEntity WHERE searchAt=(SELECT min(searchAt) FROM LostItemSearchWordEntity)")
    fun findOldestSearch(): Flow<LostItemSearchWordEntity>

    @Transaction
    suspend fun insertLostItemSearch(lostItemSearchWordEntity: LostItemSearchWordEntity) {
        val searchList = getAll().first()
        if (searchList.size >= 10) {
            deleteLostItemSearchEntity(findOldestSearch().first())
        }
        insertLostItemSearchEntity(lostItemSearchWordEntity)
    }
}
