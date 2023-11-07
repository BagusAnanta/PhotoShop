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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val activity = (LocalContext.current as Activity)
    val sharePref = SharePreference(activity)

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
                        text = "Terima Kasih",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Terima kasih atas Konfirmasinya!",
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
                .size(500.dp, 210.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 30.dp)){
                Text(
                    "Pesan Kami",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 20.dp, bottom = 20.dp)
                        .fillMaxWidth()
                )

                Text(
                    "Terima kasih telah memilih Can.DO Creative House sebagai mitra anda, costumer service  kami akan segera menghubungi anda,untuk segera merealisasikan keinginan anda",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp,end = 20.dp)
                )
            }

            OutlinedButton(
                onClick = {
                    // intent into Main menu activity
                    sharePref.deleteProductChooseAll()
                    context.startActivity(Intent(context,MainMenuBottomActivity::class.java))
                    activity.finish()
                },
                modifier = Modifier
                    .padding(top = 20.dp, start = 50.dp, end = 50.dp)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfirmResultPreview() {
    MyApplicationTheme {
        ConfirmProject()
    }
}