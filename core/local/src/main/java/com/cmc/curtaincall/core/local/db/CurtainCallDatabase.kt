package com.cmc.curtaincall.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cmc.curtaincall.core.local.db.dao.LostPropertySearchDao
import com.cmc.curtaincall.core.local.db.dao.PartySearchDao
import com.cmc.curtaincall.core.local.db.dao.ShowSearchDao
import com.cmc.curtaincall.core.local.db.entity.LostPropertySearchWordEntity
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

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE `LostItemSearchWordEntity` RENAME TO `LostPropertySearchWordEntity`"
        )
    }
}

@Database(
    entities = [ShowSearchWordEntity::class, LostPropertySearchWordEntity::class, PartySearchWordEntity::class],
    version = 4
)
abstract class CurtainCallDatabase : RoomDatabase() {
    abstract fun showSearchDao(): ShowSearchDao
    abstract fun lostItemSearchDao(): LostPropertySearchDao

    abstract fun partySearchDao(): PartySearchDao
}
