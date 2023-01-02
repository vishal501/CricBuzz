package com.example.codelab5_crickbuzz.ui.bowler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository


class BowlerViewModel(private val playerRepository: PlayerRepository) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is slideshow Fragment"
//    }
    lateinit var cricketPlayerList : MutableLiveData<List<CricketPlayer>>
    val list: LiveData<List<CricketPlayer>>
    get() = playerRepository.bowler

//    private fun onlyBowler()  {
//        viewModelScope.launch(Dispatchers.IO) {
//            playerList.postValue(playerRepository.bowlers())
//        }
//    }
}