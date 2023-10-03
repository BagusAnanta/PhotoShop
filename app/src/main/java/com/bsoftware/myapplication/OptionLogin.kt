package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class OptionLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OptionLoginUser()
                }
            }
        }
    }
}

@Composable
fun OptionLoginUser(){
    val context = LocalContext.current
    val activity = (LocalContext.current as Activity)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().wrapContentHeight(Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "LogoContain",
        )

        OutlinedButton(
            onClick = {
                // click in here for get started
                context.startActivity(Intent(context,SignUpActivity::class.java))
                activity.finish()

            },
            modifier = Modifier.padding(top = 20.dp, start = 10.dp,end = 10.dp).fillMaxWidth()
        ) {
            Text("Get Started")
        }

        OutlinedButton(
            onClick = {
                // click in here for sign in
                context.startActivity(Intent(context,MainActivity::class.java))
                activity.finish()
            },
            modifier = Modifier.padding(top = 10.dp, start = 10.dp,end = 10.dp).fillMaxWidth()
        ) {
            Text("Sign In")
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OptionLogoPreview() {
    MyApplicationTheme {
        OptionLoginUser()
    }
}