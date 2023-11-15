package com.cocktail.party.auth.model

data class SignUpRequest(
    val email: String,
    val nick: String,
    val password: String
)
