package com.jacob.reddit.repository.remote

import com.jacob.reddit.repository.model.News
import com.jacob.reddit.repository.model.Page
import com.jacob.reddit.service.model.RedditResponse
import com.jacob.reddit.utils.REDDIT_BASE_URL
import com.jacob.reddit.utils.getIconRes
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
                data.subreddit_name_prefixed,
                data.created,
                getIconRes(),
                "$REDDIT_BASE_URL${data.permalink}"
            )
            newsList.add(news)
        }

        return Page(response.data.after, newsList)
    }

}