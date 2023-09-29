package com.bsoftware.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginUser()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUser(){
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        TextField(
            value = username,
            onValueChange = {username = it},
            label = {
                Text(text = "Username")
            },
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        LoginUser()
    }
}