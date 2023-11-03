package com.example.e_commerce.ui.detail

import android.graphics.Color
import android.media.Rating
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.e_commerce.R
import com.example.e_commerce.common.loadImage
import com.example.e_commerce.common.showToast
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

/*TODO : viewmodel deki insert item fonksiyonunda daha önce böyle bir ürünün kaydedilip kaydedilmediği kontrol edilecek eğer kaydedildiyse favorite tablosundan bunu
    silecek eğer kaydedilmediyse kaydedecek
 */



@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val viewModel : DetailViewModel by viewModels()
    private val arguments : DetailFragmentArgs by navArgs()
    private lateinit var productItem : ProductsItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        binding.detailFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productItem = arguments.productModel
        viewModel.controlFavorite(productItem.id)
        observes()
        with(binding){
            productModel = productItem
            priceText = "${productItem.price}$"
            imageView.loadImage(productItem.image,requireContext())
            category = capitalizeString(productItem.category)
            backImage.setOnClickListener {
                findNavController().popBackStack()
            }
            favoriteImage.setOnClickListener {
                viewModel.addFavorite(productItem.id,productItem)
            }
        }
    }
    private fun capitalizeString(input: String): String {
        val words = input.split(" ")
        val capitalizedWords = words.map { it.capitalize(Locale.ROOT) }
        return capitalizedWords.joinToString(" ")
    }

    fun addCart(){
        viewModel.addCart(productItem)
        requireView().showToast(getString(R.string.success_message2))
        val action = DetailFragmentDirections.actionDetailFragmentToMainFragment()
        findNavController().navigate(action)
    }
    private fun observes(){
        viewModel.isFavorite.observe(viewLifecycleOwner){
            if (it){
                binding.favoriteImage.setImageResource(R.drawable.ic_favorite_selected)
            }else{
                binding.favoriteImage.setImageResource(R.drawable.ic_favorite_unselected)
            }
        }
    }
}