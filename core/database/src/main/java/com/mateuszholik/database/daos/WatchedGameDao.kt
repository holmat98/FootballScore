package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mateuszholik.database.models.entities.WatchedGameEntity
import com.mateuszholik.database.models.entities.WatchedGameEntity.Companion.ID_COLUMN_NAME
import com.mateuszholik.database.models.entities.WatchedGameEntity.Companion.TABLE_NAME

@Dao
internal interface WatchedGameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(watchedGameEntity: WatchedGameEntity)

    @Delete
    suspend fun delete(watchedGameEntity: WatchedGameEntity)

    @Query("SELECT $ID_COLUMN_NAME FROM $TABLE_NAME")
    suspend fun getAllWatchedGames(): List<Int>
}
