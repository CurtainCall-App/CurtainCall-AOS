package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.image.SaveImageModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    fun saveImage(
        image: String
    ): Flow<SaveImageModel>
}
