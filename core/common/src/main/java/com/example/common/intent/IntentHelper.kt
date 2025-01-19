package com.example.common.intent

import android.content.Context
import androidx.core.app.ShareCompat

object IntentHelper {
    private const val PLAIN_TEXT = "text/plain"

    fun sharePlainText(context: Context, text: String) {
        context.startActivity(
            ShareCompat
                .IntentBuilder(context)
                .setText(text)
                .setType(PLAIN_TEXT)
                .createChooserIntent()
        )
    }
}