package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mateuszholik.database.models.TeamDB

@Dao
internal interface TeamDBDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(teamDB: TeamDB)
}
