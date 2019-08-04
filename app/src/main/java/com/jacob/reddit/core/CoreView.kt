package com.jacob.reddit.core

public interface CoreView {
    fun showMessage(text: String)
    fun showMessage(error: Throwable)
    fun showProgress(show: Boolean)
    fun onBackPress():Boolean
}
