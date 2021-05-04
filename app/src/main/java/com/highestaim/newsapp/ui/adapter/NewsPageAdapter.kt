package com.highestaim.newsapp.ui.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.highestaim.newsapp.enums.NewsPageFragmentsKeys
import com.highestaim.newsapp.ui.fragment.FavoriteFragment
import com.highestaim.newsapp.ui.fragment.NewsFragment

class NewsPageAdapter (
    fragmentActivity: FragmentActivity,
    private val tabTitles: Array<String>,
    private val fragments: HashMap<NewsPageFragmentsKeys, Fragment>

) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int  = tabTitles.size

    override fun createFragment(position: Int): Fragment =  when (position){
        0 -> fragments[NewsPageFragmentsKeys.NEWS] ?: NewsFragment()
        1 -> fragments[NewsPageFragmentsKeys.FAVORITE] ?: FavoriteFragment()
        else -> throw IllegalStateException()
    }

}