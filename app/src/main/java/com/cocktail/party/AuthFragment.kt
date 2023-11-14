package com.cocktail.party

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cocktail.party.databinding.FragmentAuthBinding
import com.google.android.material.button.MaterialButton

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var btnLogin: MaterialButton
    private lateinit var tvSignUp: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater)

        btnLogin = binding.btnLogin
        tvSignUp = binding.tvSwitchToSignup

        btnLogin.setOnClickListener {
            val email = binding.loginText.text.toString()
            val password = binding.passwordText.text.toString()
            /*
            TODO добавить логику автоизации
             */
        }

        tvSignUp.setOnClickListener {
            /*
            TODO добавить навигацию на фрагмент регистрации
             */
        }

        return binding.root
    }
}