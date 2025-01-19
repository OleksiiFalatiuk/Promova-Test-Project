package com.example.designsystem.components.pager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.TabRow
import com.example.designsystem.components.pager.dimens.PagerDimens
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicBlue
import com.example.designsystem.resources.figma.colors.basicMint
import com.example.designsystem.resources.figma.colors.basicPurple
import com.example.designsystem.resources.figma.colors.basicWhite
import com.example.designsystem.resources.figma.typography.FigmaTypography

@Composable
fun TabRow(
    modifier: Modifier = Modifier,
    tabRowModifier: Modifier = Modifier,
    pages: List<String>,
    currentPageIndex: Int = 0,
    selectedTextStyle: TextStyle = FigmaTypography.bodyMedium,
    unselectedTextStyle: TextStyle = FigmaTypography.bodyMedium,
    selectedTextColor: Color = FigmaColors.basicWhite(),
    unselectedTextColor: Color = FigmaColors.basicMint(),
    containerColor: Color = FigmaColors.basicPurple(),
    onTabChange: (index: Int) -> Unit = {},
    enabled: Boolean = true
) {
    Column(modifier = modifier) {
        TabRow(
            modifier = tabRowModifier,
            selectedTabIndex = currentPageIndex,
            containerColor = containerColor,
            contentColor = FigmaColors.basicPurple(),
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[currentPageIndex])
                        .height(PagerDimens.indicatorHeight),
                    color = FigmaColors.basicBlue()
                )
            },
            divider = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.height(PagerDimens.indicatorHeight),
                    color = FigmaColors.basicPurple()
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                val selected = currentPageIndex == index

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.spacingNormal)
                        .clickable(
                            onClick = {
                                onTabChange(index)
                            },
                            enabled = enabled,
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = if (selected) selectedTextColor else unselectedTextColor,
                        style = if (selected) selectedTextStyle else unselectedTextStyle
                    )
                }
            }
        }
    }
}