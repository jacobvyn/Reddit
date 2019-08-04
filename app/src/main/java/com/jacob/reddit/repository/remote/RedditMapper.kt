package com.jacob.reddit.repository.remote

import com.jacob.reddit.model.News
import com.jacob.reddit.model.Page
import com.jacob.reddit.service.model.RedditResponse
import io.reactivex.functions.Function

class RedditMapper :
    Function<RedditResponse, Page> {
    override fun apply(response: RedditResponse): Page {
        val newsList = ArrayList<News>()
        response.data.children.forEach {
            val data = it.data
            val news = News(
                data.author,
                data.url,
                data.thumbnail,
                data.title,
                data.num_comments,
                data.ups,
                data.subreddit_name_prefixed
            )
            newsList.add(news)
        }

        return Page(response.data.after, newsList)
    }

}