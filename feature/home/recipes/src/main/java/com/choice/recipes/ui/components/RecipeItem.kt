package com.choice.recipes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.choice.compose.*
import com.choice.model.Recipe
import com.choice.theme.MonkeyTheme
import java.util.*

@Composable
fun RecipeItem(
    modifier: Modifier,
    item: Recipe?,
    onFavoriteClick: () -> Unit
) {

    item?.let {
        MonkeyColumn(
            modifier = modifier.padding(MonkeyTheme.spacing.small),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Recipe(it, onFavoriteClick)
        }
    }
}


@Composable
private fun Recipe(
    item: Recipe,
    onFavoriteClick: () -> Unit,
) {
    MonkeyImageRecipe(
        url = item.image,
        visible = item.favorite,
        modifier = Modifier.aspectRatio(4f / 6f),
        favoriteClick = onFavoriteClick
    )
    RecipeText(
        item.title ?: "Title not found",
        item.dateAdded ?: "00/00/0000",
        item.publisher ?: "Not found publisher",
        modifier = Modifier.paddingFromBaseline(
            top = MonkeyTheme.spacing.extraSmall
        )
    )
}

@Composable
private fun RecipeText(
    title: String,
    date: String,
    by: String,
    modifier: Modifier = Modifier,
) {
    MonkeyDefaultColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(Modifier.height(MonkeyTheme.spacing.small))
        Text(
            modifier = Modifier
                .height(MonkeyTheme.spacing.small),
            text = date,
            style = MonkeyTheme.typography.subtitle2.copy(
                color = MonkeyTheme.colors.primary
            ),
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.mediumSmall))
        Text(
            text = title,
            style = MonkeyTheme.typography.body2.copy(
                color = MonkeyTheme.colors.onSurface
            ),
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.mediumSmall))
        Text(
            modifier = Modifier
                .height(MonkeyTheme.spacing.medium),
            text = "by ${by.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}",
            style = MonkeyTheme.typography.subtitle2.copy(
                color = MonkeyTheme.colors.onSurface.copy(
                    alpha = 0.5f
                )
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}