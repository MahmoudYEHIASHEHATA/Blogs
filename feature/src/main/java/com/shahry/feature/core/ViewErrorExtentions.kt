package com.shahry.feature.core

import android.app.AlertDialog
import android.content.Context
import com.shahry.feature.R



fun Context.showErrorDialog(message: String, retryAction: (() -> Unit)?) {
    AlertDialog.Builder(this)
        .setTitle("")
        .setMessage(message)
        .also {
            retryAction?.let { retryAction ->
                it.setPositiveButton(
                    R.string.try_again
                ) { _,_->
                    retryAction.invoke()
                }
            }
        }
        .setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false)
        .show()
}
