package com.example.e_commerce.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.common.showToast
import com.example.e_commerce.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private val adapter = FavoriteAdapter()
    private val viewModel: FavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavorite()
        binding.favoriteRecyclerView.adapter = adapter
        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        observes()
    }
    private fun observes() {
        viewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            if (favoriteList.isNotEmpty()) {
                adapter.loadData(favoriteList)
            }else{
                requireView().showToast("Favorite Not Found!")
            }
        }
    }
}
