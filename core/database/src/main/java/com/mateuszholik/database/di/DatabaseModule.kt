package com.mateuszholik.database.di

import android.content.Context
import androidx.room.Room
import com.mateuszholik.database.FootballDataDatabase
import com.mateuszholik.database.daos.CompetitionDao
import com.mateuszholik.database.daos.MatchInfoDao
import com.mateuszholik.database.daos.TeamDao
import com.mateuszholik.database.daos.WatchedGameDao
import com.mateuszholik.database.utils.DatabaseUtils.FOOTBALL_DATA_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun providesFootballDataDatabase(
        @ApplicationContext context: Context,
    ): FootballDataDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            FootballDataDatabase::class.java,
            FOOTBALL_DATA_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun providesCompetitionDao(footballDataDatabase: FootballDataDatabase): CompetitionDao =
        footballDataDatabase.competitionDao()

    @Singleton
    @Provides
    fun providesMatchInfoDao(footballDataDatabase: FootballDataDatabase): MatchInfoDao =
        footballDataDatabase.matchInfoDao()

    @Singleton
    @Provides
    fun providesTeamDao(footballDataDatabase: FootballDataDatabase): TeamDao =
        footballDataDatabase.teamDao()

    @Singleton
    @Provides
    fun providesWatchedGameDao(footballDataDatabase: FootballDataDatabase): WatchedGameDao =
        footballDataDatabase.watchedGameDao()
}
