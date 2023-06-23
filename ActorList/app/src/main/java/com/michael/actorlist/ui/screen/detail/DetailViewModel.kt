package com.michael.actorlist.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.actorlist.data.ActorRepository
import com.michael.actorlist.model.Actor
import com.michael.actorlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ActorRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Actor>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Actor>>
        get() = _uiState

    fun getActorById(actorId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getActorById(actorId))
        }
    }
}