package com.highestaim.newsapp.ui.fragment

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.highestaim.newsapp.BaseFragment
import com.highestaim.newsapp.R
import com.highestaim.newsapp.databinding.NewsRootTabFragmentBinding
import com.highestaim.newsapp.enums.NewsPageFragmentsKeys
import com.highestaim.newsapp.enums.NewsPageFragmentsKeys.FAVORITE
import com.highestaim.newsapp.enums.NewsPageFragmentsKeys.NEWS
import com.highestaim.newsapp.ui.adapter.NewsPageAdapter

class NewsRootTabFragment : BaseFragment<NewsRootTabFragmentBinding>() {

    private lateinit var newsPageAdapter: NewsPageAdapter

    override fun getViewBinding(): NewsRootTabFragmentBinding  = NewsRootTabFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {
        setupTabLayout()
    }

    private fun setupTabLayout() {
        val fragments = hashMapOf(NEWS to NewsFragment() as Fragment, FAVORITE to FavoriteFragment())
        binding?.viewPager?.isUserInputEnabled = false

        initTabAdapter(fragments)

        binding?.viewPager?.adapter = newsPageAdapter

        binding?.tabView?.let {
            binding?.viewPager?.let { it1 ->
                TabLayoutMediator(it, it1) { tab, position ->
                    tab.text = resources.getStringArray(R.array.tabs_title)[position]
                }.attach()
            }
        }
    }

    private fun initTabAdapter(fragments : HashMap<NewsPageFragmentsKeys, Fragment>) {
        activity?.run {
            newsPageAdapter = NewsPageAdapter(
                this,
                resources.getStringArray(R.array.tabs_title) ,
                fragments
            )
        }
    }
}