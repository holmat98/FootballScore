package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mateuszholik.database.models.entities.TeamEntity

@Dao
internal interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(teamEntity: TeamEntity)
}
