package com.my.githubviewer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.my.githubviewer.data.db.github.repo.RepoDao
import com.my.githubviewer.data.transformer.DateConverters

@Database(
    entities = [
        DBData.Repo::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gitHubRepoDao(): RepoDao

}