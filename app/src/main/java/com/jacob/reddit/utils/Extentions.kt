package com.jacob.reddit.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Base64
import android.view.View
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

fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun formatDate(timeVal: Long): String {
    var time = timeVal
    if (time < 1000000000000L) {
        time *= 1000
    }

    val now = System.currentTimeMillis()
    if (time > now || time <= 0) {
        return ""
    }

    val diff = now - time
    return when {
        diff < MINUTE_MILLIS -> "just now"
        diff < 2 * MINUTE_MILLIS -> "a minute ago"
        diff < 50 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} min ago"
        diff < 90 * MINUTE_MILLIS -> "an hour ago"
        diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS}h ago"
        diff < 48 * HOUR_MILLIS -> "yesterday"
        else -> "${diff / DAY_MILLIS} days ago"
    }
}

const val SECOND_MILLIS = 1000;
const val MINUTE_MILLIS = 60 * SECOND_MILLIS
const val HOUR_MILLIS = 60 * MINUTE_MILLIS
const val DAY_MILLIS = 24 * HOUR_MILLIS