package com.app.bygclite.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.bygclite.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/** Created by Jishnu P Dileep on 27-05-2021 */

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .timeout(60000)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .error(R.drawable.placeholder)
        .into(view)
}
