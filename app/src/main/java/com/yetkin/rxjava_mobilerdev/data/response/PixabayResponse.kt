package com.yetkin.rxjava_mobilerdev.data.response

import com.yetkin.rxjava_mobilerdev.data.model.PixabayPhoto

data class PixabayResponse(
    val total: Int?,
    val totalHits: Int?,
    val hits: List<PixabayPhoto>?
)
