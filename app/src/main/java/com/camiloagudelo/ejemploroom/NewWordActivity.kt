package com.camiloagudelo.ejemploroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.camiloagudelo.ejemploroom.data.models.Word

class NewWordActivity : AppCompatActivity() {
    private lateinit var editWordView: EditText
    private var wordToEdit: Word? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editWordView = findViewById(R.id.edit_word)

        wordToEdit = intent.getSerializableExtra("word") as? Word
        wordToEdit?.let { editWordView.setText(it.word) }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                replyIntent.putExtra(EXTRA_ID_REPLY, wordToEdit?.id)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA_ID_REPLY = "com.example.android.wordlistsql.ID_REPLY"
    }
}