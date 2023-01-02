package com.example.codelab5_crickbuzz.ui.bowler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository


class BowlerViewModelFactory (private val playerRepository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BowlerViewModel(playerRepository) as T
    }
}