package com.cmc.curtaincall.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class RootViewModel<SE : BaseSideEffect> : ViewModel() {
    private val _effects = Channel<SE>(Channel.UNLIMITED)
    val effects: Flow<SE> = _effects.receiveAsFlow()

    protected fun sendSideEffect(sideEffect: SE) {
        viewModelScope.launch {
            _effects.send(sideEffect)
        }
    }

    override fun onCleared() {
        super.onCleared()
        _effects.close()
    }
}
