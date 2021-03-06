package com.jacob.reddit.repository.remote

import com.jacob.reddit.repository.model.Page
import io.reactivex.Single

interface RemoteRepository {
    fun loadPage(limit: Int, afterToken: String): Single<Page>
}