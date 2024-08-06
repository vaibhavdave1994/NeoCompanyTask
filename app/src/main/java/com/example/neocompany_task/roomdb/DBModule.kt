package com.example.neocompany_task.roomdb

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    private val TAG = DBModule::class.java.simpleName

    @Provides
    fun providesAlertDao(userDatabase: AppDatabase): FruitDao = userDatabase.fruitDao()

    @Provides
    @Singleton
    fun providesAlertDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "AlertDatabase")
            .allowMainThreadQueries().build()

    @Provides
    fun providesUserRepository(userDao: FruitDao): FruitDBRepository =
        FruitDBRepository(userDao)

}


