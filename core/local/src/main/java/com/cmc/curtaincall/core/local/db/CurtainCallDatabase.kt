package com.cmc.curtaincall.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmc.curtaincall.core.local.db.dao.LostItemSearchDao
import com.cmc.curtaincall.core.local.db.dao.ShowSearchDao
import com.cmc.curtaincall.core.local.db.entity.LostItemSearchWordEntity
import com.cmc.curtaincall.core.local.db.entity.ShowSearchWordEntity

@Database(
    entities = [ShowSearchWordEntity::class, LostItemSearchWordEntity::class],
    version = 2
)
abstract class CurtainCallDatabase : RoomDatabase() {
    abstract fun showSearchDao(): ShowSearchDao
    abstract fun lostItemSearchDao(): LostItemSearchDao
}
