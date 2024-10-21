package com.example.coctails.utils

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

suspend fun extractUri(uri: Uri?, context: Context): String {
    return withContext(Dispatchers.IO) {
        if (uri == null) {
            return@withContext ""
        }
        val title = uri.lastPathSegment ?: "unknown"
        val file = File(context.filesDir, title)
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val bytes = inputStream.readBytes()
            FileOutputStream(file).use {
                it.write(bytes)
            }
        }
        file.toUri().toString()
    }
}