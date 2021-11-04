package com.camiloagudelo.ejemploroom.data.dao

import androidx.room.*
import com.camiloagudelo.ejemploroom.data.models.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Delete(entity = Word::class)
    suspend fun delete(word: Word)
}