package com.jacob.reddit.repository

import com.jacob.reddit.model.Page
import com.jacob.reddit.repository.local.LocalRepository
import com.jacob.reddit.repository.remote.RemoteRepository
import com.jacob.reddit.utils.NEWS_LIMIT_PER_PAGE
import io.reactivex.Single
import timber.log.Timber

class RedditRepositoryImpl(
    private val localRepo: LocalRepository,
    private val remoteRepo: RemoteRepository
) : RedditRepository {

    override fun loadNews(afterToken: String): Single<Page> {
        return localRepo.findPage(afterToken)
            .onErrorResumeNext { error ->
                Timber.d("${error.message} ... receiving from remote source")
                remoteRepo.loadPage(getLimit(), afterToken)
                    .doOnSuccess {
                        localRepo.savePage(afterToken, it)
                            .subscribe()
                    }
            }
    }

    private fun getLimit(): Int {
        return NEWS_LIMIT_PER_PAGE // get from prefs
    }
}
