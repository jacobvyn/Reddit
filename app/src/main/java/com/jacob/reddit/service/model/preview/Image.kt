package com.jacob.reddit.service.model.preview

data class Image(
    val id: String,
    val resolutions: List<Resolution>,
    val source: Source,
    val variants: Variants
)