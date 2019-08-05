package com.jacob.reddit.ui.fragments.news.presenter

import com.jacob.reddit.core.CorePresenter
import com.jacob.reddit.repository.model.Page
import com.jacob.reddit.repository.RedditRepository
import com.jacob.reddit.ui.fragments.news.NewsView
import com.jacob.reddit.utils.add
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsPresenter(repository: RedditRepository) : CorePresenter<NewsView>(repository) {
    private var state = State.IDLE
    private lateinit var currentToken: String

    fun getTopNews() {
        loadNews("") {
            view?.onNewsLoaded(it)
        }
    }

    fun preloadNextPage() {
        loadNews(currentToken) {
            view?.onNewsPreloaded(it)
        }
    }

    private fun loadNews(token: String, consumer: (page: Page) -> Unit) {
        if (state == State.LOADING) return
        state = State.LOADING
        view?.showProgress(true)
        repository.loadNews(afterToken = token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                state = State.IDLE
                view?.showProgress(false)
            }
            .subscribe({
                currentToken = it.afterToken
                consumer(it)
            }, this::onError)
            .add(subscriptions)
    }


    enum class State {
        IDLE, LOADING
    }
}

