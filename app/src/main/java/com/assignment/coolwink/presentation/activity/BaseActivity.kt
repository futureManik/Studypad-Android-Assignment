package com.assignment.coolwink.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.assignment.coolwink.R
import com.assignment.coolwink.presentation.viewmodel.BaseViewModel

abstract class BaseActivity<BindingClass : ViewDataBinding, VM : BaseViewModel> :
    AppCompatActivity() {
    protected var viewModel: VM? = null

    private var mViewDataBinding: BindingClass? = null

    /**
     * initialise toolbar and other stuff
     */
    abstract fun initializeViews(bundle: Bundle?)

    /**
     * abstract impl for get layout id
     */
    protected abstract fun getLayoutId(): Int

    /** abstract impl for init view model
     */
    protected abstract fun initViewModel(): VM

    /**
     * get binding variable
     */
    protected abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initializeViews(savedInstanceState)
        viewModel = initViewModel()
        if (getBindingVariable() >= 0)
            mViewDataBinding?.setVariable(getBindingVariable(), viewModel)
        mViewDataBinding?.executePendingBindings()

    }

    override fun onDestroy() {
        super.onDestroy()
        mViewDataBinding = null
    }

}