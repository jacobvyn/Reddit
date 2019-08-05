package com.jacob.reddit.repository.local

import com.jacob.reddit.repository.model.Page
import com.jacob.reddit.repository.model.errors.PageNotFoundException
import io.reactivex.Single

interface LocalRepository {
    fun savePage(id: String, page: Page): Single<Boolean>

    @Throws(PageNotFoundException::class)
    fun findPage(id: String): Single<Page>
}