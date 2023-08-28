package com.cmc.curtaincall.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cmc.curtaincall.core.local.db.dao.LostItemSearchDao
import com.cmc.curtaincall.core.local.db.dao.PartySearchDao
import com.cmc.curtaincall.core.local.db.dao.ShowSearchDao
import com.cmc.curtaincall.core.local.db.entity.LostItemSearchWordEntity
import com.cmc.curtaincall.core.local.db.entity.PartySearchWordEntity
import com.cmc.curtaincall.core.local.db.entity.ShowSearchWordEntity

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `PartySearchWordEntity` (`searchAt` INTEGER NOT NULL, `word` TEXT NOT NULL, " +
                "PRIMARY KEY(`word`))"
        )
    }
}

@Database(
    entities = [ShowSearchWordEntity::class, LostItemSearchWordEntity::class, PartySearchWordEntity::class],
    version = 3
)
abstract class CurtainCallDatabase : RoomDatabase() {
    abstract fun showSearchDao(): ShowSearchDao
    abstract fun lostItemSearchDao(): LostItemSearchDao

    abstract fun partySearchDao(): PartySearchDao
}
