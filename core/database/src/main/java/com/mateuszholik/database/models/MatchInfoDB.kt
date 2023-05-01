package com.mateuszholik.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mateuszholik.database.models.MatchInfoDB.Companion.AWAY_TEAM_ID_COLUMN_NAME
import com.mateuszholik.database.models.MatchInfoDB.Companion.COMPETITION_ID_COLUMN_NAME
import com.mateuszholik.database.models.MatchInfoDB.Companion.HOME_TEAM_ID_COLUMN_NAME
import com.mateuszholik.database.models.MatchInfoDB.Companion.TABLE_NAME
import java.time.LocalDateTime

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = CompetitionDB::class,
            childColumns = [COMPETITION_ID_COLUMN_NAME],
            parentColumns = [CompetitionDB.ID_COLUMN_NAME]
        ),
        ForeignKey(
            entity = TeamDB::class,
            childColumns = [AWAY_TEAM_ID_COLUMN_NAME],
            parentColumns = [TeamDB.ID_COLUMN_NAME]
        ),
        ForeignKey(
            entity = TeamDB::class,
            childColumns = [HOME_TEAM_ID_COLUMN_NAME],
            parentColumns = [TeamDB.ID_COLUMN_NAME]
        )
    ]
)
internal data class MatchInfoDB(
    @ColumnInfo(name = ID_COLUMN_NAME) @PrimaryKey val id: Int,
    @ColumnInfo(name = COMPETITION_ID_COLUMN_NAME) val competitionId: Int,
    @ColumnInfo(name = AWAY_TEAM_ID_COLUMN_NAME) val awayTeamId: Int,
    @ColumnInfo(name = HOME_TEAM_ID_COLUMN_NAME) val homeTeamId: Int,
    @ColumnInfo(name = DURATION_COLUMN_NAME) val duration: String,
    @ColumnInfo(name = HOME_FULL_TIME_SCORE_COLUMN_NAME) val homeFullTimeScore: Int?,
    @ColumnInfo(name = AWAY_FULL_TIME_SCORE_COLUMN_NAME) val awayFullTimeScore: Int?,
    @ColumnInfo(name = WINNER_COLUMN_NAME) val winner: String,
    @ColumnInfo(name = UTC_DATE_COLUMN_NAME) val utcDate: LocalDateTime,
) {

    internal companion object {
        const val TABLE_NAME = "match_info"
        const val ID_COLUMN_NAME = "id"
        const val COMPETITION_ID_COLUMN_NAME = "competition_id"
        const val AWAY_TEAM_ID_COLUMN_NAME = "away_team_id"
        const val HOME_TEAM_ID_COLUMN_NAME = "home_team_id"
        const val DURATION_COLUMN_NAME = "duration"
        const val HOME_FULL_TIME_SCORE_COLUMN_NAME = "home_full_time_score"
        const val AWAY_FULL_TIME_SCORE_COLUMN_NAME = "away_full_time_score"
        const val WINNER_COLUMN_NAME = "winner"
        const val UTC_DATE_COLUMN_NAME = "utc_date"
    }
}
