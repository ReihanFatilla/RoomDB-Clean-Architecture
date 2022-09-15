package com.reift.latihan_roomdb.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val note: String
)