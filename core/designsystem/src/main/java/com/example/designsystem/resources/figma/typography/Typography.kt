package com.example.designsystem.resources.figma.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R

object FigmaTypography {
    val bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 28.0.sp,
        letterSpacing = (0.15).sp,
        lineHeight = 32.0.sp
    )

    val bodyBig = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 20.0.sp,
        letterSpacing = (0.15).sp,
        lineHeight = 24.0.sp
    )

    val bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 14.0.sp,
        letterSpacing = (1.25).sp,
        lineHeight = 16.0.sp
    )

    val bodyHalfMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 14.0.sp,
        letterSpacing = (0.1).sp,
        lineHeight = 24.0.sp
    )

    val descriptionMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontSize = 14.0.sp,
        letterSpacing = (0.25).sp,
        lineHeight = 20.0.sp
    )
}