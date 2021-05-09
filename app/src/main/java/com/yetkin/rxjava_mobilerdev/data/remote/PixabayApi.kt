package com.yetkin.rxjava_mobilerdev.data.remote

import com.yetkin.rxjava_mobilerdev.BuildConfig
import com.yetkin.rxjava_mobilerdev.data.response.PixabayResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val DEFAULT_QUERY = "yellow+flowers"
const val IMAGE_TYPE = "photo"
const val API = "/api/"

const val KEY = "key"
const val QUERY = "q"
const val TYPE = "image_type"

interface PixabayApi {

    @GET(API)
    fun getImages(
        @Query(KEY) key: String = BuildConfig.API_KEY,
        @Query(QUERY) query: String = DEFAULT_QUERY,
        @Query(TYPE) imageType: String = IMAGE_TYPE
    ): Single<PixabayResponse>

}