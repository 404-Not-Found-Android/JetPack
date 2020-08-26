package com.example.jetpack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.jetpack.R
import com.example.jetpack.base.fragment.BaseFragment
import com.example.jetpack.databinding.FragmentHomePagerBinding
import com.example.jetpack.ui.news.NewsFragment
import com.google.android.material.tabs.TabLayout

/**
 * Description :
 * CreateTime  : 2020/8/26
 */
class HomePagerFragment : BaseFragment<FragmentHomePagerBinding>() {

    companion object {
        val TITLES =
            listOf("头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚")
        val TYPE = listOf(
            "top",
            "shehui",
            "guonei",
            "guoji",
            "yule",
            "tiyu",
            "junshi",
            "keji",
            "caijing",
            "shishang"
        )
    }

    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentHomePagerBinding.inflate(inflater,container,false)
        return dataBinding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_pager
    }

    override fun initView() {
        TITLES.forEach {
            dataBinding.tabLayout.addTab(dataBinding.tabLayout.newTab().setText(it))
        }
        pagerAdapter = FragmentPagerAdapter(childFragmentManager)
        dataBinding.viewPager.adapter = pagerAdapter
        dataBinding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        dataBinding.tabLayout.setupWithViewPager(dataBinding.viewPager)
    }

    override fun initViewModel() {

    }

    override fun initListener() {

    }

    class FragmentPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){
        override fun getItem(position: Int): Fragment {
            val newsFragment = NewsFragment()
            newsFragment.arguments = Bundle().apply {
                putString(NewsFragment.BUNDLE_TYPE,"${TYPE[position]}")
            }
            return newsFragment
        }

        override fun getCount(): Int = TYPE.size

        override fun getPageTitle(position: Int): CharSequence? {
            return "${TITLES[position]}"
        }
    }

}