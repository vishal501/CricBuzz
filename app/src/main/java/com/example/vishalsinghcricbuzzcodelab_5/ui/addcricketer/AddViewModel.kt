package com.example.codelab5_crickbuzz.ui.addplayer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddViewModel(private val playerRepository: PlayerRepository
) : ViewModel()
{

    fun addPlayer(cricketPlayer: CricketPlayer)
    {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.addPlayer(cricketPlayer)
        }
    }

    fun checkAge(dob: String) : Boolean {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        Log.e("current year",year.toString())
        if(dob.length<8)
            return false
        when(dob.length)
        {
            0 -> return false
            else ->
            {
                var ply =0
                    ply=dob.subSequence(6,10).toString().toInt()

                Log.e("age","$ply")
                if(year - ply > 18 )
                {
                    Log.e("age","$ply")
                    return true

                }
                else
                {
                    return false
                }
            }
        }

    }


    fun checkRadio(r1:Boolean, r2:Boolean) : Boolean
    {
        Log.e(r1.toString(),r2.toString())
        return r1 || r2
    }

    fun checkName(name: String) : Boolean
    {
        if(name.isEmpty() == true)
        {
            Log.e("name","empty")
            return false
        }
        else
            return true
    }
}