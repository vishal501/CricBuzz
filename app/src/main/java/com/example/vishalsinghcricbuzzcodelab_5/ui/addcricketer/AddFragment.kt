package com.example.vishalsinghcricbuzzcodelab_5.ui.addcricketer

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.codelab5_crickbuzz.ui.addplayer.AddViewModel
import com.example.codelab5_crickbuzz.ui.addplayer.AddViewModelFactory
import com.example.vishalsinghcricbuzzcodelab_5.R
import com.example.vishalsinghcricbuzzcodelab_5.databinding.FragmentAddBinding
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.db.PlayerDataBase
import com.example.vishalsinghcricbuzzcodelab_5.repository.PlayerRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_add.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class AddFragment : Fragment() {
    private lateinit var addViewModel: AddViewModel
    private lateinit var playerRepository: PlayerRepository
    private lateinit var addBinding: FragmentAddBinding
    private lateinit var bmp: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        requireActivity().app_bar_main.bt_add.visibility = View.INVISIBLE
        addBinding = FragmentAddBinding.inflate(inflater, container, false)
        val view = addBinding.root
        playerRepository = PlayerRepository(PlayerDataBase.getDatabase(requireActivity().applicationContext))
        addViewModel = ViewModelProvider(this, AddViewModelFactory(playerRepository)).get(
            AddViewModel::class.java)



        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBinding.etPlayerdob.setOnClickListener{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                val f: NumberFormat = DecimalFormat("00")
                if(c.get(Calendar.YEAR) - year >= 18) {
                    addBinding.etPlayerdob.text = f.format(day)+"/"+f.format(month)+"/"+year.toString()

                } else {
                    Toast.makeText(requireActivity(),"Not Allowed",Toast.LENGTH_SHORT).show()
                }


            }, year, month, day)



            dpd.show()
        }

        addBinding.ivImage.setOnClickListener {

            if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            {
                openCamera()
            }
            else
            {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(android.Manifest.permission.CAMERA),1)

                Log.e("camera","Denied")
            }

        }

        addBinding.btSave.setOnClickListener{

            var result = true
            result = result && addViewModel.checkName(addBinding.etName.text.toString().trim())
            result = result && addViewModel.checkName(addBinding.etTeam.text.toString().trim())
            result = result && addViewModel.checkName(addBinding.etCountry.text.toString().trim())
            result = result && addViewModel.checkName(addBinding.etMatches.text.toString().trim())
            result = result && addViewModel.checkName(addBinding.etRun.text.toString().trim())
            result = result && addViewModel.checkName(addBinding.etWicket.text.toString().trim())
            result = result && addViewModel.checkAge(addBinding.etPlayerdob.text.toString().trim())
            result = result && addViewModel.checkRadio(addBinding.rbBatsman.isChecked,addBinding.rbBowler.isChecked)

            if(addBinding.etName.text.isNotEmpty() &&
            addBinding.etTeam.text.isNotEmpty() &&
            addBinding.etCountry.text.isNotEmpty() &&
            addBinding.etMatches.text.isNotEmpty() &&
            addBinding.etRun.text.isNotEmpty() &&
            addBinding.etWicket.text.isNotEmpty() &&
            addBinding.etPlayerdob.text.isNotEmpty()){
                Toast.makeText(requireActivity(), "Saved", Toast.LENGTH_SHORT)
                    .show()
            }
                 if(addBinding.etName.text.isNullOrBlank()){
                    Toast.makeText(requireActivity(), "Fill Correctly", Toast.LENGTH_SHORT)
                        .show()
                }
                else if(addBinding.etTeam.text.isNullOrBlank()){
                    Toast.makeText(requireActivity(), "Fill Team", Toast.LENGTH_SHORT)
                        .show()
                }
                else if(addBinding.etCountry.text.isNullOrBlank()){
                    Toast.makeText(requireActivity(), "Fill Country", Toast.LENGTH_SHORT)
                        .show()
                }
                else if(addBinding.etMatches.text.isNullOrBlank()){
                    Toast.makeText(requireActivity(), "Fill Matches", Toast.LENGTH_SHORT)
                        .show()
                }
                else if(addBinding.etRun.text.isNullOrBlank()){
                    Toast.makeText(requireActivity(), "Fill Run", Toast.LENGTH_SHORT)
                        .show()
                }
                else if(addBinding.etWicket.text.isNullOrBlank()){
                    Toast.makeText(requireActivity(), "Fill Wicket", Toast.LENGTH_SHORT)
                        .show()
                }
                else if(addBinding.etPlayerdob.text.isNullOrBlank()){
                    Toast.makeText(requireActivity(), "Fill DOB", Toast.LENGTH_SHORT)
                        .show()
                }
            else{
                resultTrue(result)
                 }







            try {
                bmp.density
                bmp.byteCount
                result = true
            }
            catch (e:Exception)
            {
                result = false
            }





//            else
//            {
//
////                resultFalse(result)
//            }

        }
    }

    private fun resultTrue(result: Boolean){
        if(result == true)
        {
            addViewModel.addPlayer(
                CricketPlayer(
                    null,
                    bmp,
                    addBinding.etName.text.toString().trim(),
                    addBinding.etTeam.text.toString().trim(),
                    addBinding.etCountry.text.toString().trim(),
                    addBinding.etPlayerdob.text.toString().trim(),
                    addBinding.rbBatsman.isChecked,
                    addBinding.rbBowler.isChecked,
                    addBinding.etMatches.text.trim().length,
                    addBinding.etRun.text.trim().length,
                    addBinding.etWicket.text.trim().length,
                    false
                )
            )
            parentFragment?.findNavController()?.navigate(R.id.action_addFragment_to_nav_home)
        }
    }

    private fun openCamera() {
        val intent= Intent()
        intent.action= MediaStore.ACTION_IMAGE_CAPTURE
        startActivityForResult(intent,1)
    }



    private fun resultFalse(result: Boolean) {
        if(result == false)
        {
            Toast.makeText(requireContext(),"All fields are mandatory", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1)
        {
            bmp=data?.extras?.get("data") as Bitmap
            addBinding.ivImage.setImageBitmap(bmp)
        }
    }
}