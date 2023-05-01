package com.mateuszholik.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mateuszholik.database.daos.CompetitionDBDao
import com.mateuszholik.database.daos.MatchInfoDao
import com.mateuszholik.database.daos.TeamDBDao
import com.mateuszholik.database.models.CompetitionDB
import com.mateuszholik.database.models.MatchInfoDB
import com.mateuszholik.database.models.TeamDB

@Database(
    entities = [CompetitionDB::class, TeamDB::class, MatchInfoDB::class],
    version = 1
)
internal abstract class FootballDataDatabase : RoomDatabase() {

    abstract fun competitionDBDao(): CompetitionDBDao
    abstract fun teamDBDao(): TeamDBDao
    abstract fun matchInfoDao(): MatchInfoDao
}
