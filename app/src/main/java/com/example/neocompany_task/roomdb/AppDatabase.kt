package com.example.neocompany_task.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.neocompany_task.ui.data.model.FruitModel

@Database(
    version = 1,
    entities = [FruitModel::class],
)
abstract class AppDatabase :RoomDatabase(){
    abstract fun fruitDao(): FruitDao
}

