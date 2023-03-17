package com.example.testcleanarch.presentation.screensNav.touchitem

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.adapter.AdapterColorsItemTouch
import com.example.testcleanarch.adapter.AdapterPhotosItemTouch
import com.example.testcleanarch.adapter.CallbackForPicassoSetBackGroundLayout
import com.example.testcleanarch.databinding.FragmentItemTouchBinding
import com.example.testcleanarch.presentation.MainActivity
import com.example.testcleanarch.presentation.MainViewModel
import com.squareup.picasso.Picasso


class ItemTouchFragment : Fragment() {
    private val adaptercolors = AdapterColorsItemTouch()
    private val adapterphotoes = AdapterPhotosItemTouch()
    lateinit var binding:FragmentItemTouchBinding
    private val mainviewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    fun init(photoes:ArrayList<String>,colors:ArrayList<String>){
        binding.apply {
            System.out.println(mainviewModel.liveGetItemOnTouch.value?.imageUrls)
            var grid1 = GridLayoutManager(activity, 1, RecyclerView.HORIZONTAL, false)
            var grid = GridLayoutManager(activity, 1, RecyclerView.HORIZONTAL, false)
            rvColorsitem.layoutManager = grid1
            rvImagesitems.layoutManager = grid
            adapterphotoes.clear()
            adaptercolors.clear()
            adapterphotoes.addAll(photoes)
            adaptercolors.addAll(colors)
            rvImagesitems.adapter = adapterphotoes
            rvColorsitem.adapter = adaptercolors
        }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainviewModel.getItemOntouch()
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.nav_home)
        }
        mainviewModel.liveGetItemOnTouch.observe(requireActivity() as MainActivity,Observer{
            binding.descriptionitemtouch.text = it.description
            binding.nametouchitem.text = it.name
            binding.raitingitem.text = "("+it.rating.toString()+" reviews)"
            binding.reviewsitem.text = it.numberOfReviews.toString()
            binding.imviewFragMainPhoto
            val cal = CallbackForPicassoSetBackGroundLayout(binding.constrImMainPhoto,binding.imviewFragMainPhoto)
            var mainphoto = arguments?.get("mainphoto") as String
            Picasso.get().load(mainphoto)
                .into(binding.imviewFragMainPhoto,cal)
            binding.imviewFragMainPhoto.visibility = View.INVISIBLE
            init(it.imageUrls,it.colors)


        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemTouchBinding.inflate(inflater)
        return binding.root
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemTouchFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}