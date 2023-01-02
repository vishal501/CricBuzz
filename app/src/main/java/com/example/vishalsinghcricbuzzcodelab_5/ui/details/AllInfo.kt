package com.example.vishalsinghcricbuzzcodelab_5.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.codelab5_crickbuzz.adapter.ViewPagerAdapter
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentPlayersDetailsBinding
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.db.PlayerDataBase
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository
import com.example.vishalsinghcricbuzzcodelab_5.ui.battinginfo.BattingInfoFragment
import com.example.vishalsinghcricbuzzcodelab_5.ui.bowlingInfo.BowlingInfoFragment
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeFragment
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeViewModel
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeViewModelFactory
import com.example.vishalsinghcricbuzzcodelab_5.ui.info.InfoFragment
import com.google.android.material.tabs.TabLayoutMediator


class PlayersDetailsFragment : Fragment() {
    private lateinit var playersDetailsBinding: FragmentPlayersDetailsBinding
    private lateinit var playerRepository: PlayerRepository
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        playersDetailsBinding = FragmentPlayersDetailsBinding.inflate(inflater,container,false)
        val view = playersDetailsBinding.root
        // return inflater.inflate(R.layout.fragment_players_details, container, false)

        val adapter = ViewPagerAdapter(requireActivity()?.supportFragmentManager, lifecycle)
        adapter.addFragment(InfoFragment())
        adapter.addFragment(BattingInfoFragment())
        adapter.addFragment(BowlingInfoFragment())
        playersDetailsBinding.viewpager.adapter = adapter

        val al=ArrayList<String>()
        al.add("Info")
        al.add(("Batting"))
        al.add("Bowling")



        TabLayoutMediator(playersDetailsBinding.tablayout, playersDetailsBinding.viewpager) { tab, position ->
            tab.text= al[position]
        }.attach()

        playerRepository = PlayerRepository(PlayerDataBase.getDatabase(requireActivity().applicationContext))
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(playerRepository)).get(
            HomeViewModel::class.java)

        if(!HomeFragment.dataCricket.favourite)
        {
            playersDetailsBinding.tvFav.visibility = View.VISIBLE
            playersDetailsBinding.tvRedfav.visibility = View.GONE
        }
        else
        {
            playersDetailsBinding.tvFav.visibility = View.GONE
            playersDetailsBinding.tvRedfav.visibility = View.VISIBLE
        }





        playersDetailsBinding.tvFav.setOnClickListener {


            playersDetailsBinding.tvFav.visibility = View.GONE
            playersDetailsBinding.tvRedfav.visibility = View.VISIBLE
            HomeFragment.dataCricket.favourite = true
            homeViewModel.update(
                CricketPlayer(
                    HomeFragment.dataCricket.id,
                    HomeFragment.dataCricket.photo,
                    HomeFragment.dataCricket.name,
                    HomeFragment.dataCricket.team,
                    HomeFragment.dataCricket.country,
                    HomeFragment.dataCricket.dob,
                    HomeFragment.dataCricket.batter,
                    HomeFragment.dataCricket.bowler,
                    HomeFragment.dataCricket.match,
                    HomeFragment.dataCricket.run,
                    HomeFragment.dataCricket.wicket,
                    true
                )
            )


        }
        playersDetailsBinding.tvRedfav.setOnClickListener {
            playersDetailsBinding.tvRedfav.visibility = View.GONE
            playersDetailsBinding.tvFav.visibility = View.VISIBLE

            HomeFragment.dataCricket.favourite = false
            homeViewModel.update(
                CricketPlayer(
                    HomeFragment.dataCricket.id,
                    HomeFragment.dataCricket.photo,
                    HomeFragment.dataCricket.name,
                    HomeFragment.dataCricket.team,
                    HomeFragment.dataCricket.country,
                    HomeFragment.dataCricket.dob,
                    HomeFragment.dataCricket.batter,
                    HomeFragment.dataCricket.bowler,
                    HomeFragment.dataCricket.match,
                    HomeFragment.dataCricket.run,
                    HomeFragment.dataCricket.wicket,
                    false
                )
            )
        }






        return view
    }


}