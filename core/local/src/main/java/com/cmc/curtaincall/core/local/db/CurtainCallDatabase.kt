package com.cmc.curtaincall.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmc.curtaincall.core.local.db.dao.ShowSearchDao
import com.cmc.curtaincall.core.local.db.entity.ShowSearchWordEntity

@Database(entities = [ShowSearchWordEntity::class], version = 1)
abstract class CurtainCallDatabase : RoomDatabase() {
    abstract fun showSearchDao(): ShowSearchDao
}
