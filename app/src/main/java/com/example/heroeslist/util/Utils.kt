package com.example.heroeslist.util

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.heroeslist.R
import com.example.heroeslist.data.network.HeroesApi
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun getMD5(timeStamp: String): String {
    try {
        val md5Instance = MessageDigest.getInstance("MD5")

        val messageDigest = md5Instance.digest(
            timeStamp.toByteArray() +
                    HeroesApi.PRIVATE_KEY.toByteArray() +
                    HeroesApi.PUBLIC_KEY.toByteArray()
        )

        val no = BigInteger(1, messageDigest)
        var hash = no.toString(16)
        while (hash.length < 32) {
            hash = "0$hash"
        }

        return hash
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}

fun isConnected(): Boolean {
    val runtime = Runtime.getRuntime()

    return try {
        val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
        val exitValue = ipProcess.waitFor()
        return exitValue == 0
    } catch (e: IOException) {
        e.printStackTrace()
        false
    } catch (e: InterruptedException) {
        e.printStackTrace()
        false
    }
}

fun showSnackbarConnection(view: View, context: Context) {
    Snackbar.make(view, context.resources.getString(R.string.disconnected), Snackbar.LENGTH_INDEFINITE).show()
}