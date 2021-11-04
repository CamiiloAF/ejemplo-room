package com.camiloagudelo.ejemploroom.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camiloagudelo.ejemploroom.R
import com.camiloagudelo.ejemploroom.data.models.Word

class WordListAdapter(private val onDelete: (word: Word) -> Unit, private val onEdit: (word: Word) -> Unit) : ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)
        private val wordImageViewDelete: ImageView = itemView.findViewById(R.id.imgDelete)
        private val wordImageViewEdit: ImageView = itemView.findViewById(R.id.imgEdit)

        fun bind(word: Word) {
            wordItemView.text = word.word
            wordImageViewDelete.setOnClickListener {
                onDelete(word)
            }
            wordImageViewEdit.setOnClickListener {
                onEdit(word)
            }

        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }
    }
}