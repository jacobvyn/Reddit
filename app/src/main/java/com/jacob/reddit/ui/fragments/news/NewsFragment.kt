package com.jacob.reddit.ui.fragments.news

import android.os.Bundle
import android.view.View
import com.jacob.reddit.R
import com.jacob.reddit.core.CoreFragment
import com.jacob.reddit.databinding.FragmentNewsBinding
import com.jacob.reddit.repository.model.News
import com.jacob.reddit.repository.model.Page
import com.jacob.reddit.ui.activities.MainActivity
import com.jacob.reddit.ui.fragments.news.adapter.NewsAdapter
import com.jacob.reddit.ui.fragments.news.presenter.NewsPresenter
import com.jacob.reddit.utils.PRELOAD_THRESHOLD
import com.jacob.reddit.utils.showDialogWithAction

class NewsFragment : CoreFragment<NewsPresenter, FragmentNewsBinding>(), NewsView,
    NewsAdapter.OnItemClickListener {

    override fun onItemClicked(news: News) {
        val host = activity
        if (host is MainActivity) host.showDetailsView(news.permalink)
    }

    override fun getLayoutId() = R.layout.fragment_news

    override fun createPresenter() = NewsPresenter()

    override fun onViewReady() {
        presenter?.onViewCreated(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter!!.getTopNews()
    }

    override fun onNewsLoaded(page: Page) {
        val adapter = NewsAdapter(this, PRELOAD_THRESHOLD) {
            presenter?.preloadNextPage()
        }
        dataBinding?.recyclerView?.adapter = adapter
        adapter.addToHead(page.newsList)
    }

    override fun onNewsPreloaded(page: Page) {
        val adapter = dataBinding?.recyclerView?.adapter as NewsAdapter
        adapter.addToTail(page.newsList)
    }

    override fun showDialog() {
        showDialogWithAction(
            R.string.no_internet_message,
            R.string.ok_button
        ) { presenter!!.getTopNews() }
    }
}
