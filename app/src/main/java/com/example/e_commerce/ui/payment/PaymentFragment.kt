package com.example.e_commerce.ui.payment

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.common.showToast
import com.example.e_commerce.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {
    var binding : FragmentPaymentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater)
        binding?.paymentFragment = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun payNow(){
        val action = PaymentFragmentDirections.actionPaymentFragmentToPaymentSuccesFragment()
        findNavController().navigate(action)
        requireView().showToast("Pay Now Button Clicked")
    }

}