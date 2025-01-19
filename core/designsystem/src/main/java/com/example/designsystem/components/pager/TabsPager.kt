package com.example.designsystem.components.pager

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicMint
import com.example.designsystem.resources.figma.colors.basicPurple
import com.example.designsystem.resources.figma.colors.basicWhite
import com.example.designsystem.resources.figma.typography.FigmaTypography
import kotlinx.coroutines.launch

@Composable
fun TabsPager(
    modifier: Modifier = Modifier,
    tabRowModifier: Modifier = Modifier,
    pagerModifier: Modifier = Modifier,
    pages: List<String>,
    initialPage: Int = 0,
    pagerState: PagerState = rememberPagerState(initialPage, pageCount = { pages.size }),
    selectedTextStyle: TextStyle = FigmaTypography.bodyMedium,
    unselectedTextStyle: TextStyle = FigmaTypography.bodyMedium,
    selectedTextColor: Color = FigmaColors.basicWhite(),
    unselectedTextColor: Color = FigmaColors.basicMint(),
    containerColor: Color = FigmaColors.basicPurple(),
    onTabChange: (index: Int) -> Unit = {},
    enabled: Boolean = true,
    beyondBoundsPageCount: Int = PagerDefaults.BeyondViewportPageCount,
    pageSpacing: Dp = Dimens.zeroDp,
    content: @Composable ((page: Int) -> Unit) = {}
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onTabChange(page)
        }
    }

    Column(modifier = modifier) {
        TabRow(
            tabRowModifier = tabRowModifier,
            pages = pages,
            currentPageIndex = pagerState.currentPage,
            selectedTextStyle = selectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            selectedTextColor = selectedTextColor,
            unselectedTextColor = unselectedTextColor,
            containerColor = containerColor,
            enabled = enabled,
            onTabChange = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )

        HorizontalPager(
            modifier = pagerModifier,
            state = pagerState,
            pageSpacing = pageSpacing,
            beyondViewportPageCount = beyondBoundsPageCount,
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                state = pagerState,
                orientation = Orientation.Horizontal
            )
        ) { pageIndex ->
            content(pageIndex)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabsPagerPreview() {
    TabsPager(
        pages = listOf("FILMS", "FAVOURITES")
    )
}