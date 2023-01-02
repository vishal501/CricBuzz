package com.example.vishalsinghcricbuzzcodelab_5.repository

import androidx.lifecycle.LiveData
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.db.PlayerDataBase

class PlayerRepository(private val playerDataBase: PlayerDataBase)
{

    //  val bowler = MutableLiveData<List<Player>>()
    val allCricketPlayer : LiveData<List<CricketPlayer>>
        get() {
            return playerDataBase.playerDao().getAll()
        }

    val bowler : LiveData<List<CricketPlayer>>
        get() {
            return playerDataBase.playerDao().onlyBowler()
        }

    val batsman : LiveData<List<CricketPlayer>>
        get() {
            return playerDataBase.playerDao().onlyBatter()
        }

    val fav : LiveData<List<CricketPlayer>>
        get() {
            return playerDataBase.playerDao().onlyFavourite()
        }

    fun addPlayer(cricketPlayer: CricketPlayer)
    {
        playerDataBase.playerDao().add(cricketPlayer)
    }

    fun update(cricketPlayer: CricketPlayer)
    {
        playerDataBase.playerDao().updatePlayer(cricketPlayer)
    }
//    fun onlyFav()
//    {
//
//    }


}