package com.jacob.reddit.repository

import com.jacob.reddit.model.Page
import com.jacob.reddit.model.errors.PageNotFoundException
import io.reactivex.Single

interface RedditRepository {
    @Throws(PageNotFoundException::class)
    fun loadNews(afterToken: String = EMPTY_TOKEN): Single<Page>

    companion object {
        const val EMPTY_TOKEN = ""
    }
}