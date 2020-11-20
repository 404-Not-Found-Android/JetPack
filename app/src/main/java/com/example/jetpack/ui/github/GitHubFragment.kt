package com.example.jetpack.ui.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentGithubBinding
import com.example.jetpack.mvvm.base.fragment.BaseFragment

/**
 * Description :
 * CreateTime  : 2020/9/15
 */
class GitHubFragment : BaseFragment<FragmentGithubBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentGithubBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_github
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }

    override fun initListener() {

    }
}