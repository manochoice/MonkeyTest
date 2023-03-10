package com.choice.recipedetail.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.choice.compose.*
import com.choice.design.util.UiEvent
import com.choice.model.Recipe
import com.choice.recipedetail.ui.components.Description
import com.choice.recipedetail.ui.components.Header
import com.choice.recipedetail.ui.compose.RecipeTopAppBarAnimation
import com.choice.recipedetail.util.RecipeDetailEvent
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.flow.collectLatest


@Composable
fun RecipeDetailUI(
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LazyColumn {
        item { RecipeHeader(viewModel, navController) }
        item { RecipeDescription(state.recipe) }
    }

}

@Composable
fun RecipeHeader(
    viewModel: RecipeDetailViewModel,
    navController: NavController
) {

    val state = viewModel.state

    Header(
        isFavorite = state.recipe.favorite,
        urlImage = state.recipe.image,
        navController = navController
    ) {
        viewModel.onEvent(RecipeDetailEvent.OnFavoriteChange)
    }
}

@Composable
fun RecipeDescription(
    recipe: Recipe
) {
    Description(
        title = recipe.title,
        author = recipe.publisher,
        date = recipe.dateAdded,
        ingredients = recipe.ingredients
    )
}

