package com.reift.latihan_roomdb.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDB: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDB? = null

        @JvmStatic
        fun getInstance(context: Context): NoteDB {
            if (INSTANCE == null){
                synchronized(NoteDB::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDB::class.java,
                        "note_database"
                    ).build()
                }
            }
            return INSTANCE as NoteDB
        }
    }
}