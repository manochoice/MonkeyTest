package com.choice.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val mediumSmall: Dp = 10.dp,
    val extraMedium: Dp = 16.dp,
    val medium: Dp = 24.dp,
    val large: Dp = 32.dp,
    val largeSmall: Dp = 56.dp,
    val largeMedium: Dp = 64.dp,
    val extraLarge: Dp = 105.dp,
    val exxtraLarge: Dp = 110.dp,
    val exxxtraLarge: Dp = 190.dp,
    val exxxztraLarge: Dp = 200.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }