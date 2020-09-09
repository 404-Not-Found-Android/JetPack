package com.example.jetpack.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentLoadNewsPageBinding
import com.example.jetpack.model.NewsModel
import com.example.mvvm.base.fragment.BaseFragment
import com.just.agentweb.AgentWeb

/**
 * Description :
 * CreateTime  : 2020/8/26
 */
class LoadNewsPageFragment : BaseFragment<FragmentLoadNewsPageBinding>() {


    companion object{
        const val BUNDLE_TITLE = "news"
    }

    lateinit var newsModel: NewsModel
    lateinit var agentWeb: AgentWeb

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentLoadNewsPageBinding.inflate(inflater,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsModel = arguments?.getParcelable(BUNDLE_TITLE) ?: return
        dataBinding.title = newsModel.title
        agentWeb = AgentWeb.with(requireActivity())
            .setAgentWebParent(view as LinearLayout, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb().ready().go(newsModel.url)
        //回退栈
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!agentWeb.back()) {
                    findNavController().popBackStack()
                }
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_load_news_page
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }

    override fun initListener() {
        dataBinding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }



}