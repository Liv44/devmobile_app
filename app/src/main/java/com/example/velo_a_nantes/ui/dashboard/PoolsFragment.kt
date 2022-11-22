package com.example.velo_a_nantes.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.velo_a_nantes.adapter.PoolAdapter
import com.example.velo_a_nantes.api.PoolApi
import com.example.velo_a_nantes.api.RetrofitHelper
import com.example.velo_a_nantes.databinding.FragmentPoolsBinding
import com.example.velo_a_nantes.models.allPools
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PoolsFragment : Fragment() {

    private var _binding: FragmentPoolsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val poolsViewModel =
            ViewModelProvider(this).get(PoolsViewModel::class.java)

        _binding = FragmentPoolsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView2
        var progressBar = binding.progressBarPools
        poolsViewModel.pools.observe(viewLifecycleOwner) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = PoolAdapter(it, requireContext())
            progressBar.visibility = View.GONE
            allPools = it
        }

        val poolsApi = RetrofitHelper().getInstance().create(PoolApi::class.java)
        GlobalScope.launch {
            val result = poolsApi.getPools()
            Log.d("POOLS", result.body().toString())
            poolsViewModel.pools.postValue(result.body())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}