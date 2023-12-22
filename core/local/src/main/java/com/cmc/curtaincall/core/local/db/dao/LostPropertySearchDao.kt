package com.cmc.curtaincall.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cmc.curtaincall.core.local.db.entity.LostPropertySearchWordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface LostPropertySearchDao {
    @Query("SELECT * FROM LostPropertySearchWordEntity ORDER BY searchAt DESC")
    fun getAll(): Flow<List<LostPropertySearchWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLostItemSearchEntity(lostItemSearchWordEntity: LostPropertySearchWordEntity)

    @Delete
    suspend fun deleteLostItemSearchEntity(lostItemSearchWordEntity: LostPropertySearchWordEntity)

    @Query("DELETE FROM LostPropertySearchWordEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM LostPropertySearchWordEntity WHERE searchAt=(SELECT min(searchAt) FROM LostPropertySearchWordEntity)")
    fun findOldestSearch(): Flow<LostPropertySearchWordEntity>

    @Transaction
    suspend fun insertLostItemSearch(lostItemSearchWordEntity: LostPropertySearchWordEntity) {
        val searchList = getAll().first()
        if (searchList.size >= 10) {
            deleteLostItemSearchEntity(findOldestSearch().first())
        }
        insertLostItemSearchEntity(lostItemSearchWordEntity)
    }
}
