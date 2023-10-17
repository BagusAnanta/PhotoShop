package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.dataViewModelClass.LoginDataViewModelClass
import com.bsoftware.myapplication.firebaseCloud.FirebaseAuthentication
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : ComponentActivity() {

    private val dataviewmodel : LoginDataViewModelClass by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUp(dataviewmodel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(dataviewmodel : LoginDataViewModelClass = LoginDataViewModelClass()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var numberTelp by remember { mutableStateOf("") }

    val context = LocalContext.current
    val activity = (LocalContext.current as Activity)

    val sharepreference = SharePreference(activity)

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
            value = name,
            onValueChange = { name = it },
            label = {
                Text(text = "Your real name")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        )
        OutlinedTextField(
            value = numberTelp,
            onValueChange = { numberTelp = it },
            label = {
                Text(text = "Your number phone (use WhatsApp number)")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(text = "Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = "Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        OutlinedButton(
            onClick = {
                // insert data in here, after that we intent to main menu
                // in here we gonna change to firebase authentication

                // dataviewmodel.insertDataLogin(username,password)
                val firebaseauthdata = FirebaseAuthentication()
                firebaseauthdata.initFirebaseAuth()
                firebaseauthdata.createDataUserEmailPass(
                    email = email,
                    password = password,
                    activity = activity,
                    onSuccess = {
                        // in here we gonna intent or give toast
                        sharepreference.setName(name)
                        sharepreference.setPhoneNum(numberTelp)
                        sharepreference.setLoginState(true)

                        // intent in here into mainmenu
                        context.startActivity(Intent(context,MainMenuActivity::class.java))
                        activity.finish()
                    },
                    onFailed = {
                        Toast.makeText(context,"Failed into SignUp Data, please try again",Toast.LENGTH_SHORT).show()
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp,end = 25.dp, top = 25.dp, bottom = 5.dp)
                .size(45.dp,45.dp)
        ) {
            Text(text = "Sign Up")
        }

        val signUpLink = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.black),
                    fontSize = 15.sp
                )
            ){
                append("Already have an account ?")
            }

            pushStringAnnotation(tag = "sign up", annotation = "")
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.purple_200),
                    fontSize = 15.sp
                )
            ){
                append(" Sign in here")
            }
        }

        ClickableText(
            text = signUpLink,
            onClick ={ offset ->
                signUpLink.getStringAnnotations(tag = "sign up", start = offset, end = offset)
                // intent into sign up
                context.startActivity(Intent(context,SignUpActivity::class.java))
                activity.finish()
            }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    MyApplicationTheme {
        SignUp()
    }
}