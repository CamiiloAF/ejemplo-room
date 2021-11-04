package com.camiloagudelo.ejemploroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camiloagudelo.ejemploroom.data.models.Word
import com.camiloagudelo.ejemploroom.ui.WordListAdapter
import com.camiloagudelo.ejemploroom.ui.WordViewModel
import com.camiloagudelo.ejemploroom.ui.WordViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val newWordActivityRequestCode = 1

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(
            {
                wordViewModel.delete(it)
            },
            {
                val intent = Intent(this@MainActivity, NewWordActivity::class.java)
                intent.putExtra("word", it)
                startActivityForResult(intent, newWordActivityRequestCode)
            },
        )

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this, { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.submitList(it) }
        })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val id: Int? = data?.getIntExtra(NewWordActivity.EXTRA_ID_REPLY, -1)

            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val wordId = if (id == -1) null else id
                val word = Word(wordId, it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}