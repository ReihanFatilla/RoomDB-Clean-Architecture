package com.reift.latihan_roomdb.domain.repository

import com.reift.latihan_roomdb.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun insertNote(note: Note)
    fun deleteNote(note: Note)
    fun updateNote(note: Note)
    fun getAllNote(): Flow<List<Note>>
}