package com.example.testcleanarch.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.FragmentSignCreatePasswordBinding


class SignCreatePasswordFragment : Fragment() {

    lateinit var binding:FragmentSignCreatePasswordBinding
    private val mainviewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val passwordFirst = binding.editPasswordFirst.text.toString()
            val passwordSecond = binding.editPasswordSecond.text.toString()
            System.out.println("SSSSSSSSSSSS")
            System.out.println(passwordFirst)
            System.out.println(passwordSecond)
            if (mainviewModel.checkEqualPassword(passwordFirst,passwordSecond)==true){
                mainviewModel.newUserDbStepTwo(passwordFirst)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.place_holder_login_act,LoginFragment())?.commit()
            }else{
                Toast.makeText(activity,"Пароли не совпадают", Toast.LENGTH_SHORT).show()}
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignCreatePasswordBinding.inflate(inflater)
        return binding.root
    }

    companion object {

        @JvmStatic fun newInstance() =
                SignCreatePasswordFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}