package com.yetkin.rxjava_mobilerdev.data.repositories

import com.yetkin.rxjava_mobilerdev.Resource
import com.yetkin.rxjava_mobilerdev.data.remote.PixabayApi
import com.yetkin.rxjava_mobilerdev.data.response.PixabayResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PixabayRepository @Inject constructor(private val pixabayApi: PixabayApi) {

    fun getImages() = Observable.create<Resource<PixabayResponse>> { emitter ->
        emitter.onNext(Resource.Loading())
        pixabayApi.getImages().subscribeOn(Schedulers.io()).subscribe(
            { pixabayResponse ->
                emitter.onNext(Resource.Success(pixabayResponse))
                emitter.onComplete()
            },
            { error ->
                emitter.onNext(Resource.Error(error.message))
                emitter.onComplete()
            }
        )
    }
}