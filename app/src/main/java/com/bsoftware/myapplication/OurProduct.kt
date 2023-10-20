package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class OurProduct : ComponentActivity() {
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
                   OurProductShow()
                }
            }
        }
    }
}

@Composable
fun OurProductShow() {
    val context = LocalContext.current
    val activity = (LocalContext.current as Activity)
    val sharePref = SharePreference(activity)

    // in here we gonna delete a data choose in here
    sharePref.deleteProductChooseAll()


    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundutama),
            contentDescription = "App Backgroud",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoutama),
                contentDescription = "IconLogo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(80.dp, 80.dp),
                alignment = Alignment.Center
            )

            Column(
                modifier = Modifier.padding(start = 20.dp)
            ){
                Text(
                    text = "Our",
                    fontSize = 30.sp,
                    color = Color.White
                )

                Text(
                    text = "Product!",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Column (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .fillMaxWidth()
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(id = R.drawable.productfoto),
                        contentDescription = "photoproduct",
                        modifier = Modifier
                            .size(160.dp, 240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        context,
                                        PhotoGraphicProductList::class.java
                                    )
                                )
                            }
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.Crop,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.productdesain),
                        contentDescription = "photoproduct",
                        modifier = Modifier
                            .size(160.dp, 200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        context,
                                        GrapicDesainProduct::class.java
                                    )
                                )
                            }
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.Crop,
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.productvideo),
                    contentDescription = "photoproduct",
                    modifier = Modifier
                        .size(500.dp, 200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    VideoGraphProduct::class.java
                                )
                            )
                        }
                        .fillMaxWidth(0.5f)
                        .padding(top = 20.dp)
                        .aspectRatio(16f / 9f)
                        .padding(start = 10.dp),
                )
            }

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        OurProductShow()
    }
}