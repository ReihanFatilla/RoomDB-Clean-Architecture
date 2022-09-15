package com.reift.latihan_roomdb.data.local.room

import androidx.room.*
import com.reift.latihan_roomdb.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("Select * from NoteEntity")
    fun getAllNote(): Flow<List<NoteEntity>>
}