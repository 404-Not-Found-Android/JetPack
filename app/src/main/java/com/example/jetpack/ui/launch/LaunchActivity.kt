package com.example.jetpack.ui.launch

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jetpack.R
import com.example.jetpack.ui.fragment.NewsFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_launch.*

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
class LaunchActivity : AppCompatActivity() {
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

    private val fragments: MutableList<Fragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        for (i in TITLES.indices) {
            tabLayout.addTab(tabLayout.newTab().setText(TITLES[i]))
            fragments.add(NewsFragment.newInstance(TYPE[i]))
        }
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.setupWithViewPager(viewPager, false)
        viewPager.adapter = FragmentAdapter(supportFragmentManager)
        viewPager.currentItem = 0
    }

    inner class FragmentAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        override fun getPageTitle(position: Int): CharSequence? {
            return TITLES[position]
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

    }
}