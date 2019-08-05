package com.jacob.reddit.repository.remote

import com.jacob.reddit.repository.model.Page
import com.jacob.reddit.service.RedditService
import io.reactivex.Single

class RemoteRedditRepository(private val redditService: RedditService) : RemoteRepository {

    override fun loadPage(limit: Int, afterToken: String): Single<Page> {
        return redditService.loadPage(limit, afterToken)
            .map(RedditMapper())
    }
}