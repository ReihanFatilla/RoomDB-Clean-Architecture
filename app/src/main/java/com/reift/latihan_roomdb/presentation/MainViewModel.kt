package com.reift.latihan_roomdb.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.latihan_roomdb.domain.model.Note
import com.reift.latihan_roomdb.domain.usecase.NoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(val noteUseCase: NoteUseCase): ViewModel() {

    val listNote = MutableLiveData<List<Note>>()

    val isUpdate = MutableLiveData(false)

    fun setAddStatus(){
        isUpdate.value = false
    }

    fun setUpdateStatus(){
        isUpdate.value = true
    }

    fun insertNote(note: Note){
        noteUseCase.insertNote(note)
    }
    fun deleteNote(note: Note){
        noteUseCase.deleteNote(note)
    }
    fun updateNote(note: Note){
        noteUseCase.updateNote(note)
    }
    fun getAllNote(){
        CoroutineScope(Dispatchers.IO).launch {
            noteUseCase.getAllNote().collect{
                listNote.postValue(it)
            }
        }
    }
}