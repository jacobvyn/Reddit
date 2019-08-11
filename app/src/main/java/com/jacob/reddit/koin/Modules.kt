package com.jacob.reddit.koin

import com.jacob.reddit.repository.RedditRepository
import com.jacob.reddit.repository.RedditRepositoryImpl
import com.jacob.reddit.repository.local.FakeLocalRedditRepository
import com.jacob.reddit.repository.local.LocalRepository
import com.jacob.reddit.repository.remote.RemoteRedditRepository
import com.jacob.reddit.repository.remote.RemoteRepository
import com.jacob.reddit.service.RedditService
import com.jacob.reddit.utils.REDDIT_BASE_URL
import com.jacob.reddit.utils.createOkHttpClient
import com.jacob.reddit.utils.createService
import org.koin.dsl.module

val dependencies = module {
    single { createOkHttpClient(RedditService.HEADERS) }
    single<RedditService> { createService(REDDIT_BASE_URL, get()) }
}
val repos = module {
    single<LocalRepository> { FakeLocalRedditRepository() }
    single<RemoteRepository> { RemoteRedditRepository(get()) }
    single<RedditRepository> { RedditRepositoryImpl(get(), get()) }
}

val appModules = listOf(dependencies, repos)