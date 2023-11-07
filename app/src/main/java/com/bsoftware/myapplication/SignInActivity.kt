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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.bsoftware.myapplication.dataClass.UserDataClass
import com.bsoftware.myapplication.firebaseCloud.FireBase
import com.bsoftware.myapplication.firebaseCloud.FirebaseAuthentication
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : ComponentActivity() {
    private val firebaseAuth : FirebaseAuthentication = FirebaseAuthentication()

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
                    val activity = (LocalContext.current as Activity)
                    val context = LocalContext.current
                    val sharepreference = SharePreference(activity)
                    firebaseAuth.initFirebaseAuth()
                    LoginUserLogic()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUserLogic(){
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
                        // if a email and password is not null or empty
                        val firebaseAuth = FirebaseAuthentication()
                        firebaseAuth.apply {
                            initFirebaseAuth()
                            signInDataUserEmailPass(
                                email = email,
                                password = password,
                                activity = activity,
                                onSuccess = {
                                    // we gonna intent into mainmenu activity
                                    sharepreference.setLoginState(true)
                                    context.startActivity(Intent(context,MainMenuBottomActivity::class.java))
                                    activity.finish()
                                    // in here we must get data from firebase userData
                                },
                                onFail = {
                                    Toast.makeText(activity,"SignIn Failed, please try again",Toast.LENGTH_SHORT).show()
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

            val signUpLink = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.white),
                        fontSize = 15.sp
                    )
                ){
                    append("Don't have an account ?")
                }

                pushStringAnnotation(tag = "sign up", annotation = "")
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.white),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ){
                    append(" Get Started")
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
}


fun getDataUser(email : String,activity : Activity) {
    val firebase = FirebaseDatabase.getInstance()
    val reference = firebase.getReference("userData").child(email) // in here we get a email data in child
    val sharepreference = SharePreference(activity)

    val postListener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val userData = snapshot.getValue(UserDataClass::class.java)
            sharepreference.apply {
                setEmail(email)
                setName(userData!!.name)
                setPhoneNum(userData.numberPhone)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }
    reference.addValueEventListener(postListener)
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    MyApplicationTheme {
        LoginUserLogic()
    }
}