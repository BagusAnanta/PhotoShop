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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class OurProduct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
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
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "IconLogo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            alignment = Alignment.Center
        )

        Column(
            modifier = Modifier.padding(start = 20.dp)
        ){
            Text(
                text = "Our",
                fontSize = 30.sp
            )

            Text(
                text = "Product!",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column (
            modifier = Modifier.padding(start = 20.dp,end = 20.dp,top = 20.dp).fillMaxWidth()
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
                            // intent into photographic
                            context.startActivity(Intent(context,PhotoGraphicProductList::class.java))
                        }
                ) {

                }

                Card(
                    modifier = Modifier
                        .size(160.dp,200.dp)
                        .fillMaxWidth(0.5f)
                        .clickable {
                            context.startActivity(Intent(context,GrapicDesainProduct::class.java))
                        }
                ) {

                }
            }

            Card(
                modifier = Modifier
                    .size(500.dp,200.dp)
                    .padding(top = 20.dp)
                    .clickable {
                        context.startActivity(Intent(context,VideoGraphProduct::class.java))
                    }
            ) {

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