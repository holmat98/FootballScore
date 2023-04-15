package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class TablePositionApi(
    @SerializedName("position")
    val position: Int,
    @SerializedName("team")
    val team: TeamApi,
    @SerializedName("playedGames")
    val playedGames: Int,
    @SerializedName("form")
    val form: String?,
    @SerializedName("won")
    val won: Int,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("lost")
    val lost: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("goalsFor")
    val goalsScored: Int,
    @SerializedName("goalsAgainst")
    val goalsConceded: Int,
    @SerializedName("goalsDifference")
    val goalsDifference: Int,
)
