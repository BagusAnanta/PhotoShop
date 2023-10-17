package com.bsoftware.myapplication

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class VideoGraphProduct : ComponentActivity() {
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
                    VideoGraphicProduct()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoGraphicProduct() {
    var search by remember { mutableStateOf("") }
    val videoGraphTitle = "Video Graphic"

    val context = LocalContext.current
    val intent = Intent(context,CheckOutProduct::class.java)
    intent.putExtra("videoGraphic",videoGraphTitle)

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundutama),
            contentDescription = "App Backgroud",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .fillMaxWidth()) {
            Row{
                Column{
                    Text(
                        text = "VideoGraphic",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Project List",
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

            OutlinedTextField(
                value = search,
                onValueChange = {search = it},
                label = {
                    Text(
                        text = "Find in here ",
                        color = Color.White
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

            Column(
                modifier = Modifier.padding(top = 20.dp).fillMaxHeight()
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(id = R.drawable.foodphotography),
                        contentDescription = "photoproduct",
                        modifier = Modifier
                            .size(160.dp, 240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                // click into checkout
                            }
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.FillBounds,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.foodphotography),
                        contentDescription = "photoproduct",
                        modifier = Modifier
                            .size(160.dp, 240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                // click into checkout
                            }
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.FillBounds,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.foodphotography),
                        contentDescription = "photoproduct",
                        modifier = Modifier
                            .size(160.dp, 240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                // click into checkout
                            }
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.FillBounds,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.foodphotography),
                        contentDescription = "photoproduct",
                        modifier = Modifier
                            .size(160.dp, 240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                // click into checkout
                            }
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.FillBounds,
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.foodphotography),
                    contentDescription = "photoproduct",
                    modifier = Modifier
                        .size(160.dp, 240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            // click into checkout
                        }
                        .fillMaxWidth(0.5f),
                    contentScale = ContentScale.FillBounds,
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoGraphicPreview() {
    MyApplicationTheme {
        VideoGraphicProduct()
    }
}