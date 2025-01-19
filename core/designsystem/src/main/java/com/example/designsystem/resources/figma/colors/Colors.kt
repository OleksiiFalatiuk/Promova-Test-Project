package com.example.designsystem.resources.figma.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.core.designsystem.R

object FigmaColors

@Composable
@ReadOnlyComposable
fun FigmaColors.basicWhite(): Color = colorResource(id = R.color.basic_white)

@Composable
@ReadOnlyComposable
fun FigmaColors.basicBlack(): Color = colorResource(id = R.color.basic_black)

@Composable
@ReadOnlyComposable
fun FigmaColors.basicBackground(): Color = colorResource(id = R.color.basic_background)

@Composable
@ReadOnlyComposable
fun FigmaColors.basicPurple(): Color = colorResource(id = R.color.basic_purple)

@Composable
@ReadOnlyComposable
fun FigmaColors.basicMint(): Color = colorResource(id = R.color.basic_mint)

@Composable
@ReadOnlyComposable
fun FigmaColors.basicBlue(): Color = colorResource(id = R.color.basic_blue)