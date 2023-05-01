package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mateuszholik.database.models.CompetitionDB

@Dao
internal interface CompetitionDBDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(competitionDB: CompetitionDB)
}
