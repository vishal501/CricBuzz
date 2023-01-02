package com.example.vishalsinghcricbuzzcodelab_5.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val playerRepository: PlayerRepository) : ViewModel() {


    val list: LiveData<List<CricketPlayer>> = playerRepository.allCricketPlayer
    val listFav: LiveData<List<CricketPlayer>> = playerRepository.fav

    fun update(cricketPlayer: CricketPlayer)
    {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.update(cricketPlayer)
        }
    }
}