package com.jacob.reddit.ui.fragments.news

import com.jacob.reddit.core.CoreView
import com.jacob.reddit.repository.model.Page

interface NewsView : CoreView {
    fun onNewsPreloaded(page: Page)
    fun onNewsLoaded(page: Page)
    fun showDialog()
}
