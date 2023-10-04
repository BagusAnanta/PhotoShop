package com.bsoftware.myapplication

import android.os.Bundle
import android.text.Layout.Alignment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
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
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.bsoftware.myapplication.dataClass.PhotoProductDataClass
import com.bsoftware.myapplication.dataViewModelClass.PhotoShopDataViewModelClass
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class MainMenuActivity : ComponentActivity() {
    private val dataviewmodel : PhotoShopDataViewModelClass by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
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
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Logo Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            alignment = androidx.compose.ui.Alignment.Center
        )

        Column(
            modifier = Modifier.padding(start = 20.dp)
        ){
            Text(
                text = "Welcome back,",
                fontSize = 30.sp
            )

            Text(
                text = "Costumer",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Card(
            modifier = Modifier
                .size(width = 500.dp, height = 210.dp)
                .padding(20.dp)
        ) {
            Row{
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background) ,
                    contentDescription = "ImageCard",
                    modifier = Modifier.padding(top = 30.dp, end = 10.dp, start = 10.dp)
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


                }
            }
        }

        Row{
            Text(
                text = "Our",
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 5.dp, start = 20.dp)
            )
            Text(
                text = "Project",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            val seeAll = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.black),
                        fontSize = 15.sp
                    )
                ){
                    Text(
                        text = "See All",
                        textAlign = TextAlign.End,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth().padding(end = 20.dp)
                    )
                }
            }

            ClickableText(
                text = seeAll,
                onClick = {/*TODO*/},
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
                    fontSize = 15.sp
                )
                Text(
                    text = "deserved great design",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                            "\nsed do eiusmod tempor incididunt ut " +
                            "\nlabore et dolore magna aliqua.",
                    fontSize = 9.sp,
                    lineHeight = 10.sp,
                    modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                    maxLines = 3
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
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    }
                }

                ClickableText(
                    text = startBrand,
                    onClick = {/*TODO*/},
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "ImageProject",
            )
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