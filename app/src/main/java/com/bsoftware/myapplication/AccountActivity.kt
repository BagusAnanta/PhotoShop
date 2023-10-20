package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.firebaseCloud.FirebaseAuthentication
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class AccountActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AccountUserData()
                }
            }
        }
    }
}

@Composable
fun AccountUserData(){

    val firebaseAuth = FirebaseAuthentication()
    val activity = (LocalContext.current as Activity)
    val sharePref = SharePreference(activity)
    firebaseAuth.initFirebaseAuth()

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundutama),
            contentDescription = "App Backgroud",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxSize()
        ) {
            Row{
                Column{
                    Text(
                        text = "Account",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Text(
                        text = "User",
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.logoutama),
                    contentDescription = "LogoImage",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .size(80.dp, 80.dp)
                )
            }

            Card(
                modifier = Modifier
                    .size(500.dp, 150.dp)
                    .padding(top = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                ) {
                   Row(modifier = Modifier.fillMaxSize()) {
                       Column(modifier = Modifier.padding(5.dp)) {
                           Text(
                               text = "Data User",
                               fontSize = 20.sp,
                               fontWeight = FontWeight.Bold
                           )

                           Text(
                               text = stringResource(id = R.string.nama_account,sharePref.getName()!!),
                               modifier = Modifier.padding(top = 5.dp),
                               fontWeight = FontWeight.Bold
                           )

                           Text(
                               text = stringResource(id = R.string.email_account,firebaseAuth.getEmail()),
                               modifier = Modifier.padding(top = 5.dp),
                               fontWeight = FontWeight.Bold
                           )

                           Text(
                               text = stringResource(id = R.string.phonenum_account,sharePref.getPhoneNum()!!),
                               modifier = Modifier.padding(top = 5.dp),
                               fontWeight = FontWeight.Bold
                           )
                       }
                   }
                }
            }

           Column(

           ) {
               // button in here
               OutlinedButton(
                   onClick = {
                       // click in here for sign out and delete data in share preference
                       firebaseAuth.signOutEmail()
                       sharePref.deleteAll()
                       // and intent into OptionLogin
                       context.startActivity(Intent(context,OptionLogin::class.java))
                       activity.finish()

                   },
                   modifier = Modifier
                       .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                       .fillMaxWidth(),
                   shape = CutCornerShape(10),
                   colors = ButtonDefaults.buttonColors(Color.Red)
               ) {
                   Text(
                       "Sign Out",
                       fontWeight = FontWeight.Bold,
                       style = TextStyle(
                           color = Color.White
                       )
                   )
               }

               OutlinedButton(
                   onClick = {
                       // click in here for get started
                        context.startActivity(Intent(context,AdminLoginActivity::class.java))
                   },
                   modifier = Modifier
                       .padding(top = 5.dp, start = 30.dp, end = 30.dp)
                       .fillMaxWidth(),
                   shape = CutCornerShape(10)
               ) {
                   Text(
                       "Login By Admin",
                       fontWeight = FontWeight.Bold,
                       style = TextStyle(
                           color = Color.White
                       )
                   )
               }
           }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    MyApplicationTheme {
        AccountUserData()
    }
}