package com.example.movieplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn=view.findViewById<Button>(R.id.loginBtn)
        val btn1=view.findViewById<TextView>(R.id.registerTV)

        btn1.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        btn.setOnClickListener{

            val email=view.findViewById<TextView>(R.id.emailLogin).text.toString()
            val password=view.findViewById<TextView>(R.id.passwordLogin).text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){

                RegisterFragment.auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        findNavController().navigate(R.id.action_loginFragment_to_movieListFragment)
                    }
                }.addOnFailureListener{

                }
            }
        }
    }



}