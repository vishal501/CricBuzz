package com.example.codelab5_crickbuzz.ui.addplayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository

class AddViewModelFactory (private val playerRepository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddViewModel(playerRepository) as T
    }
}