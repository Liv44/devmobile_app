package com.example.velo_a_nantes.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.velo_a_nantes.models.Pool

class PoolsViewModel : ViewModel() {
    private val _pools = MutableLiveData<List<Pool>>().apply {
        value = ArrayList()
    }
    val pools: MutableLiveData<List<Pool>> = _pools

}