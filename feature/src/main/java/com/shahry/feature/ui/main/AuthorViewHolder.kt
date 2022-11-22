package com.shahry.feature.ui.main

import com.shahry.base.BaseViewHolder
import com.shahry.feature.databinding.RowAuthorItemLayoutBinding
import com.shahry.feature.model.AuthorUiModel


/**
 * ViewHolder class for Author
 */
class AuthorViewHolder constructor(
    private val binding : RowAuthorItemLayoutBinding,
    private val click : ((AuthorUiModel?) -> Unit)? = null
) : BaseViewHolder<AuthorUiModel, RowAuthorItemLayoutBinding>(binding) {


    init {
        binding.crdAuthor.setOnClickListener {
            click?.invoke(getRowItem())
        }
    }

    override fun bind() {

        getRowItem()?.let {
            binding.author = it
            binding.executePendingBindings()
        }
    }
}