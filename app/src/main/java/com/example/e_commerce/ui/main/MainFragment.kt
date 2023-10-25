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
import com.example.e_commerce.databinding.FragmentMainBinding
import com.example.e_commerce.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel : MainViewModel by viewModels()
    private val productAdapter by lazy { ProductAdapter(requireContext()) }
    lateinit var binding : FragmentMainBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        observerLiveData()
        viewModel.controlIsFavorite()
        with(binding){
            swipeRefreshLayout.setOnRefreshListener {
                productsProgress.visibility = View.VISIBLE
                productsRw.visibility = View.INVISIBLE
                viewModel.getData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    private fun observerLiveData(){
        with(viewModel){
            favoriteModels.observe(viewLifecycleOwner){
                for (productModel in it) {
                    productAdapter.isChecked = productModel
                }
            }
            productsItemList.observe(viewLifecycleOwner){list ->
                with(binding){
                    productsProgress.visibility = View.INVISIBLE
                    productsRw.visibility = View.VISIBLE
                    productsRw.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(requireContext(),2)
                        adapter = productAdapter.also { adapter ->
                            adapter.loadData(list)
                            adapter.addFavorite = {isChecked,item ->
                                viewModel.setFavorite(item.id,isChecked)
                            }
                            adapter.onItemClick = {
                                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
                                findNavController().navigate(action)
                            }
                        }
                    }
                }
            }
            isLoading.observe(viewLifecycleOwner){
                if (it){
                    with(binding){
                        productsProgress.visibility = View.VISIBLE
                        productsRw.visibility = View.INVISIBLE
                    }
                }

            }
            error.observe(viewLifecycleOwner){
                if (it){
                    with(binding){
                        productsProgress.visibility = View.INVISIBLE
                        productsRw.visibility = View.INVISIBLE
                        requireView().showSnackbar(getString(R.string.error_message))
                    }
                }

            }

        }

    }
}