package com.katempoy.dapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.katempoy.dapp.models.User
import com.katempoy.dapp.state.XoStateRepository


class GameBoardViewModel(url: String) : ViewModel() {
    private var stateRepository: XoStateRepository = XoStateRepository(url)
    var game: LiveData<User> = Transformations.switchMap(stateRepository.gameFocus) { input ->
        var m = MutableLiveData<User>()
        m.value = input
        m
    }

    init { }

    fun loadGame(name: String, url: String) {
        stateRepository.getGameState(name, url)
        game = stateRepository.gameFocus
    }
}
