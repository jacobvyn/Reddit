package com.jacob.reddit.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Base64
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.jacob.reddit.App
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun String.base64(): String {
    return Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
}

fun AppCompatActivity.addFragment(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply { action() }.commit()
}

fun isAnyNetworkConnected(): Boolean {
    val cm = App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null
}

fun Disposable.add(subscriptions: CompositeDisposable?) {
    subscriptions?.add(this)
}

fun ProgressBar.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}