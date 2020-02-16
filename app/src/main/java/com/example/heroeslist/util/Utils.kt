package com.example.heroeslist.util

import android.util.Log
import java.lang.RuntimeException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

const val PUBLIC_KEY = "8f4214a98e94658ffa0449436e102b43"
const val PRIVATE_KEY = "bbbee0bfb47fc353f88793f275dc437921608ed1"
const val BASE_URL = "https://gateway.marvel.com/v1/public/"

fun getMD5(timeStamp: String): String {
    try {
        val md5Instance = MessageDigest.getInstance("MD5")

        val messageDigest = md5Instance.digest(
            timeStamp.toByteArray() +
                    PRIVATE_KEY.toByteArray() +
                    PUBLIC_KEY.toByteArray()
        )

        val no = BigInteger(1, messageDigest)
        var hash = no.toString(16)
        while (hash.length < 32) {
            hash = "0$hash"
        }

        Log.d("15022020", hash)
        return hash
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}