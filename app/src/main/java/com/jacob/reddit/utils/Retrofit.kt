package com.jacob.reddit.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jacob.reddit.BuildConfig
import com.jacob.reddit.repository.model.errors.NoInternetException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.HashMap
import java.util.concurrent.TimeUnit

inline fun <reified T> createService(baseUrl: String, httpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(GsonConverterFactory.create(createGson()))
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        client(httpClient)
    }.build()

    return retrofit.create(T::class.java)
}

fun createOkHttpClient(headers: HashMap<String, String>): OkHttpClient {
    return OkHttpClient.Builder().apply {
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
        }

        if (headers.isNotEmpty()) {
            addInterceptor { chain ->

                if (!isAnyNetworkConnected()) {
                    Timber.e("No Internet : " + chain.request().toString())
                    throw NoInternetException("No internet connection")
                }

                val original = chain.request()
                val requestBuilder = original.newBuilder()

                val keys = headers.keys
                keys.forEach { requestBuilder.addHeader(it, headers[it]!!) }

                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }
    }.build()
}

fun createGson(): Gson {
    return GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()
}