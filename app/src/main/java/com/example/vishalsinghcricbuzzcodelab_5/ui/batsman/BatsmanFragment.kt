package com.example.vishalsinghcricbuzzcodelab_5.ui.batsman

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
import com.example.codelab5_crickbuzz.ui.batsman.BatsmanViewModel
import com.example.codelab5_crickbuzz.ui.batsman.BatsmanViewModelFactory
import com.example.vishalsinghcricbuzzcodelab_5.R
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentBatsmanBinding
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayerData
import com.example.vishalsinghcricbuzzcodelab_5.db.PlayerDataBase
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository
import com.example.vishalsinghcricbuzzcodelab_5.ui.ItemClicked.Clicked
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeFragment.Companion.dataCricket
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*


class BatsmanFragment : Fragment(), Clicked {
    //private var _binding: FragmentGalleryBinding? = null
    private lateinit var binding: FragmentBatsmanBinding
    private lateinit var batsmanViewModel: BatsmanViewModel
    private lateinit var playerRepository: PlayerRepository

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val galleryViewModel =
//            ViewModelProvider(this).get(BatsmanViewModel::class.java)

        requireActivity().app_bar_main.bt_add.visibility = View.INVISIBLE

        binding = FragmentBatsmanBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        playerRepository = PlayerRepository(PlayerDataBase.getDatabase(requireActivity().applicationContext))
        batsmanViewModel = ViewModelProvider(requireActivity(), BatsmanViewModelFactory(playerRepository)).get(BatsmanViewModel::class.java)

        batsmanViewModel.list.observe(requireActivity(), Observer {
            binding.batsmanRv.layoutManager = LinearLayoutManager(context)
//            val adapter = MyRecyclerViewAdapter(it)
            val adapter = HomeRecyclerViewAdapter(it,this)
            adapter.notifyDataSetChanged()
            binding.batsmanRv.adapter = adapter
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // _binding = null
    }

    override fun withData(cricketPlayerData: CricketPlayerData) {
        dataCricket = cricketPlayerData
        parentFragment?.findNavController()?.navigate(R.id.action_nav_batsman_to_playersDetailsFragment)

    }


//    companion object{
//        lateinit var dataCricket:CricketPlayerData
//    }
}