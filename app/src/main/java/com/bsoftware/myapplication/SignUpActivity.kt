package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.bsoftware.myapplication.firebaseCloud.FireBase
import com.bsoftware.myapplication.firebaseCloud.FirebaseAuthentication
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class SignUpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var numberTelp by remember { mutableStateOf("") }

    val context = LocalContext.current
    /*val activity = (LocalContext.current as Activity)

    val sharepreference = SharePreference(activity)*/

    var isError by rememberSaveable { mutableStateOf(false) }
    val charRecommend = 6

    fun validate(pass : String){
        isError = pass.length < charRecommend
    }

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
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoutama),
                contentDescription = "LogoContain",
                modifier = Modifier.size(200.dp,100.dp)
            )

            Card(modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 30.dp)
                .height(600.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.brown_card)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp,end = 20.dp)
                ) {
                    Text(
                        text = "Let's We Know You",
                        style = TextStyle(
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "Please Fill You Data this Field !",
                        style = TextStyle(
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                }

                // layz column for textfield
                LazyColumn{
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                label = {
                                    Text(
                                        text = "Your real name",
                                        color = Color.White
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Person,
                                        contentDescription = "NameIcon",
                                        tint = Color.White
                                    )
                                }
                            )
                            OutlinedTextField(
                                value = numberTelp,
                                onValueChange = { numberTelp = it },
                                label = {
                                    Text(
                                        text = "Your number phone",
                                        color = Color.White
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Phone,
                                        contentDescription = "PhoneIcon",
                                        tint = Color.White
                                    )
                                }
                            )
                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                label = {
                                    Text(
                                        text = "Email",
                                        color = Color.White
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Email,
                                        contentDescription = "EmailIcon",
                                        tint = Color.White
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))

                            // In here, we must a give some warning for user if a password at least 6 character
                            // because if a password least 6 character a firebase return a weekpassword exception
                            // for a solution you can some give a condition or more
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = {
                                    Text(
                                        text = "Password",
                                        color = Color.White
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                                isError = isError,
                                supportingText = {
                                    if(isError){
                                        Text(
                                            text = "You Password Less 6",
                                            color = MaterialTheme.colorScheme.error,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                },
                                trailingIcon = {
                                    if(isError){
                                        Icon(Icons.Outlined.Warning,"Warning", tint = Color.Black)
                                    }
                                },
                                keyboardActions = KeyboardActions { validate(password) },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Lock,
                                        contentDescription = "PassIcon",
                                        tint = Color.White
                                    )
                                }

                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            OutlinedButton(
                                onClick = {
                                    if(name.isNotEmpty() || numberTelp.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty()){
                                        /*val firebase = FireBase()
                                        val firebaseauthdata = FirebaseAuthentication()
                                        firebaseauthdata.apply {
                                            initFirebaseAuth()
                                            createDataUserEmailPass(
                                                email = email,
                                                password = password,
                                                activity = activity,
                                                onSuccess = {
                                                    // in here we update a firebase display name
                                                    firebaseauthdata.addUpdateUserDisplayName(name)
                                                    sharepreference.apply {
                                                        setLoginState(true)
                                                        setName(name)
                                                        setEmail(email)
                                                        setPhoneNum(numberTelp)
                                                    }

                                                    // on here we gonna a write too a userData in firebase database
                                                    firebase.apply {
                                                        initDatabase()
                                                        writeDataUser(email = firebaseauthdata.getEmail(),name = name, numberPhone = numberTelp)
                                                    }

                                                    context.startActivity(Intent(context,MainMenuBottomActivity::class.java))
                                                    activity.finish()
                                                },
                                                onFailed = {
                                                    Toast.makeText(context,"Failed into SignUp Data, please try again",Toast.LENGTH_SHORT).show()
                                                }
                                            )
                                        }*/
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
                                    text = "Register",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            val signUpLink = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.white),
                                        fontSize = 15.sp
                                    )
                                ){
                                    append("Already have an account ?")
                                }

                                pushStringAnnotation(tag = "sign in", annotation = "")
                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.white),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                ){
                                    append(" Sign in here")
                                }
                            }

                            ClickableText(
                                text = signUpLink,
                                onClick ={ offset ->
                                    signUpLink.getStringAnnotations(tag = "sign in", start = offset, end = offset)
                                    // intent into sign in
                                    /* context.startActivity(Intent(context,MainActivity::class.java))
                                     activity.finish()*/
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpPreview() {
    MyApplicationTheme {
        SignUp()
    }
}