package com.cocktail.party.auth.model

data class SignUpRequest(
    val email: String,
    val nickname: String,
    val password: String
)
