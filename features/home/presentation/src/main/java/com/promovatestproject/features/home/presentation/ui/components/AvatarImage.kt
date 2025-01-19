package com.promovatestproject.features.home.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicBackground
import com.promovatestproject.features.home.presentation.dimens.HomeDimens
import com.example.core.localization.R as LocalizableResources

@Composable
internal fun AvatarImage(
    modifier: Modifier = Modifier,
    imageSize: Dp = HomeDimens.userAvatarSize,
    avatarImage: String?,
) {
    Box(
        modifier = modifier
            .size(imageSize)
            .clip(CircleShape)
            .background(
                color = FigmaColors.basicBackground(),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = avatarImage,
            contentDescription = stringResource(id = LocalizableResources.string.user_avatar_img_desc),
            contentScale = ContentScale.Crop
        )
    }
}