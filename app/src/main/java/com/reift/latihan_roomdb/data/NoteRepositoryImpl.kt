package com.reift.latihan_roomdb.data

import com.reift.latihan_roomdb.data.local.LocalDataSource
import com.reift.latihan_roomdb.domain.model.Note
import com.reift.latihan_roomdb.domain.repository.NoteRepository
import com.reift.latihan_roomdb.mapper.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NoteRepositoryImpl(
    private val localDataSource: LocalDataSource
): NoteRepository {

    override fun insertNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch{
            localDataSource.insertNote(
                DataMapper.mapDomainToEntity(note)
            )
        }

    }

    override fun deleteNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch{
            localDataSource.deleteNote(
                DataMapper.mapDomainToEntity(note)
            )
        }
    }

    override fun updateNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch{
            localDataSource.updateNote(
                DataMapper.mapDomainToEntity(note)
            )
        }
    }

    override fun getAllNote(): Flow<List<Note>> {
        return localDataSource.getAllNote().map {
            DataMapper.mapListEntityToDomain(it)
        }
    }

    companion object {
        @Volatile
        private var instance: NoteRepositoryImpl? = null

        fun getInstance(
            localData: LocalDataSource,
        ): NoteRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: NoteRepositoryImpl(localData)
            }
    }

}