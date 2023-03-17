package com.example.testcleanarch.presentation.screensNav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.adapter.AdapterFlashSale
import com.example.testcleanarch.adapter.AdapterLatest
import com.example.testcleanarch.databinding.FragmentHomeMainBinding
import com.example.testcleanarch.domain.models.FlashSaleItem
import com.example.testcleanarch.domain.models.LatestItem
import com.example.testcleanarch.presentation.MainActivity
import com.example.testcleanarch.presentation.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeMainFragment : Fragment() {
    private val adapterFlashSale = AdapterFlashSale()
    private val adapterLatest = AdapterLatest()
    lateinit var binding:FragmentHomeMainBinding
    private val mainviewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainviewModel.liveflash.observe(requireActivity() as MainActivity, Observer {
            mainviewModel.livelatest.observe(requireActivity() as MainActivity, Observer {
                init(mainviewModel.liveflash.value?.flashSale!!,mainviewModel.livelatest.value?.latest!!)
                //binding.constviewscroll.visibility = View.VISIBLE
            })})
    }
    private fun init(flashSale: ArrayList<FlashSaleItem>, latest:ArrayList<LatestItem> ) {
        binding.apply {
            var grid = GridLayoutManager(activity,1, RecyclerView.HORIZONTAL,false)
            var grid1 = GridLayoutManager(activity,1, RecyclerView.HORIZONTAL,false)
            recyclerViewFlashSale.layoutManager = grid
            adapterFlashSale.clear()
            adapterFlashSale.addAll(flashSale)
            recyclerViewFlashSale.adapter = adapterFlashSale
            //rv latest
            recyclerViewLatest.layoutManager = grid1
            adapterLatest.clear()
            adapterLatest.addAll(latest)
            recyclerViewLatest.adapter = adapterLatest
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeMainBinding.inflate(inflater)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeMainFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}