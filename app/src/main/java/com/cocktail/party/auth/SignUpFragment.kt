package com.cocktail.party.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocktail.party.R
import com.cocktail.party.auth.model.SignUpRequest
import com.cocktail.party.core.Resource
import com.cocktail.party.databinding.FragmentSignUpBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var btnSignUp: MaterialButton
    private lateinit var tvAuth: TextView

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater)

        btnSignUp = binding.signUpBtn
        tvAuth = binding.tvSwitchToAuth

        authViewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                }
                else -> {
                    Toast.makeText(requireContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnSignUp.setOnClickListener {
            val email = binding.emailText.text.toString()
            val login = binding.loginText.text.toString()
            val password = binding.passwordText.text.toString()
            authViewModel.registerUser(SignUpRequest(email, login, password))
        }

        tvAuth.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_authFragment)
        }

        return binding.root
    }
}