package com.example.testcleanarch.di

import android.content.Context
import androidx.room.Room
import com.example.testcleanarch.data.AppDB.AppDataBase
import com.example.testcleanarch.data.api.RemoteDataSource
import com.example.testcleanarch.data.api.Service
import com.example.testcleanarch.data.dao.UserDao
import com.example.testcleanarch.data.repository.UserRepositoryImpl
import com.example.testcleanarch.data.storage.SharedPrefsStorage
import com.example.testcleanarch.data.storage.UserStorage
import com.example.testcleanarch.data.utils.BaseApiResponse
import com.example.testcleanarch.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providesUserStorage(@ApplicationContext context: Context):UserStorage{
        return SharedPrefsStorage(context = context)
    }
    @Provides
    @Singleton
    fun provides(@ApplicationContext context: Context):AppDataBase{
        return Room.databaseBuilder(context,AppDataBase::class.java,"database.db")
            //.createFromAsset("room_article.db")
            .build()
    }

    @Provides
    @Singleton
    fun providesRemoteDataSourse(service: Service): RemoteDataSource {
        return RemoteDataSource(service = service)
    }
    @Provides
    @Singleton
    fun providesUserRepository(userStorage: UserStorage,userDao: AppDataBase,remoteDataSource: RemoteDataSource):UserRepository{
        return UserRepositoryImpl(userStorage = userStorage,userDao = userDao.getUserDao(),remoteDataSource = remoteDataSource)
    }



}