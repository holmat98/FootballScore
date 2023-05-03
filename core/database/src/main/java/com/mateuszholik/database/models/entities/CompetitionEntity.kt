package com.mateuszholik.database.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateuszholik.database.models.entities.CompetitionEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
internal data class CompetitionEntity(
    @ColumnInfo(name = ID_COLUMN_NAME) @PrimaryKey val id: Int,
    @ColumnInfo(name = CODE_COLUMN_NAME) val code: String,
    @ColumnInfo(name = COUNTRY_NAME_COLUMN_NAME) val countryName: String,
    @ColumnInfo(name = COUNTRY_CODE_COLUMN_NAME) val countryCode: String,
    @ColumnInfo(name = COUNTRY_FLAG_COLUMN_NAME) val countryFlag: String,
    @ColumnInfo(name = EMBLEM_COLUMN_NAME) val emblem: String,
    @ColumnInfo(name = COMPETITION_NAME_COLUMN_NAME) val name: String,
    @ColumnInfo(name = TYPE_COLUMN_NAME) val type: String,
) {

    internal companion object {
        const val TABLE_NAME = "competition"
        const val ID_COLUMN_NAME = "id"
        const val CODE_COLUMN_NAME = "code"
        const val COUNTRY_NAME_COLUMN_NAME = "country_name"
        const val COUNTRY_CODE_COLUMN_NAME = "country_code"
        const val COUNTRY_FLAG_COLUMN_NAME = "country_flag"
        const val EMBLEM_COLUMN_NAME = "emblem"
        const val COMPETITION_NAME_COLUMN_NAME = "name"
        const val TYPE_COLUMN_NAME = "type"
    }
}
