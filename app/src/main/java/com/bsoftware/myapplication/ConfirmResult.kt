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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class ConfirmResult : ComponentActivity() {
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
                    ConfirmProject()
                }
            }
        }
    }
}

@Composable
fun ConfirmProject() {
    val context = LocalContext.current
    /*val activity = (LocalContext.current as Activity)
    val sharePref = SharePreference(activity)*/

    // val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.check_mark_lottie_animation))

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
                .fillMaxWidth()
        ) {
            Row{
                Column{
                    Text(
                        text = "Thank You",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Thank You For Confirmation!",
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

            Card(modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 30.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.brown_card)
                )
            ){

                /*LottieAnimation(
                    composition = composition
                )*/

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logokecil),
                        contentDescription = "Complete Mark",
                        modifier = Modifier.size(width = 100.dp, height = 100.dp)
                    )
                }

                Text(
                    "Thank's for Order",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp, bottom = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    color = Color.White
                )

                Text(
                    "Thank's for choose Can.DO Creative House for you partner,our customer service will contact you soon,to immediately make your wishes come true !",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp,end = 20.dp, top = 20.dp, bottom = 20.dp),
                    color = Color.White
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "For information about Can.Do, you can call :",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )

                Text(
                    text = stringResource(id = R.string.customer_service,"+62 342 7864 23456"),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                    color = Color.White
                )

                Text(
                    text = stringResource(id = R.string.email_service,"admincando@email.com"),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 5.dp),
                    color = Color.White
                )

                OutlinedButton(
                    onClick = {
                        // intent into Main menu activity
                        /* sharePref.deleteProductChooseAll()
                         context.startActivity(Intent(context,MainMenuBottomActivity::class.java))
                         activity.finish()*/
                    },
                    modifier = Modifier
                        .padding(top = 20.dp, start = 50.dp, end = 50.dp, bottom = 20.dp)
                        .fillMaxWidth(),

                    shape = CutCornerShape(10)
                ) {
                    Text(
                        "Back to Home",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfirmResultPreview() {
    MyApplicationTheme {
        ConfirmProject()
    }
}