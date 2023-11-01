package com.example.e_commerce.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.common.showAlert
import com.example.e_commerce.common.showToast
import com.example.e_commerce.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()
    private val cartAdapter by lazy { CartAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.fragmentCart = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProducts()
        viewModel.getTotalBalance()
        observes()
        binding.cartRw.adapter = cartAdapter
        with(viewModel) {
            cartAdapter.deleteClick = { itemId ->
                requireView().showAlert(msg = "Are you sure delete?", positiveBtnOnClick = {
                    deleteItem(itemId)
                })
            }
        }
    }

    fun gotoPay() {
        val action = CartFragmentDirections.actionCartFragmentToPaymentFragment()
        findNavController().navigate(action)
    }

    private fun observes() {
        viewModel.allProductList.observe(viewLifecycleOwner) {
            cartAdapter.loadData(it)
        }
        viewModel.totalBalance.observe(viewLifecycleOwner) {
            binding.totalBalanceText.text = it.toString().format("%.3f$")
        }
    }
}