package com.bsoftware.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class MainMenuActivity : ComponentActivity() {

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
                    ShowMainMenu()
                }
            }
        }
    }
}

@Composable
fun ShowMainMenu(){
    val context = LocalContext.current

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
                contentDescription = "Logo Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(80.dp, 80.dp),
                alignment = androidx.compose.ui.Alignment.Center
            )

            Column(
                modifier = Modifier.padding(start = 20.dp)
            ){
                Text(
                    text = "Welcome back,",
                    fontSize = 30.sp,
                    color = Color.White
                )

                Text(
                    text = "Costumer",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Card(
                modifier = Modifier
                    .size(width = 500.dp, height = 200.dp)
                    .padding(20.dp),
            ) {
                Row{
                    Image(
                        painter = painterResource(id = R.drawable.logoutama) ,
                        contentDescription = "ImageCard",
                        modifier = Modifier
                            .padding(top = 30.dp, end = 10.dp, start = 10.dp)
                            .size(80.dp, 80.dp)
                    )
                    Column(
                        modifier = Modifier.padding(top = 10.dp)
                    ){
                        Text(
                            text = "Grow With",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Great Partner!",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                            fontSize = 9.sp,
                            lineHeight = 10.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Card(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp, bottom = 5.dp, end = 10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Lorem Ipsum dolor sit amet",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                }
            }

            Row{
                Text(
                    text = "Our",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 5.dp, start = 20.dp),
                    color = Color.White
                )
                Text(
                    text = "Project",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                val seeAll = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.white),
                            fontSize = 15.sp
                        )
                    ){
                        Text(
                            text = "See All ->",
                            textAlign = TextAlign.End,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 20.dp),
                            color = Color.White,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                ClickableText(
                    text = seeAll,
                    onClick = {
                        //intent into our product
                        context.startActivity(Intent(context,OurProduct::class.java))

                    },
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        textAlign = TextAlign.End,
                    )
                )

            }

            Row(modifier = Modifier
                .padding(start = 20.dp, top = 10.dp,end = 20.dp)
            ){
                Column {
                    Text(
                        text = "Your Business",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                    Text(
                        text = "deserved great design",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "\nsed do eiusmod tempor incididunt ut " +
                                "\nlabore et dolore magna aliqua.",
                        fontSize = 9.sp,
                        lineHeight = 10.sp,
                        modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                        maxLines = 3,
                        color = Color.White
                    )

                    val startBrand = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(id = R.color.black),
                                fontSize = 15.sp
                            )
                        ){
                            Text(
                                text = "Start Your Brand!",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 20.dp),
                                color = Color.White,
                                style = TextStyle(
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                        }
                    }

                    ClickableText(
                        text = startBrand,
                        onClick = {
                            // intent into Graphic Design
                            context.startActivity(Intent(context,GrapicDesainProduct::class.java))
                        },
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "ImageProject",
                )
            }

            Column {
                Text(
                    text = "Favorite",
                    modifier = Modifier.padding(start = 20.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Text(
                    text = "Most 3 Favorit Choice",
                    modifier = Modifier.padding(start = 20.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                LazyRow(
                    contentPadding = PaddingValues(5.dp),
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.preweddingbox),
                            contentDescription = "PrewedPhoto",
                        )
                        Image(
                            painter = painterResource(id = R.drawable.dokumentasibox),
                            contentDescription = "PrewedPhoto",
                        )
                        Image(
                            painter = painterResource(id = R.drawable.dokumentasibox),
                            contentDescription = "PrewedPhoto",
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuPreview() {
    MyApplicationTheme {
        ShowMainMenu()
    }
}