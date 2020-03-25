package com.assignment.coolwink.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.assignment.coolwink.BR
import com.assignment.coolwink.R
import com.assignment.coolwink.databinding.ActivityMainBinding
import com.assignment.coolwink.domain.GitRepositoriesModel
import com.assignment.coolwink.domain.InternetStatusImpl
import com.assignment.coolwink.presentation.activity.BaseActivity
import com.assignment.coolwink.presentation.adapters.RepositoryAdapter
import com.assignment.coolwink.presentation.adapters.VerticalSpaceItemDecoration
import com.assignment.coolwink.presentation.viewmodel.MainViewModel
import com.assignment.coolwink.presentation.viewmodel.ViewModelFactory
import com.assignment.coolwink.usecases.Instructors
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_place_holder.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    @Inject
    lateinit var intractors: Instructors

    @Inject
    lateinit var internetStatusImpl: InternetStatusImpl
    private var mRepositoryAdapter: RepositoryAdapter? = null
    private var mRepoItems = mutableListOf<GitRepositoriesModel.Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.getRepositories()
        shimmer_view_container.startShimmerAnimation()
        handleLiveData()
    }


    override fun initializeViews(bundle: Bundle?) {
        AndroidInjection.inject(this)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        rvRepositories?.layoutManager = linearLayoutManager
        mRepositoryAdapter = RepositoryAdapter(this, mRepoItems)
        rvRepositories?.adapter = mRepositoryAdapter
        rvRepositories?.addItemDecoration(VerticalSpaceItemDecoration(3))
        (rvRepositories.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        rvRepositories?.addItemDecoration(
            DividerItemDecoration(
                rvRepositories.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel(): MainViewModel {
        val viewModelFactory = ViewModelFactory(
            this.application,
            intractors, internetStatusImpl
        )
        return ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewmodel
    }

    private fun handleLiveData() {
        viewModel?.repoResponseData?.observe(this,
            Observer<MutableList<GitRepositoriesModel.Item>> { repoItems ->
                repoItems?.let {
                    if (repoItems.isEmpty()) {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.no_data_found),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    mRepoItems.clear()
                    shimmer_view_container.stopShimmerAnimation()
                    mRepoItems.addAll(it)
                    mRepositoryAdapter?.notifyDataSetChanged()
                }
            })
    }


}
