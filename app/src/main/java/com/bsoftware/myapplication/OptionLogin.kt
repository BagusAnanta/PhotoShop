package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsoftware.myapplication.sharePreference.SharePreference
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
                    val activity = (LocalContext.current as Activity)
                    val context = LocalContext.current
                    val sharepreference = SharePreference(activity)

                    if(sharepreference.getLoginState()){ // if user is true
                        context.startActivity(Intent(context,MainMenuBottomActivity::class.java))
                        activity.finish()
                    } else if(sharepreference.getLoginAdminState()) { // if admin is true
                        // intent into admin report
                        context.startActivity(Intent(context,AdminReport::class.java))
                    } else {
                        OptionLoginUser()
                    }
                }
            }
        }
    }
}

@Composable
fun OptionLoginUser(){
    val context = LocalContext.current
    val activity = OptionLogin()
    // val activity = (LocalContext.current as Activity)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroundutama),
            contentDescription = "App Backgroud",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoutama),
                contentDescription = "LogoContain",
                modifier = Modifier.size(200.dp,100.dp)
            )

            OutlinedButton(
                onClick = {
                    // click in here for get started
                     context.startActivity(Intent(context,SignUpActivity::class.java))
                     activity.finish()
                },
                modifier = Modifier
                    .padding(top = 20.dp, start = 50.dp, end = 50.dp)
                    .fillMaxWidth(),
                shape = CutCornerShape(10)
            ) {
                Text(
                    "Get Started",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }

            OutlinedButton(
                onClick = {
                    // click in here for sign in
                    context.startActivity(Intent(context,MainActivity::class.java))
                    activity.finish()
                },
                modifier = Modifier
                    .padding(top = 10.dp, start = 50.dp, end = 50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = CutCornerShape(10)
            ) {
                Text(
                    "Sign In",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            }
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