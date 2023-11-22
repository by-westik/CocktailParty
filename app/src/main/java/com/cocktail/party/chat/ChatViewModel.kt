package com.cocktail.party.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocktail.party.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.Request
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private var chatRepository: ChatRepository
) : ViewModel() {

    fun getUserToken() =  chatRepository.getUserAuthToken()

}