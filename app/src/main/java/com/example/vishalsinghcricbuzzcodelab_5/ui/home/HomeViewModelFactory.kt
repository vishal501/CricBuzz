package com.example.vishalsinghcricbuzzcodelab_5.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository


class HomeViewModelFactory(private val playerRepository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(playerRepository) as T
    }
}