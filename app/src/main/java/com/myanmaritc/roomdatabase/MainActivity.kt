package com.myanmaritc.roomdatabase

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myanmaritc.roomdatabase.adapter.WordAdapter
import com.myanmaritc.roomdatabase.entity.Word
import com.myanmaritc.roomdatabase.viewmodel.WordViewmodel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var wordViewmodel: WordViewmodel
    val wordAdapter = WordAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewmodel = ViewModelProvider(this).get(WordViewmodel::class.java)

        btnSave.setOnClickListener {
            val word = Word(edtWord.text.toString())
            wordViewmodel.insert(word)
        }

        recyclerWord.layoutManager = LinearLayoutManager(this)
        recyclerWord.adapter = wordAdapter

        wordViewmodel.allWords.observe(this, Observer {
            wordAdapter.setWord(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Search any"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                var searchQuery = "%$newText%"

                wordViewmodel.getSearchWord(searchQuery!!).observe(this@MainActivity, Observer {
                    Log.d("SEARCH>>>>>>", searchQuery)
                    Log.d("SEARCH>>>>>>", it.toString())
                    wordAdapter.setWord(it)
                })
                recyclerWord.layoutManager = LinearLayoutManager(applicationContext)
                recyclerWord.adapter = wordAdapter

                return true
            }

        })

        return super.onCreateOptionsMenu(menu)

    }
}