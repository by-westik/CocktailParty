package com.cocktail.party

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cocktail.party.databinding.FragmentSignUpBinding
import com.google.android.material.button.MaterialButton

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var btnSignUp: MaterialButton
    private lateinit var tvAuth: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater)

        btnSignUp = binding.signUpBtn
        tvAuth = binding.tvSwitchToAuth

        btnSignUp.setOnClickListener {
            val email = binding.emailText.text.toString()
            val login = binding.loginText.text.toString()
            val password = binding.passwordText.text.toString()
            /*
            TODO добавить логику регистрации
             */
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }

        tvAuth.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_authFragment)
        }

        return binding.root
    }
}