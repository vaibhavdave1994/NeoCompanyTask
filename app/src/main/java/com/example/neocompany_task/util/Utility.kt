package com.example.neocompany_task.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.neocompany_task.databinding.DesignBottomSheetBinding
import com.example.neocompany_task.ui.data.model.FruitModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Utility {

    companion object {
        const val TAG = "ModalBottomSheetDialog"
        lateinit var fruitList: MutableList<FruitModel>

        fun countListChar(fruitListData:List<FruitModel>,binding: DesignBottomSheetBinding) {
            // Sample ArrayList
            var list = ArrayList<String>()
            for (i in fruitListData.indices) {
                fruitListData[i].fruitName?.let { list.add(it) }
            }
            // MutableMap to store counts of first characters
            val firstCharCount = mutableMapOf<Char, Int>()

            // Iterate through the list and count first characters

            for (s in list) {
                if (s.isNotEmpty()) {
                    val firstChar = s[0]
                    firstCharCount[firstChar] = firstCharCount.getOrDefault(firstChar, 0) + 1
                }
            }


            // Print the result
            val builder = StringBuilder()
            for ((char, count) in firstCharCount) {
                builder.append("Character: $char, Count: $count\n");
                println("Character: $char, Count: $count")
            }
            binding.textShare.text = builder.toString()

        }

        fun setDataList(){
            fruitList = ArrayList<FruitModel>()
            fruitList.add(FruitModel("Apple", "Fruit"))
            fruitList.add(FruitModel("Avocados", "Fruit"))
            fruitList.add(FruitModel("Apricot", "Fruit"))
            fruitList.add(FruitModel("Banana", "Fruit"))
            fruitList.add(FruitModel("Blueberries", "Fruit"))
            fruitList.add(FruitModel("Cherry", "Fruit"))
            fruitList.add(FruitModel("Cantaloupe", "Fruit"))
            fruitList.add(FruitModel("Clementine", "Fruit"))
            fruitList.add(FruitModel("Cucumbers", "Fruit"))
            fruitList.add(FruitModel("Dewberries", "Fruit"))
            fruitList.add(FruitModel("Dragon", "Fruit"))
            fruitList.add(FruitModel("Elderberry", "Fruit"))
            fruitList.add(FruitModel("Evergreen", "Fruit"))
            fruitList.add(FruitModel("Imbe", "Fruit"))
            fruitList.add(FruitModel("Indian Fig", "Fruit"))
            fruitList.add(FruitModel("Jackfruit", "Fruit"))
            fruitList.add(FruitModel("Jambolan", "Fruit"))
            fruitList.add(FruitModel("Kiwi", "Fruit"))
            fruitList.add(FruitModel("Lime", "Fruit"))
            fruitList.add(FruitModel("Longan", "Fruit"))
            fruitList.add(FruitModel("Mango", "Fruit"))
            fruitList.add(FruitModel("Mandarin", "Fruit"))
            fruitList.add(FruitModel("Orange", "Fruit"))
            fruitList.add(FruitModel("Mulberry", "Fruit"))
            fruitList.add(FruitModel("Melon", "Fruit"))

        }

    }

}