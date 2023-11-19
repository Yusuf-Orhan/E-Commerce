package com.example.e_commerce.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.common.util.CreditCardTextFormatter
import com.example.e_commerce.common.showToast
import com.example.e_commerce.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {
    lateinit var binding: FragmentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater)
        binding.paymentFragment = this
        return binding.root
    }

    private val monthList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private val yearList = listOf(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032)

    private var monthValue = 0
    private var yearValue = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monthAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, monthList)
        val yearAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, yearList)
        with(binding) {

            etCreditCardNumber.addTextChangedListener(CreditCardTextFormatter())

            actExpireOnMonth.setAdapter(monthAdapter)
            actExpireOnYear.setAdapter(yearAdapter)

            actExpireOnMonth.setOnItemClickListener { _, _, position, _ ->
                monthValue = monthList[position]
            }

            actExpireOnYear.setOnItemClickListener { _, _, position, _ ->
                yearValue = yearList[position]
            }
        }
    }

    fun payNow() {
        val action =
            PaymentFragmentDirections.actionPaymentFragmentToPaymentSuccesFragment()
        findNavController().navigate(action)
        requireView().showToast("Pay Now Button Clicked")
    }

}