package com.cocktail.party.auth.network

import com.cocktail.party.auth.model.AuthResponse
import com.cocktail.party.auth.model.AuthRequest
import com.cocktail.party.auth.model.SignUpRequest
import com.cocktail.party.utils.Constants
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(Constants.LOGIN_END_POINT)
    suspend fun loginUser(
        @Body loginRequest: AuthRequest
    ) : AuthResponse

    @POST(Constants.REGISTER_END_POINT)
    suspend fun registerUser(
        @Body registerRequest: SignUpRequest
    ) : AuthResponse
}