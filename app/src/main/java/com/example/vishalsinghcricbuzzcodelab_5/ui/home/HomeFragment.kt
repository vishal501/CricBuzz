package com.example.vishalsinghcricbuzzcodelab_5.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab5_crickbuzz.adapter.HomeRecyclerViewAdapter
import com.example.vishalsinghcricbuzzcodelab_5.R
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentHomeBinding
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayerData
import com.example.vishalsinghcricbuzzcodelab_5.db.PlayerDataBase
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository
import com.example.vishalsinghcricbuzzcodelab_5.ui.ItemClicked.Clicked
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment() : Fragment(),Clicked {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var playerRepository: PlayerRepository
//    private lateinit var adapter1: HomeRecyclerViewAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        playerRepository = PlayerRepository(PlayerDataBase.getDatabase(requireActivity().applicationContext))
        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(playerRepository)).get(HomeViewModel::class.java)

        homeViewModel.list.observe(requireActivity(), Observer {
            binding.homeRv.layoutManager = LinearLayoutManager(requireContext())
            val adapter = HomeRecyclerViewAdapter(it,this)
            val list = ArrayList<CricketPlayer>()
            it.forEach {
                list.add(it)
            }
            if (list.size > 0) {
                if (isAdded) {
//                    val adapter = HomeRecyclerViewAdapter(list, this)
                    binding.homeRv.adapter = adapter
                    binding.noData.visibility = View.INVISIBLE
                }
            } else {
                binding.noData.visibility = View.VISIBLE
            }


//            binding.noData.visibility = View.INVISIBLE
            adapter.notifyDataSetChanged()
            binding.homeRv.adapter = adapter
        })


        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.svSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("search", "callback")
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                if (p0!!.length >= 3) {
                    homeViewModel.list.observe(requireActivity()) {
                        val list = ArrayList<CricketPlayer>()

                        it.forEach {
                            if (it.name!!.contains(p0)) {
                                list.add(it)
                                if (list.size > 0) {
                                    if (isAdded) {
                                        val adapter = HomeRecyclerViewAdapter(list, this@HomeFragment)
                                        binding.homeRv.adapter = adapter
                                        binding.noData.visibility = View.INVISIBLE
                                    }
                                }
                                else if(list.isEmpty()){
                                    binding.noData.visibility = View.VISIBLE
                                }
                            }

                        }
                    }
                }
                else{

                    homeViewModel.list.observe(requireActivity()) {
                        val list = ArrayList<CricketPlayer>()

                        it.forEach {
                            if (it.name!!.contains(p0)) {
                                list.add(it)
                                if (list.size > 0) {
                                    if (isAdded) {
                                        val adapter = HomeRecyclerViewAdapter(list, this@HomeFragment)
                                        binding.homeRv.adapter = adapter
                                        binding.noData.visibility = View.INVISIBLE
                                    }
                                }
                                else if(list.isEmpty()){
                                    binding.noData.visibility = View.VISIBLE
                                }
                            }

                        }
                    }

                }
//


                return false

            }
        })
    }

    override fun onResume() {
        super.onResume()
        requireActivity().app_bar_main.bt_add.visibility = View.VISIBLE
    }

    override fun withData(cricketPlayerData: CricketPlayerData)  {
        parentFragment?.findNavController()?.navigate(R.id.action_nav_home_to_playersDetailsFragment)
        dataCricket = cricketPlayerData

    }
    companion object{
        lateinit var dataCricket:CricketPlayerData
    }
}