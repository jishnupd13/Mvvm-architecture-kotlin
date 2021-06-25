package com.app.bygclite.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import es.dmoral.toasty.Toasty
import javax.inject.Inject

/** Created by Jishnu P Dileep on 27-05-2021 */
class StylishToastyUtils @Inject constructor(val mContext: Context) {

    fun showSuccessMessage(msg: String) {
        Toasty.success(mContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showErrorMessage(msg: String) {
        Toasty.error(mContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showInfoMessage(msg: String) {
        Toasty.info(mContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showWarningMessage(msg: String) {
        Toasty.warning(mContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showNormalMessage(msg: String) {
        Toasty.normal(mContext, msg).show();
    }

    fun showNormalWithIconMessage(msg: String, drawable: Drawable) {
        Toasty.normal(mContext, msg, drawable).show();
    }

    fun showYourCustomToast(
        msg: String,
        drawable: Int,
        color: Int,
        duration: Int,
        withIcon: Boolean,
        shouldTint: Boolean
    ) {
        Toasty.custom(mContext, msg, drawable, color, duration, withIcon, shouldTint)
    }
}