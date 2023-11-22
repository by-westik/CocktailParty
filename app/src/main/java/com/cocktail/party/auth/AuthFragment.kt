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
import com.cocktail.party.auth.model.AuthRequest
import com.cocktail.party.core.Resource
import com.cocktail.party.databinding.FragmentAuthBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var btnLogin: MaterialButton
    private lateinit var tvSignUp: TextView

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater)

        btnLogin = binding.btnLogin
        tvSignUp = binding.tvSwitchToSignup

        authViewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.action_authFragment_to_homeFragment)
                    authViewModel.saveUserAuthToken(it.value.token)
                }
                else -> {
                    Toast.makeText(requireContext(), "Ошибка авторизации", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnLogin.setOnClickListener {
            val login = binding.loginText.text.toString()
            val password = binding.passwordText.text.toString()
            authViewModel.loginUser(AuthRequest(login, password))
        }

        tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_signUpFragment)
        }

        return binding.root
    }
}