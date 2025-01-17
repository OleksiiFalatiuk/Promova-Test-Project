package com.example.designsystem.resources.figma.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R

object FigmaTypography {
    val bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 14.0.sp,
        letterSpacing = (1.25).sp,
        lineHeight = 16.0.sp
    )
}