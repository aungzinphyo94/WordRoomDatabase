package com.myanmaritc.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.myanmaritc.roomdatabase.database.WordDatabase
import com.myanmaritc.roomdatabase.entity.Word
import com.myanmaritc.roomdatabase.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewmodel(application: Application) : AndroidViewModel(application){

    private val repository: WordRepository

    val allWords: LiveData<List<Word>>

    init {
        val wordDao = WordDatabase.getDatabase(application, viewModelScope)
            .wordDao()

        repository = WordRepository(wordDao)
        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun getSearchWord(word: String): LiveData<List<Word>> {
        return repository.searchWord(word)
    }

}