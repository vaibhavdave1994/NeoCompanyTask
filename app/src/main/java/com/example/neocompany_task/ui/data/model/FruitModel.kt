package com.example.neocompany_task.ui.data.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

 @Entity(tableName = "Fruit")
 data class FruitModel (
  @ColumnInfo("fruitName") var fruitName: String? = null,
  @ColumnInfo("whatIs") var whatIs: String? = null,
){
  @PrimaryKey(autoGenerate = true)var id=0

 }