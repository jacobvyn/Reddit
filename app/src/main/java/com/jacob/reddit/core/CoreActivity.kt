package com.jacob.reddit.core

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.jacob.reddit.utils.show
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("Registered")
open class CoreActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments.lastOrNull() as? CoreFragment<*, *>
        val handled = fragment?.onBackPress() ?: false
        if (!handled) super.onBackPressed()
    }

    fun showProgress(show: Boolean) {
        progress_bar.show(show)
    }
}