package com.michael.actorlist.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.actorlist.data.ActorRepository
import com.michael.actorlist.model.Actor
import com.michael.actorlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ActorRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Actor>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Actor>>>
        get() = _uiState

    fun getAllActors() {
        viewModelScope.launch {
            repository.getAllActors()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { actors ->
                    _uiState.value = UiState.Success(actors)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String){
        _query.value = newQuery
        viewModelScope.launch {
            repository.getActorByName(newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{actors ->
                    _uiState.value = UiState.Success(actors)
                }
        }
    }
}