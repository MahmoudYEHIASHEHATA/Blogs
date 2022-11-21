package com.shahry.feature.ui.detail

import com.shahry.base.BaseViewHolder
import com.shahry.feature.databinding.RowPostItemLayoutBinding
import com.shahry.feature.model.PostUiModel


/**
 * ViewHolder class for Post
 */
class PostViewHolder constructor(
    private val binding : RowPostItemLayoutBinding,
    private val click : ((PostUiModel?) -> Unit)? = null
) : BaseViewHolder<PostUiModel, RowPostItemLayoutBinding>(binding) {


    init {
        binding.crdAuthor.setOnClickListener {
            click?.invoke(getRowItem())
        }
    }

    override fun bind() {

        getRowItem()?.let {
            binding.post = it
            binding.executePendingBindings()
        }
    }
}