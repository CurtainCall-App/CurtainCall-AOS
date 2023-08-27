package com.cmc.curtaincall.feature.performance.lostitem.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.repository.ImageRepository
import com.cmc.curtaincall.domain.repository.LostItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PerformanceLostItemCreateViewModel @Inject constructor(
    private val lostItemRepository: LostItemRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _lostCreateInfo = MutableStateFlow<PerformanceLostItemCreateUiState>(
        PerformanceLostItemCreateUiState()
    )
    val lostCreateInfo = _lostCreateInfo.asStateFlow()

    fun setTitle(title: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            title = title
        )
    }

    fun setItemType(type: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            type = type
        )
    }

    fun setFoundPlaceDetail(foundPlaceDetail: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            foundPlaceDetail = foundPlaceDetail
        )
    }

    fun setFoundDate(foundDate: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            foundDate = foundDate
        )
    }

    fun setFoundTime(foundTime: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            foundTime = foundTime
        )
    }

    fun setParticulars(particulars: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            particulars = particulars
        )
    }

    fun setImageId(imageId: Int) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            imageId = imageId
        )
    }

    fun uploadImage(imageUrl: String) {
        imageRepository.saveImage(image = imageUrl)
            .onEach { Timber.d("uploadImage $imageUrl") }
            .launchIn(viewModelScope)
    }
}
