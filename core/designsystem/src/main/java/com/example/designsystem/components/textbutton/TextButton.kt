package com.example.designsystem.components.textbutton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicPurple
import com.example.designsystem.resources.figma.typography.FigmaTypography

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = FigmaColors.basicPurple(),
    textStyle: TextStyle = FigmaTypography.bodyMedium,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(Dimens.spacingTiny)
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle
        )
    }
}