package com.katempoy.dapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.katempoy.dapp.models.User
import com.katempoy.dapp.state.XoStateRepository

class GameViewModel(initUrl: String) : ViewModel() {
    private var stateRepository: XoStateRepository = XoStateRepository(initUrl)
    var games: LiveData<List<User>> = Transformations.switchMap(stateRepository.games) { input ->
        var m = MutableLiveData<List<User>>()
        m.value = input
        m
    }

    init {
        stateRepository.getState(true, initUrl)
    }

    fun loadGames(update: Boolean, url: String) {
        stateRepository.getState(update, url)
    }
}
