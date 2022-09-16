package com.example.movieplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {

    companion object{
        lateinit var auth: FirebaseAuth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth= FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn=view.findViewById<Button>(R.id.createAccountBtn)
        val btn1=view.findViewById<TextView>(R.id.loginTV)
        btn1.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        btn.setOnClickListener{

            val email=view.findViewById<TextView>(R.id.emailRegister).text.toString()
            val password=view.findViewById<TextView>(R.id.passwordRegister).text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        findNavController().navigate(R.id.action_registerFragment_to_movieListFragment)
                    }
                }.addOnFailureListener{

                }
            }

        }
    }



}