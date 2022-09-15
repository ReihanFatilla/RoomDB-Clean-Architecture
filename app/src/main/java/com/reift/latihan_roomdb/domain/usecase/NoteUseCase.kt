package com.reift.latihan_roomdb.domain.usecase

import com.reift.latihan_roomdb.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteUseCase {
    fun insertNote(note: Note)
    fun deleteNote(note: Note)
    fun updateNote(note: Note)
    fun getAllNote(): Flow<List<Note>>
}