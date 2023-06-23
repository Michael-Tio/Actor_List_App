package com.michael.actorlist.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.michael.actorlist.R
import com.michael.actorlist.ui.components.AppBarBack

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        AppBarBack(stringResource(R.string.profile) ,navigateBack)
        Spacer(modifier = modifier.size(32.dp))
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = stringResource(R.string.profile_image),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(200.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.michael_andreas_prasetio),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        Text(
            text = stringResource(R.string.mtio161_gmail_com),
            fontSize = 18.sp,
            modifier = modifier
        )
    }
}