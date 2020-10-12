package com.myanmaritc.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.myanmaritc.roomdatabase.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("SELECT * FROM word_table WHERE word LIKE :word ORDER BY word ASC")
    fun getSearchWords(word: String): LiveData<List<Word>>

    @Query("DELETE FROM word_table WHERE word = :word")
    fun delete(word: String)

}