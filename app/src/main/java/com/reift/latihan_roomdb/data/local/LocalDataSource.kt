package com.reift.latihan_roomdb.data.local

import android.content.Context
import com.reift.latihan_roomdb.data.local.room.NoteDao
import com.reift.latihan_roomdb.data.local.room.NoteEntity
import com.reift.latihan_roomdb.domain.model.Note
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(
    private val dao: NoteDao
    ) {
    suspend fun insertNote(note: NoteEntity){
        dao.insertNote(note)
    }
    suspend fun deleteNote(note: NoteEntity){
        dao.deleteNote(note)
    }
    suspend fun updateNote(note: NoteEntity){
        dao.updateNote(note)
    }
    fun getAllNote(): Flow<List<NoteEntity>>{
        return dao.getAllNote()
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(noteDao: NoteDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(noteDao)
            }
    }
}