package com.camiloagudelo.ejemploroom.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "word_table")
class Word(@PrimaryKey(autoGenerate = true) val id: Int?, val word: String): Serializable