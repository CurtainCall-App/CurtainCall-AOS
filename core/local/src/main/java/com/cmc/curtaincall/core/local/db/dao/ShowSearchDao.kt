package com.cmc.curtaincall.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cmc.curtaincall.core.local.db.entity.ShowSearchWordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface ShowSearchDao {
    @Query("SELECT * FROM ShowSearchWordEntity ORDER BY searchAt DESC")
    fun getAll(): Flow<List<ShowSearchWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchEntity(showSearchWordEntity: ShowSearchWordEntity)

    @Delete
    suspend fun deleteShowSearch(showSearchWordEntity: ShowSearchWordEntity)

    @Query("DELETE FROM ShowSearchWordEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM ShowSearchWordEntity WHERE searchAt=(SELECT min(searchAt) FROM ShowSearchWordEntity)")
    fun findOldestSearch(): Flow<ShowSearchWordEntity>

    @Transaction
    suspend fun insertShowSearch(showSearchWordEntity: ShowSearchWordEntity) {
        val searchList = getAll().first()
        if (searchList.size >= 10) {
            deleteShowSearch(findOldestSearch().first())
        }
        insertSearchEntity(showSearchWordEntity)
    }
}
