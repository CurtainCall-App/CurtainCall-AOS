package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.ImageRemoteSource
import com.cmc.curtaincall.domain.model.image.SaveImageModel
import com.cmc.curtaincall.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.InputStream
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteSource: ImageRemoteSource
) : ImageRepository {
    override fun uploadGalleryImage(inputStream: InputStream): Flow<SaveImageModel> =
        imageRemoteSource.uploadGalleryImage(inputStream).map { it.toModel() }
}
