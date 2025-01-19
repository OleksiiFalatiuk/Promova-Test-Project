package com.promovatestproject.features.splash.presentation.ui.route

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicWhite
import com.example.core.designsystem.R
import com.example.designsystem.resources.dimens.Dimens
import com.example.core.localization.R as LocalizableResources

@Composable
internal fun SplashRoute() {
    SplashScreen()
}

@Composable
internal fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FigmaColors.basicWhite())
            .padding(horizontal = Dimens.spacingNormal),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.promova_test_splash_image),
            contentDescription = stringResource(
                id = LocalizableResources.string.splash_screen_image_desc
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}