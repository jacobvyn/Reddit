package com.jacob.reddit.repository.model

data class Award(
    val award_type: String,
    val coin_price: Int,
    val coin_reward: Int,
    val count: Int,
    val days_of_drip_extension: Int,
    val days_of_premium: Int,
    val description: String,
    val icon_height: Int,
    val icon_url: String,
    val icon_width: Int,
    val id: String,
    val is_enabled: Boolean,
    val name: String,
    val resized_icons: List<ResizedIcon>,
    val subreddit_coin_reward: Int,
    val subreddit_id: Any
)