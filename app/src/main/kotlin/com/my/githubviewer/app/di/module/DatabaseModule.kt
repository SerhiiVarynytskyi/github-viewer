package com.my.githubviewer.app.di.module

import android.content.Context
import androidx.room.Room
import com.my.githubviewer.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase").build()

    @Provides
    @Singleton
    fun provideHubRepoDao(appDatabase: AppDatabase) = appDatabase.gitHubRepoDao()

}