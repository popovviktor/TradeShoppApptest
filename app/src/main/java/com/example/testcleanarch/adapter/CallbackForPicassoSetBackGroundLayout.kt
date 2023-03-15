package com.example.testcleanarch.adapter

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Callback
import java.lang.Exception

class CallbackForPicassoSetBackGroundLayout(private val costr: ConstraintLayout, private val im: ImageView):
    Callback {
    override fun onSuccess() {
        costr.setBackgroundDrawable(im.drawable)
    }

    override fun onError(e: Exception?) {

    }
}