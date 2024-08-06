package com.example.neocompany_task.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Utility {

    companion object {

        fun saveImageToInternalStorage(bitmap: Bitmap, context: Context): String {
            val fileName = "${System.currentTimeMillis()}.png"
            val file = File(context.filesDir, fileName)
            try {
                val fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.close()
            } catch (e: IOException) {
                Log.e("errorimage",e.message.toString())
                e.printStackTrace()
            }
            return file.absolutePath
        }

        // Save the image path to the database

        // Retrieve the image from the path
        fun loadImageFromPath(imagePath: String): Bitmap? {
            return BitmapFactory.decodeFile(imagePath)
        }

        fun bitmapToBase64(bitmap: Bitmap): String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }

        // Convert Base64 string to Bitmap
        fun base64ToBitmap(base64Str: String): Bitmap {
            val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }

    }

}