package com.example.testcleanarch.presentation.screensNav

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.FragmentProfileUserBinding
import com.example.testcleanarch.presentation.LoginActivity
import com.example.testcleanarch.presentation.MainActivity
import com.example.testcleanarch.presentation.MainViewModel
import android.widget.EditText
import androidx.lifecycle.Observer
import java.io.InputStream


class ProfileUserFragment : Fragment() {
    private val mainviewModel: MainViewModel by activityViewModels()
    private val SELECT_PICTURE = 1
    private val imageView: ImageView? = null
    private val image_uri:EditText? = null

    lateinit var binding:FragmentProfileUserBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var first = activity?.intent?.getStringExtra("firstName")
        var lastname = activity?.intent?.getStringExtra("LastName")
        binding.firstNameAndLast.text = first + " " + lastname
        mainviewModel.liveBitMapforPrifilePhoto.observe(requireActivity()as MainActivity,Observer{
            if (it!=null){
                binding.imViewProfile.setImageBitmap(it)
            }
        })
        binding.tradeStoreConstr.setOnClickListener {
            System.out.println("TRade store CLICK")
        }

        binding.logout.setOnClickListener {
            var i = Intent(activity, LoginActivity::class.java)
            startActivity(i)
        }
        binding.btnbacktohome.setOnClickListener {
            val navController =
                Navigation.findNavController(requireActivity() as MainActivity, R.id.nav_host)
            navController.navigate(R.id.nav_home)
        }
        binding.btnUploadPhoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)


        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileUserBinding.inflate(inflater)
        return binding.root
    }

    companion object {
         @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileUserFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}