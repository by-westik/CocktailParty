package com.cocktail.party.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cocktail.party.chat.EchoWebSocketListener.Companion.NORMAL_CLOSURE_STATUS
import com.cocktail.party.databinding.FragmentHomeBinding
import com.cocktail.party.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var message : Button
    private lateinit var output: TextView
    private lateinit var entryText: EditText
    private val client by lazy {
        OkHttpClient()
    }
    private var ws: WebSocket? = null

    private val chatViewModel: ChatViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        message = binding.message
        output = binding.output
        entryText = binding.textEntry

        message.setOnClickListener {
            ws?.apply {
                val text = entryText.text.toString()
                output(text)
                send(text)
                entryText.text.clear()
            } ?: ping("Error: Restart the App to reconnect")
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        start()
    }

    override fun onPause() {
        super.onPause()
        stop()
    }

    private fun start() {
        val request: Request = Request.Builder()
            .url(Constants.WEBSOCKET_URL)
            .addHeader("Authorization", "Bearer ${chatViewModel.getUserToken().toString()}")
            .build()
        val listener = EchoWebSocketListener(this::output, this::ping) { ws = null }
        ws = client.newWebSocket(request, listener)
    }

    private fun stop() {
        ws?.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
    }

    override fun onDestroy() {
        super.onDestroy()
        client.dispatcher.executorService.shutdown()
    }

    private fun output(txt: String) {
        activity?.runOnUiThread {
            "${output.text}\n$txt".also { output.text = it }
        }
    }

    private fun ping(txt: String) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), txt, Toast.LENGTH_SHORT).show()
        }
    }
}