package com.cmc.curtaincall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.repository.ChattingRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val chattingRepository: ChattingRepository,
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _user = MutableStateFlow(User())
    private val _token = MutableStateFlow("")

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun connectChattingClient(chatClient: ChatClient) {
        memberRepository.getMemberId()
            .flatMapLatest {
                memberRepository.requestMemberInfo(it)
            }
            .onEach {
                _user.value = User(
                    id = it.id.toString(),
                    name = it.nickname,
                    image = it.imageUrl.toString()
                )
            }.flatMapLatest {
                chattingRepository.requestChattingToken()
            }.onEach {
                _token.value = it.value
            }.onCompletion {
                chatClient.connectUser(
                    user = _user.value,
                    token = _token.value
                ).enqueue { result ->
                    _isSuccess.value = result.isSuccess
                }
            }.launchIn(viewModelScope)
    }
}
