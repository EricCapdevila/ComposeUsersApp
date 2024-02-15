package com.example.composeusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                    with(viewModel.usersState.collectAsState().value) {

                        Column {
                            SearchBar {
                                viewModel.filterUsers(it)
                            }
                            UserList(filteredData ?: data)
                        }

                        error?.let {
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

    @Composable
    fun SearchBar(
        lambda: (input: String) -> Unit
    ) {
        var text by remember { mutableStateOf("") }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label = { Text(text = "Find...") },
            onValueChange = {
                text = it
                lambda(it)
            })
    }
}

