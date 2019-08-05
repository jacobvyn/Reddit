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
import com.jacob.reddit.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

fun String.base64(): String {
    return Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
}

fun AppCompatActivity.addFragment(
    fragment: Fragment, @IdRes frameId: Int,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.transact {
        add(frameId, fragment)
        if (addToBackStack) addToBackStack("")
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
        diff < 59 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} min ago"
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
private val RANDOM = Random()

fun getIconRes(): Int {
    return when (RANDOM.nextInt(8)) {
        1 -> R.drawable.aquarius_icon
        2 -> R.drawable.aries_icon
        3 -> R.drawable.cancer_icon
        4 -> R.drawable.capricorn_icon
        5 -> R.drawable.gemini_icon
        6 -> R.drawable.pisces_icon
        7 -> R.drawable.sagittarius_icon
        else -> R.drawable.gemini_icon
    }
}