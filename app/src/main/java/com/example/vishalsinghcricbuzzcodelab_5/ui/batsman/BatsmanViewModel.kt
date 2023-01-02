package com.example.codelab5_crickbuzz.ui.batsman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository



class BatsmanViewModel(private val playerRepository: PlayerRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val list: LiveData<List<CricketPlayer>> = playerRepository.batsman
}