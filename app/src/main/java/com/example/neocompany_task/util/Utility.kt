package com.example.neocompany_task.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
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

        fun countListChar(fruitListData:List<FruitModel>):String {
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
            return builder.toString()

        }

        fun setDataList(){
            fruitList = ArrayList<FruitModel>()
            fruitList.add(FruitModel("Apple", "Fruit1"))
            fruitList.add(FruitModel("Avocados", "Fruit2"))
            fruitList.add(FruitModel("Apricot", "Fruit3"))
            fruitList.add(FruitModel("Banana", "Fruit4"))
            fruitList.add(FruitModel("Blueberries", "Fruit5"))
            fruitList.add(FruitModel("Cherry", "Fruit6"))
            fruitList.add(FruitModel("Cantaloupe", "Fruit7"))
            fruitList.add(FruitModel("Clementine", "Fruit8"))
            fruitList.add(FruitModel("Cucumbers", "Fruit9"))
            fruitList.add(FruitModel("Dewberries", "Fruit10"))
            fruitList.add(FruitModel("Dragon", "Fruit11"))
            fruitList.add(FruitModel("Elderberry", "Fruit12"))
            fruitList.add(FruitModel("Evergreen", "Fruit13"))
            fruitList.add(FruitModel("Imbe", "Fruit14"))
            fruitList.add(FruitModel("Indian Fig", "Fruit15"))
            fruitList.add(FruitModel("Jackfruit", "Fruit16"))
            fruitList.add(FruitModel("Jambolan", "Fruit17"))
            fruitList.add(FruitModel("Kiwi", "Fruit18"))
            fruitList.add(FruitModel("Lime", "Fruit19"))
            fruitList.add(FruitModel("Longan", "Fruit20"))
            fruitList.add(FruitModel("Mango", "Fruit21"))
            fruitList.add(FruitModel("Mandarin", "Fruit22"))
            fruitList.add(FruitModel("Orange", "Fruit23"))
            fruitList.add(FruitModel("Mulberry", "Fruit24"))
            fruitList.add(FruitModel("Melon", "Fruit25"))

        }

        private fun DrawScope.drawIndicator(
            x: Float,
            y: Float,
            width: Float,
            height: Float,
            radius: CornerRadius
        ) {
            val rect = RoundRect(
                x,
                y - height / 2,
                x + width,
                y + height / 2,
                radius
            )
            val path = Path().apply { addRoundRect(rect) }
            drawPath(path = path, color = Color.White)
        }

    }

}