package com.neuralnet.maisfinancas.ui.screens.chatbot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.databinding.FragmentChatbotBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chatbot) {

    private var _binding: FragmentChatbotBinding? = null
    private val binding: FragmentChatbotBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChatbotBinding.inflate(inflater, container, false)

        return binding.root
    }
}