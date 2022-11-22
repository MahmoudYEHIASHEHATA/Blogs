package com.shahry.feature.core

import android.graphics.drawable.Drawable
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import javax.annotation.Nullable

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageURL", "progressLoading", requireAll = false)
    fun bindImageURL(view: ImageView, url: String?, @Nullable progressLoading: ProgressBar?) {
        progressLoading?.visibility = VISIBLE
        if (url != null && url.isNotEmpty()) {
            Glide.with(view.context)
                .load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressLoading?.visibility = GONE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressLoading?.visibility = GONE
                        return false
                    }
                })
                .apply(
                    RequestOptions()
                        .centerInside()
                )
                .into(view)
        }
    }
}
