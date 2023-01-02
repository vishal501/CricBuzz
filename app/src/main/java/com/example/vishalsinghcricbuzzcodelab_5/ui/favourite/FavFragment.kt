package com.example.vishalsinghcricbuzzcodelab_5.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab5_crickbuzz.adapter.HomeRecyclerViewAdapter
import com.example.vishalsinghcricbuzzcodelab_5.R
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentFavouriteBinding
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayerData
import com.example.vishalsinghcricbuzzcodelab_5.db.PlayerDataBase
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository
import com.example.vishalsinghcricbuzzcodelab_5.ui.ItemClicked.Clicked
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeFragment
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeViewModel
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*

class favouriteFragment : Fragment(), Clicked {

    private lateinit var favouriteBinding: FragmentFavouriteBinding
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
        requireActivity().app_bar_main.bt_add.visibility = View.INVISIBLE
        favouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val view = favouriteBinding.root
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        playerRepository = PlayerRepository(PlayerDataBase.getDatabase(requireActivity().applicationContext))
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(playerRepository)).get(
            HomeViewModel::class.java)

        homeViewModel.listFav.observe(requireActivity(), Observer {
            favouriteBinding.favRv.layoutManager = LinearLayoutManager(context)
//            val adapter = MyRecyclerViewAdapter(it)
            val adapter = HomeRecyclerViewAdapter(it,this)
            adapter.notifyDataSetChanged()
            favouriteBinding.favRv.adapter = adapter
        })

        return view
        //return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun withData(cricketPlayerData: CricketPlayerData) {
        HomeFragment.dataCricket = cricketPlayerData
        parentFragment?.findNavController()?.navigate(R.id.action_nav_favourite_to_playersDetailsFragment)

    }




}