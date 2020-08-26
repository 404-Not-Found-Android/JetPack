package com.example.jetpack.ui.news

import android.os.Bundle
import android.view.KeyEvent
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityNewsPageBinding
import com.example.jetpack.model.NewsModel
import com.just.agentweb.AgentWeb

/**
 * Description :
 * CreateTime  : 2020/8/26
 */
class NewsPageActivity : AppCompatActivity() {
    companion object {
        const val BUNDLE_TITLE = "news"
    }

    lateinit var newsModel: NewsModel
    lateinit var binding: ActivityNewsPageBinding
    lateinit var agentWeb: AgentWeb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_page)
        newsModel = intent.extras?.getParcelable(BUNDLE_TITLE) ?: return
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.title = newsModel.title
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(binding.linearLayout, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb().ready().go(newsModel.url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}