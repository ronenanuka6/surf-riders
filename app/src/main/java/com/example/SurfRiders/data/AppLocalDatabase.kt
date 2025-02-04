package com.example.SurfRiders.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.SurfRiders.SurfRidersApplication
import com.example.SurfRiders.data.review.Review
import com.example.SurfRiders.data.review.ReviewDao
import com.example.SurfRiders.data.user.User
import com.example.SurfRiders.data.user.UserDTO


@Database(entities = [Review::class, User::class], version = 7, exportSchema = true)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
    abstract fun userDto(): UserDTO
}

object AppLocalDatabase {
    val db: AppLocalDbRepository by lazy {
        val context = SurfRidersApplication.Globals.appContext
            ?: throw IllegalStateException("Application context not available")

        Room.databaseBuilder(
            context,
            AppLocalDbRepository::class.java,
            "SurfRiders"
        ).fallbackToDestructiveMigration()
            .build()
    }
}