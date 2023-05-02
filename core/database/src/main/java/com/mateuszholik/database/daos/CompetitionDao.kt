package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mateuszholik.database.models.CompetitionEntity

@Dao
internal interface CompetitionDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(competitionEntity: CompetitionEntity)
}
