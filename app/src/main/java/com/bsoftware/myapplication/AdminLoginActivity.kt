package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.firebaseCloud.FirebaseAuthentication
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class AdminLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginAdminLogic()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAdminLogic(){
    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }

    val context = LocalContext.current
    val activity = (LocalContext.current as Activity)

    val sharepreference = SharePreference(activity)

    Box(modifier = Modifier.fillMaxSize()){
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
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = {
                    Text(
                        text = "Email",
                        color = Color.White
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {
                    Text(
                        text = "Password",
                        color = Color.White
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))

            val forgetPass = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.white),
                        fontSize = 15.sp
                    )
                ){
                    Text(
                        text = "Forget Password ?",
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        color = Color.White
                    )
                }
            }

            ClickableText(
                text = forgetPass,
                onClick = {/*TODO*/},
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    textAlign = TextAlign.End,
                    fontSize = 15.sp
                )
            )

            OutlinedButton(
                onClick = {
                    if(email.isNotEmpty() || password.isNotEmpty()){
                        val firebaseAuth = FirebaseAuthentication()
                        firebaseAuth.apply {
                            initFirebaseAuth()
                            signInDataUserEmailPass(
                                email = email,
                                password = password,
                                activity = activity,
                                onSuccess = {
                                    // we gonna intent into Admin History and delete a User Login State in here
                                    // in here we gonna check a email
                                    if(firebaseAuth.getEmail() == "admincando@gmail.com"){
                                        sharepreference.apply {
                                            deleteLoginState()
                                            setLoginAdminState(true)
                                        }
                                        context.startActivity(Intent(context,AdminReport::class.java))
                                        activity.finish()
                                    } else {
                                        // if a email is not admin email
                                        Toast.makeText(activity,"You Not Admin",Toast.LENGTH_SHORT).show()
                                    }
                                },
                                onFail = {
                                    Toast.makeText(activity,"SignIn Failed, please try again", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    } else {
                        Toast.makeText(context,"A Field is have empty, please check again",Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp, top = 25.dp, bottom = 5.dp)
                    .size(45.dp, 45.dp),
                shape = CutCornerShape(10)
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview6() {
    MyApplicationTheme {
        LoginAdminLogic()
    }
}