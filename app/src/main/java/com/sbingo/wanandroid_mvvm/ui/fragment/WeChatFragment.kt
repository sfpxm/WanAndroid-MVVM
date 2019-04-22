package com.sbingo.wanandroid_mvvm.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sbingo.wanandroid_mvvm.R
import com.sbingo.wanandroid_mvvm.adapter.WeChatViewPagerAdapter
import com.sbingo.wanandroid_mvvm.base.BaseFragment
import com.sbingo.wanandroid_mvvm.data.http.HttpManager
import com.sbingo.wanandroid_mvvm.repository.WeChatRepository
import com.sbingo.wanandroid_mvvm.viewmodel.WeChatViewModel
import kotlinx.android.synthetic.main.fragment_wechat.*

/**
 * Author: Sbingo666
 * Date:   2019/4/15
 */
class WeChatFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = WeChatRepository(HttpManager.getInstance())
                return WeChatViewModel(repository) as T
            }
        })
            .get(WeChatViewModel::class.java)
    }

    private val adapter by lazy {
        WeChatViewPagerAdapter(childFragmentManager)
    }

    override var layoutId = R.layout.fragment_wechat

    override fun initData() {
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun subscribeUi() {
        handleData(viewModel.wxChapters) {
            adapter.setData(it)
            viewPager.offscreenPageLimit = it.size
        }
    }
}