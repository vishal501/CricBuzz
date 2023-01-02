package com.example.vishalsinghcricbuzzcodelab_5.ui.bowlingInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentBowlingInfoBinding
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeFragment


class BowlingInfoFragment : Fragment() {
    private lateinit var bowlingInfoBinding: FragmentBowlingInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bowlingInfoBinding = FragmentBowlingInfoBinding.inflate(inflater,container,false)
        val view = bowlingInfoBinding.root

        bowlingInfoBinding.tvPmatch.text = HomeFragment.dataCricket.match.toString()
        bowlingInfoBinding.tvPwicket.text = HomeFragment.dataCricket.wicket.toString()
        bowlingInfoBinding.tvPteam.text = HomeFragment.dataCricket.team.toString()
        // return inflater.inflate(R.layout.fragment_bowling_info, container, false)
        return view
    }


}