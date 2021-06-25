package com.app.bygclite.localdatabaseservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.bygclite.localdatabaseservice.entities.LoginEntity
import com.app.bygclite.localdatabaseservice.entities.StudentEntity

/** Created by Jishnu P Dileep on 27-05-2021 */

@Database(entities = [StudentEntity::class,LoginEntity::class], version = 1, exportSchema = false)
abstract class LocalRoomDatabase : RoomDatabase() {
    /**
     * Connects the database to the DAO.
     */
    abstract fun appLocalRoomDatabaseDao(): AppLocalRoomDatabaseDao
}