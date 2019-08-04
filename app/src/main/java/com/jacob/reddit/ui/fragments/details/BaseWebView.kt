package com.jacob.reddit.ui.fragments.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class BaseWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : WebView(
    context,
    attrs,
    defStyleAttr,
    defStyleRes
) {
    var progressListener: ProgressListener? = null

    init {
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)
        webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressListener?.showProgress(true)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressListener?.showProgress(false)
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

    interface ProgressListener {
        fun showProgress(show: Boolean)
    }
}
