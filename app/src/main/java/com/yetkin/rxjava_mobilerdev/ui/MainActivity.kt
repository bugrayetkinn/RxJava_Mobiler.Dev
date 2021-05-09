package com.yetkin.rxjava_mobilerdev.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yetkin.rxjava_mobilerdev.Resource
import com.yetkin.rxjava_mobilerdev.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val pixabayAdapter: PixabayAdapter by lazy {
        PixabayAdapter()
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            lifecycleOwner = this@MainActivity
            initializeRecycler()
        }

        mainViewModel.images.observe(this, { resource ->
            when (resource) {
                is Resource.Loading -> Log.e("LOADING", "@@@")
                is Resource.Success -> {
                    pixabayAdapter.submitList(resource.data?.hits)
                }
                is Resource.Error -> Log.e("ERROR", "${resource.exception}")
            }
        })
    }

    private fun initializeRecycler() {
        binding.recyclerPixabay.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = pixabayAdapter
        }
    }
}