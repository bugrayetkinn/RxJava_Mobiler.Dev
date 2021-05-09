package com.yetkin.rxjava_mobilerdev.di

import com.yetkin.rxjava_mobilerdev.BuildConfig
import com.yetkin.rxjava_mobilerdev.data.remote.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Singleton
    @Provides
    fun provideHttpClient() = OkHttpClient.Builder().apply {
        addInterceptor(provideInterceptor())
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit() =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Singleton
    @Provides
    fun providePixabayClient(retrofit: Retrofit) = retrofit.create(PixabayApi::class.java)

}