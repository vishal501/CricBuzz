package com.example.vishalsinghcricbuzzcodelab_5.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CricketPlayerDao {

    @Insert
    fun add(cricketPlayer: CricketPlayer)

    @Update
    fun updatePlayer(cricketPlayer: CricketPlayer)

    @Query("select * from PlayersData")
    fun getAll() : LiveData<List<CricketPlayer>>

    @Query("select * from PlayersData where batter = 1")
    fun onlyBatter() : LiveData<List<CricketPlayer>>

    @Query("select * from PlayersData where bowler = 1")
    fun onlyBowler() : LiveData<List<CricketPlayer>>

    @Query("select * from PlayersData where favourite = 1")
    fun onlyFavourite() : LiveData<List<CricketPlayer>>
}