package com.example.blogreading

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val webView: WebView = findViewById(R.id.webView)
        val blogUrl = intent.getStringExtra("blog_url")

        webView.webViewClient = WebViewClient()
        blogUrl?.let {
            webView.loadUrl(it)
        }
    }
}
