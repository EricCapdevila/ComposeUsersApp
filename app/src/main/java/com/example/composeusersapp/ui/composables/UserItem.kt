package com.example.composeusersapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeusersapp.R.drawable
import com.example.composeusersapp.ui.UserUI

@Composable
fun UserItem(
    user: UserUI
) {
    Column(modifier = Modifier.wrapContentHeight()) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            UserImage(user.picture)
            UserTextFields(user.name, user.email)
            IconImage()
        }
        Divider(
            modifier = Modifier.padding(start = 100.dp),
            thickness = 3.dp,
            color = Color.Gray
        )
    }
}

@Composable
fun UserImage(source: String) {
    Image(
        painter = painterResource(id = drawable.ic_launcher_background),
        contentDescription = "example",
        modifier = Modifier
            .padding(10.dp)
            .clip(CircleShape)
            .size(80.dp)
            .border(5.dp, Color.White, CircleShape)

    )
}

@Composable
fun IconImage() {
    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
}

@Composable
fun UserTextFields(name: String, email: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 50.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(name)
        Spacer(modifier = Modifier.height(10.dp))
        Text(email)
    }
}