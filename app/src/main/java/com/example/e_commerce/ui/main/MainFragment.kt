package com.example.e_commerce.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.common.showSnackbar
import com.example.e_commerce.common.showToast
import com.example.e_commerce.data.model.room.FavoriteModel
import com.example.e_commerce.databinding.FragmentMainBinding
import com.example.e_commerce.ui.detail.DetailFragmentArgs
import com.example.e_commerce.ui.favorite.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel : MainViewModel by viewModels()
    private val favoriteViewModel : FavoriteViewModel by viewModels()
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
                                val favoriteModel = FavoriteModel(item,isChecked)
                                if(isChecked){
                                    favoriteViewModel.addFavorite(favoriteModel).run {
                                        requireView().showToast("Added Favorite")
                                    }
                                }else{
                                    favoriteViewModel.deleteFavorite(favoriteModel).run {
                                        requireView().showToast("Deleted Favorite")
                                    }
                                }
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