package com.example.composeusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeusersapp.ui.UserUI
import com.example.composeusersapp.ui.UsersViewModel
import com.example.composeusersapp.ui.composables.UserItem
import com.example.composeusersapp.ui.theme.ComposeUsersAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: UsersViewModel by viewModels()


        setContent {
            ComposeUsersAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val scope = rememberCoroutineScope()
                    with(viewModel.usersList.collectAsState().value) {

                        val users = data
                        val message = error

                        UserList(users)

                        message?.let {
                            scope.launch {
                                SnackbarHostState().showSnackbar(it)
                            }
                        }
                    }
                }

            }
        }
    }

    @Composable
    fun UserList(
        users: List<UserUI>,
        state: LazyListState = rememberLazyListState(),
    ) {

        LazyColumn {
            items(items = users) {
                UserItem(it)
            }
        }
    }


}

