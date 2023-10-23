package com.codebook.petfacts

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.io.File

object Utils {
    const val CAT = "Cat"
    const val DOG = "Dog"

    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nc = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nc) ?: return false
        val result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    fun File.writeBitmap(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        quality: Int
    ) {
        outputStream().use { out ->
            bitmap.compress(format, quality, out)
            out.flush()
            out.close()
        }
    }
}