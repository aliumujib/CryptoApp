package com.aliumujib.cryptoapp.remote.datasource

import android.content.Context
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import java.io.InputStream
import java.nio.charset.Charset

inline fun <reified T> parseJson(moshi: Moshi, json: String): T? {
    return moshi.adapter(T::class.java).fromJson(json)
}

abstract class FileDataSource<T>  constructor(private val context: Context) {

    protected fun readFileContents(fileName: String): String {
        val inputStream: InputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.defaultCharset())
    }

    abstract fun fetch() : T?
}
