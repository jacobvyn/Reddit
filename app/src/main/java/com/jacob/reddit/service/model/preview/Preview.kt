package com.jacob.reddit.service.model.preview

data class Preview(
    val enabled: Boolean,
    val images: List<Image>,
    val reddit_video_preview: RedditVideoPreview
)