package com.highestaim.newsapp.ui.fragment


import androidx.lifecycle.lifecycleScope
import com.highestaim.newsapp.BaseFragment
import com.highestaim.newsapp.databinding.FavoriteFragmentBinding
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.ui.adapter.FavoritesAdapter
import com.highestaim.newsapp.ui.viewModel.FavoritesViewModel
import com.highestaim.newsapp.utils.initRecyclerView
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

//we can also implement pagination with paging 3 from local use Paging3
//also i don't implement delete/add favorite logic in in this page it's same as NewsFeedFragment
class FavoriteFragment : BaseFragment<FavoriteFragmentBinding>() {

    private val favorViewModel: FavoritesViewModel by viewModel()

    private val favoritesAdapter: FavoritesAdapter = FavoritesAdapter()

    override fun getViewBinding(): FavoriteFragmentBinding  = FavoriteFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {
        initFavorites()
        initFavoritesRecyclerView()
    }

    private fun initFavorites() {
        lifecycleScope.launchWhenCreated {
            favorViewModel.getFavorites().collect { favorites ->
                favorites?.run {
                    submitData(this)
                }
            }
        }
    }

    private fun initFavoritesRecyclerView() {
        binding?.favoritesRecyclerview?.initRecyclerView(context,favoritesAdapter)
    }


    private fun submitData(favorites: List<NewsDto>) {
        favoritesAdapter.submitList(favorites)
    }



}