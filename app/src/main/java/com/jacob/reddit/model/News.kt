package com.jacob.reddit.model

import com.jacob.reddit.utils.formatDate

class News(
    val author: String,
    val url: String,
    val thumbnail: String,
    val title: String,
    val comments: Int,
    val likes: Int,
    val subreddit: String,
    val postTime: Long
) {
    val commentsString: String
        get() = "$comments"

    val likesString: String
        get() = "$likes"

    val date: String
        get() = formatDate(postTime)
}
