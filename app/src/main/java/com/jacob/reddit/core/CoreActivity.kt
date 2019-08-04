package com.jacob.reddit.core

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class CoreActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments.last() as? CoreFragment<*, *>
        val handled = fragment?.onBackPress() ?: false
        if (!handled) super.onBackPressed()
    }
}