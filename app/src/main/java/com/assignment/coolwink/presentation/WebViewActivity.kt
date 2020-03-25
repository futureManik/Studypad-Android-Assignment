package com.assignment.coolwink.presentation

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.assignment.coolwink.BR
import com.assignment.coolwink.R
import com.assignment.coolwink.databinding.ActivityWebViewBinding
import com.assignment.coolwink.domain.InternetStatusImpl
import com.assignment.coolwink.presentation.activity.BaseActivity
import com.assignment.coolwink.presentation.viewmodel.ViewModelFactory
import com.assignment.coolwink.presentation.viewmodel.WebViewModel
import com.assignment.coolwink.usecases.Instructors
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_web_view.*
import javax.inject.Inject

class WebViewActivity : BaseActivity<ActivityWebViewBinding, WebViewModel>() {
    @Inject
    lateinit var intractors: Instructors

    @Inject
    lateinit var internetStatusImpl: InternetStatusImpl
    override fun initializeViews(bundle: Bundle?) {
        AndroidInjection.inject(this)
        webview.settings.javaScriptEnabled = true
        val webViewClient = WebViewClient()
        webview.webViewClient = webViewClient
        webview.loadUrl(intent.getStringExtra("url"))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }

    override fun initViewModel(): WebViewModel {
        val viewModelFactory = ViewModelFactory(
            this.application,
            intractors, internetStatusImpl
        )
        return ViewModelProvider(this, viewModelFactory).get(WebViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewmodel
    }


}