package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.image.SaveImageModel
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface ImageRepository {

    fun uploadGalleryImage(
        inputStream: InputStream
    ): Flow<SaveImageModel>
}
