package com.michael.actorlist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.michael.actorlist.R
import com.michael.actorlist.ui.theme.Shapes

@Composable
fun ActorListItem(
    id: Long,
    name: String,
    photoUrl: String,
    dateOfBirth: String,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {navigateToDetail(id)}
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = stringResource(R.string.actors_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp)
                .clip(Shapes.medium)
        )
        Column(
            modifier = modifier
                .padding(start = 16.dp, end = 8.dp)
                .fillMaxWidth()
        ){
            Text(
                text = name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = modifier
            )
            Text(
                text = "Birth Date: $dateOfBirth",
                fontSize = 14.sp,
                modifier = modifier
            )
        }
    }
}