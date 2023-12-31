package com.cmc.curtaincall.data.source.remote

import android.content.Context
import android.net.Uri
import com.cmc.curtaincall.common.utility.extensions.getAbsolutePath
import com.cmc.curtaincall.core.network.service.image.ImageService
import com.cmc.curtaincall.core.network.service.image.response.SaveImageResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.InputStream
import javax.inject.Inject

class ImageRemoteSource @Inject constructor(
    private val imageService: ImageService,
    @ApplicationContext private val context: Context
) {

    fun uploadGalleryImage(
        inputStream: InputStream
    ): Flow<SaveImageResponse> = flow {
        val body = inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            "${System.currentTimeMillis()}_image.jpg",
            body
        )
        emit(imageService.uploadImage(multipartBody))
    }

    fun saveImage(
        image: String
    ): Flow<SaveImageResponse> = flow {
        val uri = Uri.parse(image)
        val imageFile = File(uri.getAbsolutePath(context))
        val imageRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val imageMultipartBody = MultipartBody.Part.createFormData(
            "image",
            imageFile.name,
            imageRequestBody
        )
        emit(imageService.uploadImage(imageMultipartBody))
    }
}
