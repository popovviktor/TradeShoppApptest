package com.example.testcleanarch.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.FragmentSignBinding


class SignFragment : Fragment() {
    lateinit var binding: FragmentSignBinding
    private val mainviewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            SignFragment().apply {
                arguments = Bundle()
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIN.setOnClickListener {
        val email = binding.editEmail.text.toString()
        var checkcorrectemail = mainviewModel.emailCheckCorrect(email)
        if (checkcorrectemail==false){
            System.out.println("Некорректный mail")
            System.out.println("Некорректный mail!!!!!!!!!!")
            Toast.makeText(activity,"Uncorrect mail",Toast.LENGTH_SHORT).show()
        }
        if (checkcorrectemail==true){
            System.out.println("корректный mail")
            mainviewModel.emailCheckInDB(email)
            mainviewModel.liveBoolemailCheckInDB.observe(requireActivity() as LoginActivity,
                Observer {
                    if (it==true){System.out.println("Даный email зарегистрирован")
                        Toast.makeText(activity,"Даный email зарегистрирован",Toast.LENGTH_SHORT).show()
                        System.out.println("Сработало 1")
                    }
                    if (it==false){
                        System.out.println("Сработало 2")
                        val firstname = binding.editFirstName.text.toString()
                        val lastname = binding.editLastNAme.text.toString()
                        System.out.println("!!!!!!!!!! Sign fragment")
                        System.out.println("$firstname   $lastname")
                        System.out.println("!!!!!!!!!! Sign fragment")



                        if (mainviewModel.checkCorrectFirstLastName(firstname,lastname)==true){
                            System.out.println("Сработало 3")
                            mainviewModel.newUserDbStepOne(firstName = firstname, lastName = lastname,email=email)
                            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.place_holder_login_act,SignCreatePasswordFragment())?.commit()
                        }else{Toast.makeText(activity,"Провести заполнены ли все данные",Toast.LENGTH_SHORT).show()
                        }
                    }


                })

        }
    }
        binding.btnLogIN.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.place_holder_login_act,LoginFragment())?.commit()
        }


   }}
