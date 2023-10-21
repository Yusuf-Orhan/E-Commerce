package com.example.e_commerce.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.common.showToast

import com.example.e_commerce.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    lateinit var binding : FragmentCartBinding
    private val viewModel : CartViewModel by viewModels()
    private val cartAdapter by lazy { CartAdapter(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false)
        binding.fragmentCart = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProducts()
        viewModel.getTotalBalance()
        observeLiveData()
    }
    fun gotoPay(){
        val action = CartFragmentDirections.actionCartFragmentToPaymentFragment()
        findNavController().navigate(action)
    }
    private fun observeLiveData(){
        viewModel.allProductList.observe(viewLifecycleOwner){
            binding.cartRw.adapter = cartAdapter
            cartAdapter.apply {
                productList = it
                deleteClick = {productModel ->
                    viewModel.deleteItem(productModel)
                    loadData(it)
                }
            }
        }
        viewModel._totalBalance.observe(viewLifecycleOwner){
            binding.totalBalanceText.text = "${it.toString()} $"
        }
    }
}