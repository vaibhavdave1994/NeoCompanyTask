package com.example.neocompany_task.ui.domain.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neocompany_task.roomdb.FruitDBRepository
import com.example.neocompany_task.ui.data.model.FruitModel
import com.example.neocompany_task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fruitDBRepository: FruitDBRepository,
    @ApplicationContext val context: Context,


    ) : ViewModel() {

   /* val FruitData: MutableLiveData<Resource<FruitModel>> = MutableLiveData()

    fun fetchCustomerData(page: Int){
        FruitData.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val response = fruitDBRepository.getList
                FruitData.postValue(Resource.Success(response))

            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> FruitData.postValue(Resource.Error("Network Failure " +  ex.localizedMessage))
                    else -> FruitData.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }*/

    val getList: List<FruitModel>
        get() = fruitDBRepository.getList

    suspend fun delete(fruitModel: FruitModel) {
        viewModelScope.launch {
            fruitDBRepository.delete(fruitModel)
        }
    }

    suspend fun insert(user: MutableList<FruitModel>) {
        viewModelScope.launch {
            fruitDBRepository.insert(user)
        }
    }


}
