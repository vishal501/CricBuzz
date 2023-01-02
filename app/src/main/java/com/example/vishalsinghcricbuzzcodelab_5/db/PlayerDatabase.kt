package com.example.vishalsinghcricbuzzcodelab_5.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [CricketPlayer::class], version = 1)
@TypeConverters(Converter::class)
abstract class PlayerDataBase : RoomDatabase() {

    abstract fun playerDao() : CricketPlayerDao

    companion object {
        @Volatile
        private var INSTANCE : PlayerDataBase ?= null

        fun getDatabase(context: Context) : PlayerDataBase {
            if (INSTANCE == null) {
                synchronized(this) {}
                INSTANCE = Room.databaseBuilder(
                    context,
                    PlayerDataBase::class.java, "userDB"
                )
                    .build()
            }
            return INSTANCE!!
        }
    }
}