package com.cmc.curtaincall.feature.mypage.editprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.repository.ImageRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.InputStream
import javax.inject.Inject

enum class CheckState {
    None, Validate, Duplicate
}

@HiltViewModel
class MyPageProfileEditViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _validateState = MutableStateFlow(CheckState.None)
    val validateState: StateFlow<CheckState> = _validateState.asStateFlow()

    private val _imageIdState = MutableStateFlow<Int?>(null)
    val imageIdState: StateFlow<Int?> = _imageIdState.asStateFlow()

    private val _nicknameState = MutableStateFlow("")
    val nicknameState: StateFlow<String> = _nicknameState.asStateFlow()

    private val _completeUpdate = MutableStateFlow(false)
    val completeUpdate: StateFlow<Boolean> = _completeUpdate.asStateFlow()

    fun uploadImage(inputStream: InputStream) {
        imageRepository.uploadGalleryImage(inputStream)
            .onEach { _imageIdState.value = it.id }
            .launchIn(viewModelScope)
    }

    fun setNickname(nickname: String) {
        _nicknameState.value = nickname
    }

    fun changeValidateState(checkState: CheckState) {
        _validateState.value = checkState
    }

    fun checkDuplicateNickname(nickname: String) {
        memberRepository.checkDuplicateNickname(nickname)
            .onEach { check ->
                _validateState.value = if (check) CheckState.Duplicate else CheckState.Validate
            }.launchIn(viewModelScope)
    }

    fun updateMember() {
        memberRepository.updateMember(
            nickname = nicknameState.value,
            imageId = imageIdState.value
        ).onEach { check ->
            _completeUpdate.value = check
        }.launchIn(viewModelScope)
    }
}
