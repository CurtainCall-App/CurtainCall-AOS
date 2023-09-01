package com.cmc.curtaincall.core.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmc.curtaincall.domain.model.party.PartySearchWordModel

@Entity
data class PartySearchWordEntity(
    @PrimaryKey val word: String,
    val searchAt: Long
) {
    fun toModel() = PartySearchWordModel(
        searchAt = searchAt,
        word = word
    )
}
