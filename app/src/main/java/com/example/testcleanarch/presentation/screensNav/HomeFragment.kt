package com.example.testcleanarch.presentation.screensNav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.adapter.AdapterFlashSale
import com.example.testcleanarch.adapter.AdapterLatest
import com.example.testcleanarch.databinding.FragmentHomeBinding
import com.example.testcleanarch.domain.models.FlashSaleItem
import com.example.testcleanarch.domain.models.LatestItem
import com.example.testcleanarch.presentation.MainActivity
import com.example.testcleanarch.presentation.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val adapterFlashSale = AdapterFlashSale()
    private val adapterLatest = AdapterLatest()
    private val mainviewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        System.out.println("HOME FRAG")

        mainviewModel.liveBitMapforPrifilePhoto.observe(requireActivity()as MainActivity,Observer{
            if (it!=null){}
            binding.imViewProfileHome.setImageBitmap(it)
        })
        mainviewModel.liveflash.observe(requireActivity() as MainActivity, Observer {
        mainviewModel.livelatest.observe(requireActivity() as MainActivity, Observer {
            init(mainviewModel.liveflash.value?.flashSale!!,mainviewModel.livelatest.value?.latest!!)
        })
        binding.btnNavigateToProfile.setOnClickListener {
        val navController = Navigation.findNavController(requireActivity() as MainActivity,R.id.nav_host)
            navController.navigate(R.id.nav_profile)
        }


        })
    }

    private fun init(flashSale: ArrayList<FlashSaleItem>,latest:ArrayList<LatestItem> ) {
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
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}