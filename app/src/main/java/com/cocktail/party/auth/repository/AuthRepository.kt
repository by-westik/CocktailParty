package com.cocktail.party.auth.repository

import com.cocktail.party.auth.model.AuthRequest
import com.cocktail.party.auth.model.SignUpRequest
import com.cocktail.party.auth.network.AuthService
import com.cocktail.party.core.BaseRepository
import com.cocktail.party.core.UserPreferences
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: AuthService,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun loginUser(
        authRequest: AuthRequest
    ) = safeApiCall {
        apiService.loginUser(authRequest)
    }

    suspend fun registerUser(
        signUpRequest: SignUpRequest
    ) = safeApiCall {
        apiService.registerUser(signUpRequest)
    }

    suspend fun saveUserAuthToken(token: String) {
        preferences.saveUserAuthToken(token)
    }

}