package com.cmc.curtaincall.domain.enums

enum class ShowGenreType(val value: String) {
    PLAY("연극"), MUSICAL("뮤지컬")
}

fun translateShowGenreType(name: String): ShowGenreType = when (name) {
    ShowGenreType.PLAY.name -> ShowGenreType.PLAY
    ShowGenreType.MUSICAL.name -> ShowGenreType.MUSICAL
    else -> ShowGenreType.PLAY
}
