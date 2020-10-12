package com.myanmaritc.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.myanmaritc.roomdatabase.dao.WordDao
import com.myanmaritc.roomdatabase.entity.Word

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun searchWord(word: String) : LiveData<List<Word>> {
        return wordDao.getSearchWords(word)
    }
}