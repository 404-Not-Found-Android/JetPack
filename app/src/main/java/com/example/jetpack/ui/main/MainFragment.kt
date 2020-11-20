package com.example.jetpack.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentMainBinding
import com.example.jetpack.mvvm.base.fragment.BaseFragment

/**
 * Description :
 * CreateTime  : 2020/9/15
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentMainBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }

    override fun initListener() {
        dataBinding.btnGoToNews.setOnClickListener {
            findNavController().navigate(R.id.action_global_navigationGoHomeFragment)
        }
        dataBinding.btnGotoGitHub.setOnClickListener {
            findNavController().navigate(R.id.action_global_navigationGoGitHubFragment)
        }
    }
}