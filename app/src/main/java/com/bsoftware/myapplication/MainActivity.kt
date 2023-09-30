package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bsoftware.myapplication.dataViewModelClass.DataViewModelClass
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val dataviewmodel : DataViewModelClass by viewModels()

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
                    if(sharepreference.getLoginState()){
                        // if a true we continue in main menu
                        context.startActivity(Intent(context,MainMenuActivity::class.java))
                        activity.finish()
                    } else {
                        LoginUserLogic(dataViewModel = dataviewmodel, this)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUserLogic(dataViewModel : DataViewModelClass, lifeCycleOwner: LifecycleOwner){
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }

    var usernameData : String?
    var passwordData : String?

    val context = LocalContext.current
    val activity = (LocalContext.current as Activity)

    val sharepreference = SharePreference(activity)

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = {
                dataViewModel.Datauser.observe(lifeCycleOwner, Observer { datauser ->
                    for(datauserlogin in datauser){
                        usernameData = datauserlogin.username
                        passwordData = datauserlogin.password

                        if(usernameData == username && passwordData == password){
                            sharepreference.setLoginState(true)
                            context.startActivity(Intent(context,MainMenuActivity::class.java))
                            activity.finish()
                        } else {
                            Toast.makeText(context,"Hello fail :(",Toast.LENGTH_SHORT).show()
                        }

                    }
                })
                dataViewModel.getDataLogin()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "Login")
        }

        val signUpLink = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.black),
                    fontSize = 15.sp
                )
            ){
                append("No Have Account ?")
            }

            pushStringAnnotation(tag = "sign up", annotation = "")
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.purple_200),
                    fontSize = 15.sp
                )
            ){
                append(" Sign up")
            }
        }

        ClickableText(
            text = signUpLink,
            onClick ={offset ->
                signUpLink.getStringAnnotations(tag = "sign up", start = offset, end = offset)
                // intent into sign up
                context.startActivity(Intent(context,SignUpActivity::class.java))
                activity.finish()
            }
        )
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "Login")
        }

        val signUpLink = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.black),
                    fontSize = 15.sp
                )
            ){
                append("No Have Account ?")
            }

            pushStringAnnotation(tag = "sign up", annotation = "")
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.purple_200),
                    fontSize = 15.sp
                )
            ){
                append(" Sign up")
            }
        }

        ClickableText(
            text = signUpLink,
            onClick ={offset ->
                signUpLink.getStringAnnotations(tag = "sign up", start = offset, end = offset)
                // intent into sign up
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        LoginUser()
    }
}