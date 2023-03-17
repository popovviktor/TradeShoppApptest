package com.example.testcleanarch.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope


class LoginFragment : Fragment() {


    lateinit var binding:FragmentLoginBinding
    private val mainviewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            val email = binding.editEmaillogin.text.toString()
            val password = binding.editPasswordLogin.text.toString()
            System.out.println("Код стартанул почти в активити мэйн")
            mainviewModel.login(email,password)
            mainviewModel.liveBoolLogIn.observe(requireActivity() as LoginActivity,Observer{
                if (it == true){
                    var user =mainviewModel.liveUserLoginDone.value
                    System.out.println(user)
                    var i = Intent(activity,MainActivity::class.java)
                    i.putExtra("firstName",user?.firstName!!)
                    i.putExtra("LastName",user?.lastName!!)
                    i.putExtra("email",user?.email!!)
                    startActivity(i)
                }
                if(it ==false){
                    Toast.makeText(activity,"Uncorrect login or password",Toast.LENGTH_SHORT).show()
                }
            })



        }
        binding.btnSignIN2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.place_holder_login_act,SignFragment())?.commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle()
            }
    }
}