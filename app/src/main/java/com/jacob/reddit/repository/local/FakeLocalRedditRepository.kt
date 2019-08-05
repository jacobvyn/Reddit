package com.jacob.reddit.repository.local

import com.jacob.reddit.repository.model.Page
import com.jacob.reddit.repository.model.errors.PageNotFoundException
import io.reactivex.Single

class FakeLocalRedditRepository : LocalRepository {

    override fun findPage(id: String): Single<Page> {
        return Single.fromCallable {
            return@fromCallable FAKE_DATA_BASE[id] ?: throw PageNotFoundException()
        }
    }

    override fun savePage(id: String, page: Page): Single<Boolean> {
        return Single.fromCallable {
            if (page.newsList.isEmpty()) return@fromCallable false

            FAKE_DATA_BASE[id] = page
            true
        }
    }

    companion object {
        private val FAKE_DATA_BASE = HashMap<String, Page>()
    }
}
