package com.yetkin.rxjava_mobilerdev.data.model

data class PixabayPhoto(
    val id: Int?,
    val imageURL: String?,
    val views: Int,
    val downloads: Int?,
    val likes: Int?,
    val userImageURL: String?,
    val previewURL:String?
)
