package com.cmc.curtaincall.domain.type

import java.lang.IllegalStateException

enum class ShowGenreType(val value: String) {
    PLAY("연극"), MUSICAL("뮤지컬")
}

fun translateShowGenreType(name: String): ShowGenreType = when (name) {
    ShowGenreType.PLAY.name -> ShowGenreType.PLAY
    ShowGenreType.MUSICAL.name -> ShowGenreType.MUSICAL
    else -> throw IllegalStateException()
}
