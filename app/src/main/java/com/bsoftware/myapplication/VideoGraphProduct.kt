package com.bsoftware.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
                    modifier = Modifier.fillMaxSize(),
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

    Column(modifier = Modifier.padding(start = 20.dp,end = 20.dp,top = 20.dp).fillMaxWidth()) {
        Row{
            Column{
                Text(
                    text = "VideoGraphic",
                    fontSize = 25.sp
                )
                Text(
                    text = "Project List",
                    fontSize = 15.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "LogoImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            )
        }

        OutlinedTextField(
            value = search,
            onValueChange = {search = it},
            label = {
                Text(text = "Find in here ")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Column(
            modifier = Modifier.padding(top = 20.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Card(
                    modifier = Modifier
                        .size(160.dp,200.dp)
                        .fillMaxWidth(0.5f)
                        .clickable {
                            context.startActivity(intent)
                        }
                ) {

                }

                Card(
                    modifier = Modifier
                        .size(160.dp,200.dp)
                        .fillMaxWidth(0.5f)
                        .clickable {
                            context.startActivity(intent)
                        }
                ) {

                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
            ){
                Card(
                    modifier = Modifier
                        .size(160.dp,200.dp)
                        .fillMaxWidth(0.5f)
                        .clickable {
                            context.startActivity(intent)
                        }
                ) {

                }

                Card(
                    modifier = Modifier
                        .size(160.dp,200.dp)
                        .fillMaxWidth(0.5f)
                        .clickable {
                            context.startActivity(intent)
                        }
                ) {

                }
            }

            Card(
                modifier = Modifier
                    .size(160.dp,200.dp)
                    .fillMaxWidth(0.5f)
                    .padding(top = 20.dp)
                    .clickable {
                        context.startActivity(intent)
                    }
            ) {

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