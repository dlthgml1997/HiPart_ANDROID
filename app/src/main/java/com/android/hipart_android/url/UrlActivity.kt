package com.android.hipart_android.url

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.hipart_android.R
import com.android.hipart_android.util.BaseActivity

class UrlActivity : BaseActivity() {

    private var webView : WebView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)

        webView = findViewById(R.id.webView)
        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView!!.loadUrl(intent.getStringExtra("url"))
    }
}
