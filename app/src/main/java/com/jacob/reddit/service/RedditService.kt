package com.jacob.reddit.service

import com.jacob.reddit.service.model.RedditResponse
import com.jacob.reddit.utils.NEWS_LIMIT_PER_PAGE
import com.jacob.reddit.utils.REDDIT_APP_ID
import com.jacob.reddit.utils.base64
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface RedditService {

    @GET("top/.json?")
    fun loadPage(
        @Query("limit") limit: Int = NEWS_LIMIT_PER_PAGE,
        @Query("after") afterToken: String = ""
    ): Single<RedditResponse>


    companion object {
        private const val KEY_GRANT_TYPE = "grant_type"
        private const val KEY_AUTHORIZATION = "Authorization"
        private const val KEY_DEVICE_ID = "device_id"

        private const val PARAM_GRANT_TYPE = "installed_client"
        private const val PARAM_DEVICE_ID = "4d7674f3-46bf-4352-9784-91e87c74d106"
        private val PARAM_BASIC_AUTHORIZATION = "Basic ${REDDIT_APP_ID.base64()}"

        val HEADERS = HashMap<String, String>().apply {
            put(KEY_AUTHORIZATION, PARAM_BASIC_AUTHORIZATION)
            put(KEY_GRANT_TYPE, PARAM_GRANT_TYPE)
            put(KEY_DEVICE_ID, PARAM_DEVICE_ID)
        }

    }
}