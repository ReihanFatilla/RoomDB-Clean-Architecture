package com.reift.latihan_roomdb.mapper

import com.reift.latihan_roomdb.data.local.room.NoteEntity
import com.reift.latihan_roomdb.domain.model.Note

object DataMapper {
    fun mapEntityToDomain(input: NoteEntity): Note{
        return Note(
            input.id,
            input.note
        )
    }

    fun mapDomainToEntity(input: Note): NoteEntity{
        return NoteEntity(
            input.id,
            input.note
        )
    }

    fun mapListEntityToDomain(input: List<NoteEntity>): List<Note>{
        val domain = ArrayList<Note>()
        input.map {
            val note = Note(
                it.id,
                it.note
            )
            domain.add(note)
        }
        return domain
    }
}