package com.example.vishalsinghcricbuzzcodelab_5.ui.bowler

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.example.codelab5_crickbuzz.ui.bowler.BowlerViewModel
import com.example.codelab5_crickbuzz.ui.bowler.BowlerViewModelFactory
import com.example.vishalsinghcricbuzzcodelab_5.R
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentBowlerBinding
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayerData
import com.example.vishalsinghcricbuzzcodelab_5.db.PlayerDataBase
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository
import com.example.vishalsinghcricbuzzcodelab_5.ui.ItemClicked.Clicked
import com.example.vishalsinghcricbuzzcodelab_5.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*


class BowlerFragment : Fragment(), Clicked{
    private lateinit var binding: FragmentBowlerBinding
    private lateinit var bowlerViewModel: BowlerViewModel
    private lateinit var playerRepository: PlayerRepository

    //private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().app_bar_main.bt_add.visibility = View.INVISIBLE

        binding = FragmentBowlerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        playerRepository = PlayerRepository(PlayerDataBase.getDatabase(requireActivity().applicationContext))
        bowlerViewModel = ViewModelProvider(requireActivity(), BowlerViewModelFactory(playerRepository)).get(BowlerViewModel::class.java)

//        val textView: TextView = binding.textSlideshow
//        slideshowViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        bowlerViewModel.list.observe(requireActivity(), Observer {
            binding.bowlerRv.layoutManager = LinearLayoutManager(requireContext())
//            val adapter = MyRecyclerViewAdapter(it)
            val adapter = HomeRecyclerViewAdapter(it,this)
            Log.e("bowler",it.toString())
            adapter.notifyDataSetChanged()
            binding.bowlerRv.adapter = adapter
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // _binding = null
    }

    override fun withData(cricketPlayerData: CricketPlayerData) {
        HomeFragment.dataCricket = cricketPlayerData
        parentFragment?.findNavController()?.navigate(R.id.action_nav_bowler_to_playersDetailsFragment)

    }


}