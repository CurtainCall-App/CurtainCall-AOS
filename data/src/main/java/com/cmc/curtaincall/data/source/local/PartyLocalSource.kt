package com.cmc.curtaincall.data.source.local

import com.cmc.curtaincall.core.local.db.dao.PartySearchDao
import com.cmc.curtaincall.core.local.db.entity.PartySearchWordEntity
import com.cmc.curtaincall.domain.model.party.PartySearchWordModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartyLocalSource @Inject constructor(
    private val partySearchDao: PartySearchDao
) {
    fun getPartySearchWordList(): Flow<List<PartySearchWordEntity>> =
        partySearchDao.getAll()

    suspend fun insertPartySearchWord(partySearchWordModel: PartySearchWordModel) =
        partySearchDao.insertPartySearch(
            partySearchWordEntity = PartySearchWordEntity(
                partySearchWordModel.word,
                partySearchWordModel.searchAt
            )
        )

    suspend fun deletePartySearchWord(partySearchWordModel: PartySearchWordModel) =
        partySearchDao.deletePartySearch(
            partySearchWordEntity = PartySearchWordEntity(
                partySearchWordModel.word,
                partySearchWordModel.searchAt
            )
        )

    suspend fun deletePartySearchWordList() = partySearchDao.deleteAll()
}
