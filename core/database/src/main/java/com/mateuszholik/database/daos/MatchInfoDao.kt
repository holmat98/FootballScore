package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mateuszholik.database.models.entities.MatchInfoEntity
import com.mateuszholik.database.models.views.MergedMatchInfo
import java.time.LocalDateTime

@Dao
internal interface MatchInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(matchInfoEntity: MatchInfoEntity)

    @Query("SELECT * FROM MergedMatchInfo WHERE utcDate >= :dayStart AND utcDate < :dayEnd")
    suspend fun getListOfMatchInfoFor(
        dayStart: LocalDateTime,
        dayEnd: LocalDateTime,
    ): List<MergedMatchInfo>
}
