package com.michael.actorlist.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.michael.actorlist.R

@Composable
fun AppBarHome(
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.actor_list))
        },
        actions = {
            IconButton(onClick = {
                navigateToProfile()
            }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = stringResource(R.string.about_page))
            }
        },
        modifier = modifier
    )
}