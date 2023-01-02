package com.example.vishalsinghcricbuzzcodelab_5.ui.battinginfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentBattingInfoBinding
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeFragment



class BattingInfoFragment : Fragment() {
    private lateinit var battingInfoBinding: FragmentBattingInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        battingInfoBinding = FragmentBattingInfoBinding.inflate(inflater,container,false)
        val view = battingInfoBinding.root
        // return inflater.inflate(R.layout.fragment_batting_info, container, false)

        battingInfoBinding.tvPmatch.text = HomeFragment.dataCricket.match.toString()
        battingInfoBinding.tvPrun.text = HomeFragment.dataCricket.run.toString()
        battingInfoBinding.tvPteam.text = HomeFragment.dataCricket.team.toString()

        return view
    }

    
}