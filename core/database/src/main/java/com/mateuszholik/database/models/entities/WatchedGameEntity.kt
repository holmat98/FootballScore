package com.mateuszholik.database.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateuszholik.database.models.entities.WatchedGameEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
internal data class WatchedGameEntity(
    @PrimaryKey @ColumnInfo(ID_COLUMN_NAME) val gameId: Int,
) {

    internal companion object {
        const val TABLE_NAME = "watched_game"
        const val ID_COLUMN_NAME = "game_id"
    }
}
