package com.cmc.curtaincall.feature.partymember.ui.livetalk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.repository.ChattingRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PartyMemberLiveTalkViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val chattingRepository: ChattingRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<User>(User())
    val userState = _userState.asStateFlow()

    private val _tokenState = MutableStateFlow("")
    val tokenState = _tokenState.asStateFlow()

    init {
        getMemberId()
        getChattingToken()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMemberId() {
        memberRepository.getMemberId()
            .flatMapLatest {
                memberRepository.requestMemberInfo(it)
            }
            .onEach {
                _userState.value = User(
                    id = it.id.toString(),
                    name = it.nickname,
                    image = it.imageUrl.toString()
                )
            }.launchIn(viewModelScope)
    }

    private fun getChattingToken() {
        chattingRepository.requestChattingToken()
            .onEach {
                _tokenState.value = it.value
            }.launchIn(viewModelScope)
    }
}
