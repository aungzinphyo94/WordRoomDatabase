package com.myanmaritc.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myanmaritc.roomdatabase.R
import com.myanmaritc.roomdatabase.entity.Word
import kotlinx.android.synthetic.main.item_result.view.*

class WordAdapter: RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    var wordList: List<Word> = ArrayList()

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(word: Word) {
            itemView.txtResult.text = word.word
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    fun setWord(wordList: List<Word>) {
        this.wordList = wordList
        notifyDataSetChanged()
    }
}