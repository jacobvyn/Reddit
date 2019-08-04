package com.jacob.reddit.di

import com.jacob.reddit.repository.RedditRepositoryImpl
import com.jacob.reddit.repository.local.FakeLocalRedditRepository
import com.jacob.reddit.repository.remote.RemoteRedditRepository
import com.jacob.reddit.service.RedditService
import com.jacob.reddit.utils.REDDIT_BASE_URL
import com.jacob.reddit.utils.createOkHttpClient
import com.jacob.reddit.utils.createService

// should be Dagger2 or Koin
object Injector {

    fun injectRepository() = Injector.RepositoryModule.getRedditRepository()

    object RepositoryModule {
        fun getRedditRepository() = RedditRepositoryImpl(
            localRepo(),
            remoteRepo()
        )

        private fun localRepo() = FakeLocalRedditRepository()

        private fun remoteRepo() = RemoteRedditRepository(redditService)

        private val redditService: RedditService =
            createService(REDDIT_BASE_URL, createOkHttpClient(RedditService.HEADERS))
    }
}
