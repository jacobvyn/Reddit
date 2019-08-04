package com.jacob.reddit.service.model

data class Data(
    val after: String,
    val before: String,
    val children: List<Child>,
    val dist: Int,
    val facets: Facets,
    val modhash: String
)