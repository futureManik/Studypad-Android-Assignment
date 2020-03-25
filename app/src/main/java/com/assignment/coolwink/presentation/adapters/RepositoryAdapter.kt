package com.assignment.coolwink.presentation.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.assignment.coolwink.R
import com.assignment.coolwink.databinding.RepositoryItemBinding
import com.assignment.coolwink.domain.GitRepositoriesModel
import com.assignment.coolwink.presentation.WebViewActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.repository_item.view.*


class RepositoryAdapter(
    val mContext: Context,
    private val mRepoItem: MutableList<GitRepositoriesModel.Item>
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RepositoryViewHolder {
        val binding = DataBindingUtil.inflate<RepositoryItemBinding>(
            LayoutInflater.from(
                viewGroup.context
            ), R.layout.repository_item, viewGroup, false
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(photoGalleryViewHolder: RepositoryViewHolder, position: Int) {
        photoGalleryViewHolder.itemView.rv_parentView.setOnClickListener {
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("url", mRepoItem[position].htmlUrl)
            mContext.startActivity(intent)
        }
        return photoGalleryViewHolder.bind(mRepoItem[position])
    }


    override fun getItemCount(): Int {
        return mRepoItem.size
    }

    inner class RepositoryViewHolder(itemView: RepositoryItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val viewBinding by lazy {
            itemView
        }

        fun bind(item: GitRepositoriesModel.Item) {
            Glide.with(itemView.imageView.context)
                .setDefaultRequestOptions(RequestOptions().circleCrop())
                .load(item.avatarUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(itemView.imageView)
            viewBinding.setVariable(BR.repository, item)
            viewBinding.executePendingBindings()
        }
    }
}