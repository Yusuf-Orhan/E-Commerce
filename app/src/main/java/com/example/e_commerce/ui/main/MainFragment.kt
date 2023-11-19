package com.example.e_commerce.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.common.showSnackbar
import com.example.e_commerce.data.model.remote.ProductsItem
import com.example.e_commerce.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private val productAdapter by lazy { ProductAdapter(requireContext()) }
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerLiveData()
        viewModel.controlIsFavorite()
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.handleEvent(MainEvent.IsRefreshed)
                swipeRefreshLayout.isRefreshing = false
            }
            tryAgainButton.setOnClickListener {
                viewModel.handleEvent(MainEvent.TryAgain)
            }
        }
    }

    private fun observerLiveData() {
        with(viewModel) {
            state.observe(viewLifecycleOwner) { state ->
                if (state.isLoading == true) {
                    with(binding) {
                        errorView.visibility = View.INVISIBLE
                        productsProgress.visibility = View.VISIBLE
                        productsRw.visibility = View.INVISIBLE

                    }
                } else if (state.isError != null) {
                    with(binding) {
                        errorView.visibility = View.VISIBLE
                        productsProgress.visibility = View.INVISIBLE
                        productsRw.visibility = View.INVISIBLE
                        requireView().showSnackbar(getString(R.string.error_message))
                    }
                } else if (state.products != null) {
                    with(binding) {
                        errorView.visibility = View.INVISIBLE
                        productsProgress.visibility = View.INVISIBLE
                        productsRw.visibility = View.VISIBLE
                        productsRw.apply {
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = productAdapter.also { adapter ->
                                adapter.loadData(state.products as List<ProductsItem>)
                                adapter.onItemClick = {
                                    val action =
                                        MainFragmentDirections.actionMainFragmentToDetailFragment(it)
                                    findNavController().navigate(action)
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}