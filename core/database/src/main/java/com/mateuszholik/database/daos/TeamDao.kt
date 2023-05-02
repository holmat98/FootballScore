package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mateuszholik.database.models.TeamEntity

@Dao
internal interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(teamEntity: TeamEntity)
}
