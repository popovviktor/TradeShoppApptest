package com.example.testcleanarch.presentation.screensNav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.adapter.AdapterSearch
import com.example.testcleanarch.databinding.FragmentSearchBinding
import com.example.testcleanarch.presentation.MainViewModel


class SearchFragment : Fragment() {
    private val adaptersearch = AdapterSearch()
    private val mainviewModel: MainViewModel by activityViewModels()
    lateinit var binding:FragmentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var grid = GridLayoutManager(activity,1, RecyclerView.VERTICAL,false)
        binding.rvsearch.layoutManager = grid
        System.out.println(mainviewModel.liveSearchWord.value!!)
        System.out.println(mainviewModel.liveSearch.value?.words!!)
        adaptersearch.addSearchWord(mainviewModel.liveSearchWord.value!!)
        adaptersearch.addAllWords(mainviewModel.liveSearch.value?.words!!)
        adaptersearch.searchWords()
        binding.rvsearch.adapter = adaptersearch
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        return  binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}