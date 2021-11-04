package com.camiloagudelo.ejemploroom.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.camiloagudelo.ejemploroom.data.dao.WordDao
import com.camiloagudelo.ejemploroom.data.models.Word

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
