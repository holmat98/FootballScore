package com.mateuszholik.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mateuszholik.database.converters.LocalDateTimeConverter
import com.mateuszholik.database.daos.CompetitionDao
import com.mateuszholik.database.daos.MatchInfoDao
import com.mateuszholik.database.daos.TeamDao
import com.mateuszholik.database.models.entities.CompetitionEntity
import com.mateuszholik.database.models.entities.MatchInfoEntity
import com.mateuszholik.database.models.entities.TeamEntity
import com.mateuszholik.database.models.views.MergedMatchInfo

@Database(
    entities = [CompetitionEntity::class, TeamEntity::class, MatchInfoEntity::class],
    views = [MergedMatchInfo::class],
    version = 1,
)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class FootballDataDatabase : RoomDatabase() {

    abstract fun competitionDao(): CompetitionDao
    abstract fun teamDao(): TeamDao
    abstract fun matchInfoDao(): MatchInfoDao
}
