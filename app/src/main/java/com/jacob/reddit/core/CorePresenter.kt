package com.jacob.reddit.core

import com.jacob.reddit.repository.RedditRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class CorePresenter<V : CoreView>(val repository: RedditRepository) {
    var view: V? = null
    var subscriptions: CompositeDisposable? = null

    init {
        subscriptions = CompositeDisposable()
    }

    fun onError(error: Throwable) {
        Timber.e(error)
        view?.let {
            it.showProgress(false)
            it.showMessage(error)
        }
    }

    fun detachView() {
        view = null
        subscriptions?.let { if (!it.isDisposed) it.dispose() }
        subscriptions = null
    }

    fun onViewCreated(view: V) {
        this.view = view
    }
}