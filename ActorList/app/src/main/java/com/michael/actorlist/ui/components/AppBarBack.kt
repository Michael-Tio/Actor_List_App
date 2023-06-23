package com.michael.actorlist.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.michael.actorlist.R

@Composable
fun AppBarBack(
    pageTitle: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navigateBack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back_to_home))
            }
        },
        title = {
            Text(text = pageTitle)
        },
        modifier = modifier
    )
}