package com.cmc.curtaincall.feature.show.lostproperty.detail

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.RootViewModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyDetailModel
import com.cmc.curtaincall.domain.repository.LostPropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShowLostPropertyDetailViewModel @Inject constructor(
    private val lostPropertyRepository: LostPropertyRepository
) : RootViewModel<Nothing>() {
    private val _lostPropertyDetailModel = MutableStateFlow(LostPropertyDetailModel())
    val lostPropertyDetailModel = _lostPropertyDetailModel.asStateFlow()

    fun requestLostDetailProperty(lostPropertyId: Int) {
        lostPropertyRepository.requestLostPropertyDetail(lostPropertyId)
            .onEach { _lostPropertyDetailModel.value = it }
            .launchIn(viewModelScope)
    }
}
