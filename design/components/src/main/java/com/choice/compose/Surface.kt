package com.choice.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeySurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(color),
    border: BorderStroke? = null,
    elevation: Dp = MonkeyTheme.elevation.default,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        shape = shape,
        color = color,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        content = content
    )
}