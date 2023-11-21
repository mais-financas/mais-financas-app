package com.neuralnet.maisfinancas.ui.screens.chatbot

import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.databinding.FragmentChatbotBinding
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar

@Composable
fun ChatBotScreen(onNavigateUp: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(R.string.entenda_seus_objetivos),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
            )
        }
    ) {
        AndroidViewBinding(FragmentChatbotBinding::inflate, modifier = Modifier.padding(it)) {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl("https://mediafiles.botpress.cloud/b6cf7b0a-6c47-49c4-893b-3c81b3b479dd/webchat/bot.html")
                settings.domStorageEnabled = true
                settings.javaScriptEnabled = true
                settings.setSupportZoom(true)
            }
        }
    }
}
