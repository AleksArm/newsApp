package com.highestaim.newsapp.ui.fragment

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.highestaim.newsapp.BaseFragment
import com.highestaim.newsapp.databinding.NewsFragmentBinding
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.ui.adapter.NewsFeedAdapter
import com.highestaim.newsapp.ui.pagination.LoadStateAdapter
import com.highestaim.newsapp.ui.viewModel.NewsViewModel
import com.highestaim.newsapp.utils.initRecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<NewsFragmentBinding>() {

    private val newsViewModel: NewsViewModel by viewModel()

    private val newsFeedAdapter: NewsFeedAdapter = NewsFeedAdapter()

    override fun getViewBinding(): NewsFragmentBinding = NewsFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {
        initNewsFeed()
        initNewsFeedRecyclerView()
        handleFavorites()
    }

    private fun initNewsFeedRecyclerView() {
        val adapter = newsFeedAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter(newsFeedAdapter),
            footer = LoadStateAdapter(newsFeedAdapter)
        )
        binding?.newsRecyclerview?.initRecyclerView(context, adapter)

        handleAdapterLoadStateFlow()
        addLoadStateListenerOnAdapter()
    }

    private fun handleAdapterLoadStateFlow() {
        lifecycleScope.launchWhenCreated {

            newsFeedAdapter.loadStateFlow.collectLatest { loadStates ->
                binding?.swipeRefresh?.isRefreshing = loadStates.refresh is LoadState.Loading
            }

            newsFeedAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding?.newsRecyclerview?.scrollToPosition(0) }
        }
    }

    private fun addLoadStateListenerOnAdapter() {
        newsFeedAdapter.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.NotLoading -> {

                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                }
            }

        }
    }

    private fun handleFavorites() {
        newsFeedAdapter.onFavoriteClick  = { newsDto ->
            if (!newsDto.isFavorite) newsViewModel.deleteFavorite(newsDto) else
            newsViewModel.insertAsFavorite(newsDto)
        }
    }

    private fun initNewsFeed() {
        lifecycleScope.launchWhenCreated {
            newsViewModel.getNews().collectLatest {
                submitData(it)
            }
        }
    }


    private fun submitData(places: PagingData<NewsDto>) {
        lifecycleScope.launchWhenCreated {
            newsFeedAdapter.submitData(places)
        }
    }
}