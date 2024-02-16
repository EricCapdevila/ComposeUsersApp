package com.example.composeusersapp.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeusersapp.ui.UserUI
import com.example.composeusersapp.ui.theme.ComposeUsersAppTheme

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
            horizontalArrangement = Arrangement.End
        ) {
            UserImage(user.picture)
            UserTextFields(user.name, user.email)
            IconImage()
        }
        Divider(
            modifier = Modifier.padding(start = 100.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserImage(source: String) {

    GlideImage(
        model = source, contentDescription = "User Profile", modifier = Modifier
            .padding(10.dp)
            .size(70.dp)
            .clip(CircleShape)
    )

}

@Composable
fun UserTextFields(name: String, email: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 50.dp, start = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = name)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = email, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun IconImage() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(end = 5.dp), contentAlignment = Alignment.CenterEnd
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
    }
}

// PREVIEW ________________________________________

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    ComposeUsersAppTheme {
        UserItem(UserUI("Mr. Name Surname", "names@hotmail.es", "", "", "", ""))
    }
}