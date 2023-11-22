package com.cocktail.party.chat

import com.cocktail.party.core.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val userPreferences: UserPreferences
){

    fun getUserAuthToken() = runBlocking { userPreferences.authToken.first() }

}