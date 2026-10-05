package com.example.bookstore

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookStoreWebView()
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BookStoreWebView() {

    val webViewState = remember {
        mutableStateOf<WebView?>(null)
    }

    BackHandler {
        webViewState.value?.let { webView ->
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                // Let Android handle back button
            }
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),

        factory = { context ->

            WebView(context).apply {

                webViewClient = WebViewClient()

                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true

                loadUrl("https://notes-taking-app-by-anas.netlify.app/")

                webViewState.value = this
            }
        }
    )
}