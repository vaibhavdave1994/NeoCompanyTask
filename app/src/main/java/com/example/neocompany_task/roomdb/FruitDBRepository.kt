package com.example.neocompany_task.roomdb


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neocompany_task.ui.data.model.FruitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FruitDBRepository @Inject constructor (private val fruitDao : FruitDao) {

    val getList: List<FruitModel> = fruitDao.getList()

    @WorkerThread
    suspend fun insert(responseData: MutableList<FruitModel>) = withContext(Dispatchers.IO) {
        fruitDao.insert(responseData)
    }

    @WorkerThread
    suspend fun delete(fruitModel: FruitModel) = withContext(Dispatchers.IO) {
        fruitDao.dataDelete(fruitModel = fruitModel)
    }
}