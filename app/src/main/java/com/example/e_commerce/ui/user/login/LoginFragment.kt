package com.example.e_commerce.ui.user.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.e_commerce.MainActivity
import com.example.e_commerce.R
import com.example.e_commerce.common.showToast
import com.example.e_commerce.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private val  viewModel: LoginViewModel by activityViewModels()
    lateinit var binding : FragmentLoginBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginFragment = this
        observeLiveData()
    }
    fun login(){
        val email = binding.emailEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()
        viewModel.signIn(email, password)
    }
    private fun observeLiveData() = with(viewModel){
        isSignIn.observe(viewLifecycleOwner){isSignIn ->
            if (isSignIn){
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

            }else{
              Log.e("LoginFragment","Is sign in error")
            }
        }
        isEmpty.observe(viewLifecycleOwner){isEmpty ->
            if (isEmpty){
                requireView().showToast(getString(R.string.blanks_error))
            }
        }
    }

}