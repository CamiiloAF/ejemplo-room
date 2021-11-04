package com.camiloagudelo.ejemploroom

import android.app.Application
import androidx.room.Room
import com.camiloagudelo.ejemploroom.core.database.WordRoomDatabase
import com.camiloagudelo.ejemploroom.data.repositories.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            WordRoomDatabase::class.java, "database-name"
        ).build()
    }
    val repository by lazy { WordRepository(database.wordDao()) }
}