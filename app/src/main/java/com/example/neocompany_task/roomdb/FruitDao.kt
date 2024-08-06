package com.example.neocompany_task.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.neocompany_task.ui.data.model.FruitModel

@Dao
interface FruitDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: MutableList<FruitModel>)

    @Query("SELECT * FROM Fruit")
    fun getList(): List<FruitModel>

    @Delete
    fun dataDelete(fruitModel: FruitModel)
}