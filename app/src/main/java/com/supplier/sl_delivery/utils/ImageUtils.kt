package com.supplier.sl_delivery.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.io.ByteArrayOutputStream

/**
 * Created by Aurora on 2020-04-10.
 */
class ImageUtils {
    companion object {
        fun downloadImageWithoutCache(mActivity: Activity, url: String, imageView: ImageView) {
            Glide.with(mActivity.baseContext)
                .load(url)
                .apply(RequestOptions.skipMemoryCacheOf(false))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                .into(imageView)
        }

        fun displayImageWithURI(mActivity: Activity, uri: Uri, imageView: ImageView) {
            Glide.with(mActivity.baseContext)
                .load(uri)
                .into(imageView)
        }

        fun compressBitMap(source: Bitmap): Bitmap {
            if (source.byteCount > 512) {
                var byteArrayOutPutStream = ByteArrayOutputStream()
                source.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutPutStream)
                var byteArray = byteArrayOutPutStream.toByteArray()
                return BitmapFactory.decodeByteArray(byteArray, 0, arrayOf(byteArray).size)
            }
            return source
        }
    }
}
