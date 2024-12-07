package com.example.penziapp.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.penziapp.R
import com.example.penziapp.data.SharedPref
import com.example.penziapp.network.LoginValuesObj
import com.example.penziapp.ui.theme.PenziAppTheme

/**
 * Contains the LoginScreen composable
 */

@Composable
fun LoginScreen(
    nav: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = LoginViewModel()
) {
    val (logins, setLogins) = remember { mutableStateOf<LoginValuesObj>(LoginValuesObj("", "")) }
//    val errCode = remember { viewModel.errorCode }
    val context = LocalContext.current
    val sharedPref = remember { SharedPref(context) }

    //    val message = remember { viewModel.message }
    fun handleLogin() {
        viewModel.handleLogin(logins, nav, sharedPref)
    }

    LaunchedEffect(viewModel.token, viewModel.errorCode) {
        Log.d("Dang", sharedPref.token().toString())
        Log.d("Auth effect", "${viewModel.errorCode.intValue} - ${viewModel.token.value}")
        if (viewModel.errorCode.intValue == 200 && viewModel.token.value.isNotEmpty()) {
            Log.d("Auth effect", "Authenticated")
            sharedPref.saveToken(viewModel.token.value)
            sharedPref.saveUsername(viewModel.username.value)
            nav.navigate("home")
        }
    }

    val focusManager = LocalFocusManager.current
    Surface(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            },
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Penzi Admin",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(20.dp))
            Box(
                modifier
                    .width(200.dp)
                    .height(250.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.couple),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Couples holding hands",
                    modifier = modifier
                        .width(150.dp)
                        .height(200.dp)
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(20.dp)),

                    )
                Image(
                    painter = painterResource(id = R.drawable.couplehands),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Couples holding hands",
                    modifier = modifier
                        .width(100.dp)
                        .height(200.dp)
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(20.dp)),
                )
            }
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = modifier
                    .width(300.dp)
                    .background(color = Color.White)
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Welcome back",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (viewModel.message.value.isNotEmpty()) {
                    Spacer(modifier.height(15.dp))
                    Text(
                        text = viewModel.message.value,
                        color = MaterialTheme.colorScheme.error,
                        modifier = modifier.padding(5.dp)
                    )
                }
                Spacer(modifier.height(15.dp))
                OutlinedTextField(
                    value = logins.username,
                    onValueChange = { setLogins(logins.copy(username = it)) },
                    shape = RoundedCornerShape(50.dp),
                    placeholder = { Text("Username") }
                )
                Spacer(modifier.height(15.dp))
                OutlinedTextField(
                    value = logins.password,
                    onValueChange = { setLogins(logins.copy(password = it)) },
                    shape = RoundedCornerShape(50.dp),
                    placeholder = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier.height(15.dp))
                Button(modifier = modifier
                    .fillMaxWidth()
                    .height(50.dp), onClick = {
                    handleLogin()
                }) {
                    Text("Login")
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun PreviewLoginScreen() {

    PenziAppTheme {

    }
}
