package com.puzzling.puzzlingaos.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Context.getClipboardManager(): ClipboardManager {
    return getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
}

fun String.copyToClipboard(context: Context) {
    val clipBoard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("COPY_TEXT", this)
    clipBoard.setPrimaryClip(clipData)
}