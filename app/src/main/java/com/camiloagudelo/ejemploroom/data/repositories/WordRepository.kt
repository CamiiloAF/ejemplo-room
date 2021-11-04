package com.camiloagudelo.ejemploroom.data.repositories

import com.camiloagudelo.ejemploroom.data.dao.WordDao
import com.camiloagudelo.ejemploroom.data.models.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        if (word.id == null) wordDao.insert(word) else wordDao.update(word)
    }

    suspend fun delete(word: Word) {
        wordDao.delete(word)
    }
}