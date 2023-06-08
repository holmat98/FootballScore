package com.mateuszholik.database.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateuszholik.database.models.entities.TeamEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
internal data class TeamEntity(
    @ColumnInfo(name = ID_COLUMN_NAME) @PrimaryKey val id: Int,
    @ColumnInfo(name = CREST_COLUMN_NAME) val crest: String,
    @ColumnInfo(name = TEAM_NAME_COLUMN_NAME) val name: String,
    @ColumnInfo(name = SHORT_NAME_COLUMN_NAME) val shortName: String,
    @ColumnInfo(name = TLA_COLUMN_NAME) val tla: String,
) {

    internal companion object {
        const val TABLE_NAME = "team"
        const val ID_COLUMN_NAME = "id"
        const val CREST_COLUMN_NAME = "crest"
        const val TEAM_NAME_COLUMN_NAME = "name"
        const val SHORT_NAME_COLUMN_NAME = "short_name"
        const val TLA_COLUMN_NAME = "tla"
    }
}
