package com.yetkin.rxjava_mobilerdev.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yetkin.rxjava_mobilerdev.Resource
import com.yetkin.rxjava_mobilerdev.data.repositories.PixabayRepository
import com.yetkin.rxjava_mobilerdev.data.response.PixabayResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pixabayRepository: PixabayRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _images = MutableLiveData<Resource<PixabayResponse>>()
    val images: LiveData<Resource<PixabayResponse>> get() = _images

    init {
        getImages()
    }

    private fun getImages() {
        compositeDisposable.add(pixabayRepository.getImages()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                _images.postValue(response)
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}