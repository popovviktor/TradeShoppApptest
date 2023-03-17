package com.example.testcleanarch.presentation.screensNav

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.adapter.AdapterFlashSale
import com.example.testcleanarch.adapter.AdapterLatest
import com.example.testcleanarch.adapter.AdapterSearch
import com.example.testcleanarch.databinding.FragmentHomeBinding
import com.example.testcleanarch.domain.models.FlashSaleItem
import com.example.testcleanarch.domain.models.LatestItem
import com.example.testcleanarch.presentation.MainActivity
import com.example.testcleanarch.presentation.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule



class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val adapterFlashSale = AdapterFlashSale()
    private val adapterLatest = AdapterLatest()
    private val mainviewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        System.out.println("HOME FRAG")
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frameMainHome,HomeMainFragment())?.commit()
        //binding.constviewscroll.visibility = View.INVISIBLE
        mainviewModel.liveBitMapforPrifilePhoto.observe(requireActivity()as MainActivity,Observer{
            if (it!=null){}
            binding.imViewProfileHome.setImageBitmap(it)
        })
        binding.btnNavigateToProfile.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity() as MainActivity,R.id.nav_host)
            navController.navigate(R.id.nav_profile)
        }
        binding.imCloseSearch.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frameMainHome,HomeMainFragment())?.commit()
            binding.imCloseSearch.visibility =View.INVISIBLE
            }




            GlobalScope.launch {
                binding.editSearchText.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    }

                    override fun afterTextChanged(s: Editable?) {


                        Timer().schedule(1000){

                            if (binding.editSearchText.text.length>0){
                                GlobalScope.launch(Dispatchers.Main) {
                                    binding.imCloseSearch.visibility = View.VISIBLE
                                    mainviewModel.setSearch(s.toString())
                            }}
                            if (binding.editSearchText.text.length == 0){
                                    GlobalScope.launch (Dispatchers.Main){
                                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frameMainHome,HomeMainFragment())?.commit()
                                        binding.imCloseSearch.visibility = View.INVISIBLE
                                    }

                            }
                        }
                    }

                })
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
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}