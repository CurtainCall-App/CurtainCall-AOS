package com.cmc.curtaincall.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cmc.curtaincall.core.local.db.entity.ShowSearchWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowSearchDao {
    @Query("SELECT * FROM ShowSearchWordEntity ORDER BY searchAt DESC")
    fun getAll(): Flow<List<ShowSearchWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShowSearch(showSearchWordEntity: ShowSearchWordEntity)

    @Delete
    suspend fun deleteShowSearch(showSearchWordEntity: ShowSearchWordEntity)

    @Query("DELETE FROM ShowSearchWordEntity")
    suspend fun deleteAll()
}