package com.example.coctails.utils

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

suspend fun extractUri(uri: Uri?, context: Context): String {
    if (uri == null) {
        return ""
    }
    context.contentResolver.openInputStream(uri)?.use { inputStream ->
        val bytes = inputStream.readBytes()
        val title = uri.lastPathSegment ?: "unknown"
        val file = File(context.filesDir, title)

        withContext(Dispatchers.IO) {
            FileOutputStream(file).use {
                it.write(bytes)
            }
        }
        return file.toUri().toString()
    }
    return ""
}