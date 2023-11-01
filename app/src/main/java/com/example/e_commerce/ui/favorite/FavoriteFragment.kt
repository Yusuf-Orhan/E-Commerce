package com.example.e_commerce.ui.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.e_commerce.R
import com.example.e_commerce.common.showToast
import com.example.e_commerce.common.showToastMessage
import com.example.e_commerce.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    val viewModel: FavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            FavoriteScreen(requireContext())
        }

    }
}

@Composable
fun FavoriteScreen(context: Context) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = { showToastMessage(context = context, "Hello World") }) {
                Text(text = "My Favorites")
            }
            ElevatedButton(onClick = { showToastMessage(context, "Elevated Button Clicked") }) {
                Text(
                    text = "Clicked"
                )
            }
            Button(onClick = { showToastMessage(context, "Button Clicked") }) {
                Text(
                    text = "Clicked"
                )
            }
        }
    }
}