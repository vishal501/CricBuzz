package com.example.vishalsinghcricbuzzcodelab_5.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentInfoBinding
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeFragment

class InfoFragment : Fragment() {
    private lateinit var infoBinding: FragmentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoBinding = FragmentInfoBinding.inflate(inflater,container,false)
        val view = infoBinding.root

        infoBinding.ivImage.setImageBitmap(HomeFragment.dataCricket.photo)
        infoBinding.tvName.text = HomeFragment.dataCricket.name
        infoBinding.tvPteam.text = HomeFragment.dataCricket.team
        infoBinding.tvPcountry.text = HomeFragment.dataCricket.country
        infoBinding.tvBornp.text = HomeFragment.dataCricket.dob
        if(HomeFragment.dataCricket.batter)
            infoBinding.tvPstyle.text = "Batsman"
        else
            infoBinding.tvPstyle.text = "Bowler"
        return view
    }


}