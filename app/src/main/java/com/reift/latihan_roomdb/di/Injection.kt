package com.reift.latihan_roomdb.di

import android.content.Context
import com.reift.latihan_roomdb.data.NoteRepositoryImpl
import com.reift.latihan_roomdb.data.local.LocalDataSource
import com.reift.latihan_roomdb.data.local.room.NoteDB
import com.reift.latihan_roomdb.domain.repository.NoteRepository
import com.reift.latihan_roomdb.domain.usecase.NoteInteractor
import com.reift.latihan_roomdb.domain.usecase.NoteUseCase

object Injection {
    private fun provideRepository(context: Context): NoteRepository {
        val database = NoteDB.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.noteDao())

        return NoteRepositoryImpl.getInstance(localDataSource)
    }

    fun provideNoteUseCase(context: Context): NoteUseCase {
        val repository = provideRepository(context)
        return NoteInteractor(repository)
    }
}