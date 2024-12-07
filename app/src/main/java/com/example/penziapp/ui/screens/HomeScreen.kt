package com.example.penziapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.penziapp.data.SharedPref
import com.example.penziapp.ui.screens.components.AllMessagesList
import com.example.penziapp.ui.screens.components.AllUsersList
import com.example.penziapp.ui.theme.PenziAppTheme

/**
 * Contains the home screen and the Result screens (both users and messages)
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    nav: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = HomeViewModel()
) {
    val context = LocalContext.current
    val sharedPref = remember { SharedPref(context) }
    val token = sharedPref.token()

    LaunchedEffect(token) {
        if (token.isNullOrEmpty()) {
            Log.d("Effect_in", "Passed: ${sharedPref.token()}")
            nav.navigate("login")
        } else {
            viewModel.getAllDashMessages()
        }
    }

    val selected = remember { mutableIntStateOf(0) }


    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Penzi dashboard",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                        )
                        Text(
                            text = "${sharedPref.username()}Admin",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.surfaceBright
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        sharedPref.clearBoth()
                        nav.navigate("login")
                    }, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = "Logout")
                    }
                },
                modifier = modifier,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
            Column {
                TabRow(selectedTabIndex = selected.intValue, modifier = modifier) {
                    Tab(
                        selected = selected.intValue == 0,
                        onClick = { selected.intValue = 0 },
                        text = { Text("Users") }
                    )
                    Tab(
                        selected = selected.intValue == 1,
                        onClick = { selected.intValue = 1 },
                        text = { Text("Messages") }
                    )
                    Tab(
                        selected = selected.intValue == 2,
                        onClick = { selected.intValue = 2 },
                        text = { Text("Overview") }
                    )
                }
                when (selected.intValue) {
                    0 -> AllUsersList(listData = ArrayList())
                    1 -> AllMessagesList(listData = ArrayList())
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    PenziAppTheme {
        HomeScreen(NavController(LocalContext.current))
    }
}