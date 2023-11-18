package com.example.e_commerce.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.example.e_commerce.R
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.FavoriteModel
import com.example.e_commerce.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    state: FavoriteState, context: Context, modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.bottom_nav_favorite)) })
    }) {
        Column(
            modifier = modifier.padding(it)
        ) {
            FavoriteList(state.favoriteList)
        }
    }
}


@Composable
fun FavoriteList(
    favorites: List<FavoriteModel>, modifier: Modifier = Modifier
) {
    LazyColumn {
        items(favorites) {
            FavoriteItem(it.productsItem)
        }
    }
}



@Composable
fun FavoriteItem(
    productsItem: ProductsItem, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Row {
                AsyncImage(
                    model = productsItem.image,
                    modifier = modifier
                        .padding(16.dp)
                        .size(90.dp),
                    placeholder = painterResource(id = R.drawable.baseline_loading_24),
                    contentDescription = null
                )
                Column(
                    modifier = modifier.padding(top = 10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = productsItem.title)
                    Text(text = "${productsItem.price}$")
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(red = 255, green = 186, blue = 73),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "${productsItem.rating.rate}",
                    modifier = Modifier.padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
























