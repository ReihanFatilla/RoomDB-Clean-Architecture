package com.reift.latihan_roomdb.domain.usecase

import com.reift.latihan_roomdb.domain.repository.NoteRepository
import com.reift.latihan_roomdb.domain.model.Note
import kotlinx.coroutines.flow.Flow

class NoteInteractor(private val repository: NoteRepository): NoteUseCase {
    override fun insertNote(note: Note) {
        repository.insertNote(note)
    }

    override fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }

    override fun updateNote(note: Note) {
        repository.updateNote(note)
    }

    override fun getAllNote(): Flow<List<Note>> {
        return repository.getAllNote()
    }
}