package com.cmc.curtaincall.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<STATE : BaseState, EVENT : BaseEvent, SE : BaseSideEffect>(
    initialState: STATE
) : ViewModel() {

    private val _effects = Channel<SE>(Channel.UNLIMITED)
    val effects: Flow<SE> = _effects.receiveAsFlow()

    private val events = Channel<EVENT>(Channel.UNLIMITED)
    val uiState: StateFlow<STATE> = events.receiveAsFlow()
        .runningFold(initialState, ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialState)

    protected abstract fun reduceState(currentState: STATE, event: EVENT): STATE

    override fun onCleared() {
        super.onCleared()
        _effects.close()
        events.close()
    }
}
