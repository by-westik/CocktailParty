package com.cocktail.party.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocktail.party.auth.model.AuthRequest
import com.cocktail.party.auth.model.AuthResponse
import com.cocktail.party.auth.model.SignUpRequest
import com.cocktail.party.auth.repository.AuthRepository
import com.cocktail.party.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<AuthResponse>>
        get() = _loginResponse

    fun loginUser(authRequest: AuthRequest) = viewModelScope.launch {
        _loginResponse.value = repository.loginUser(authRequest)
    }

    fun registerUser(signUpRequest: SignUpRequest) = viewModelScope.launch {
        _loginResponse.value = repository.registerUser(signUpRequest)
    }

}