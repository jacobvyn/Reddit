package com.jacob.reddit.model

class News(
    val author: String,
    val url: String,
    val thumbnail: String,
    val title: String,
    val comments: Int,
    val likes: Int,
    val subreddit: String
) {
    val commentsString: String
        get() = "$comments"

    val likesString: String
        get() = "$comments"
}
