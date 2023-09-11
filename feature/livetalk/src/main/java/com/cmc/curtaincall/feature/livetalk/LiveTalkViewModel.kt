package com.cmc.curtaincall.feature.livetalk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.model.show.LiveTalkShowModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class LiveTalkViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _liveTalkShows = MutableStateFlow<List<LiveTalkShowModel>>(listOf())
    val liveTalkShows = _liveTalkShows.asStateFlow()

    private val _userState = MutableStateFlow(User())
    val userState = _userState.asStateFlow()

    init {
        getMemberInfo()
        requestLiveTalkShow()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getMemberInfo() {
        memberRepository.getMemberId().flatMapLatest {
            memberRepository.requestMemberInfo(it)
        }.onEach {
            _userState.value = User(
                id = it.id.toString(),
                name = it.nickname,
                image = it.imageUrl.toString()
            )
        }.launchIn(viewModelScope)
    }

    private fun requestLiveTalkShow() {
        showRepository.requestLiveTalkShowList(null, null, SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(Calendar.getInstance().time))
            .onEach { _liveTalkShows.value = it }
            .launchIn(viewModelScope)
    }
}
