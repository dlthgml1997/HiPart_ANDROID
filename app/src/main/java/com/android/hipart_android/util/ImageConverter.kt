package com.android.hipart_android.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.InputStream


/**
 * Created by TakHyeongMin on 2019-07-08.
 */
class ImageConverter(var context: Context) {

    fun imgToByteArrayStream(uri: Uri): ByteArrayOutputStream {
        val options = BitmapFactory.Options()
        val inputStream: InputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        val byteArrayOutputStream = ByteArrayOutputStream()
        // compress
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        return byteArrayOutputStream
        val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
    }

    fun drawableToByteArrayStream(drawable: Drawable): ByteArray? {
        val bitmap = (drawable as BitmapDrawable).bitmap

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return byteArray
    }

}