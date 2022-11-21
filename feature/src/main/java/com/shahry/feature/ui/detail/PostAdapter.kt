package com.shahry.feature.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.shahry.base.BaseRecyclerAdapter
import com.shahry.feature.databinding.RowPostItemLayoutBinding
import com.shahry.feature.model.PostUiModel
import com.shahry.feature.ui.detail.PostViewHolder

/**
 * Adapter class for RecyclerView
 */
class PostAdapter constructor(
    private val clickFunc: ((PostUiModel?) -> Unit)? = null
) : BaseRecyclerAdapter<PostUiModel, RowPostItemLayoutBinding, PostViewHolder>(
    PostItemDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = RowPostItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return PostViewHolder(binding = binding, click = clickFunc)

    }

}

class PostItemDiffUtil : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }
}