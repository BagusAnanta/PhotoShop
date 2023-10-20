package com.bsoftware.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.dateFormat.DateFormat
import com.bsoftware.myapplication.firebaseCloud.FireBase
import com.bsoftware.myapplication.generateID.GenerateIDProduct
import com.bsoftware.myapplication.sharePreference.SharePreference
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class CheckOutProduct : ComponentActivity() {
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
                    CheckOutConfirmProduct()
                }
            }
        }
    }
}

@Composable
fun CheckOutConfirmProduct() {
    val context = LocalContext.current
    val activity = (LocalContext.current as Activity)
    val sharepref = SharePreference(activity)
    val generateID = GenerateIDProduct()
    val dateNow = DateFormat()

    val kodePesanan : Any = generateID.generateIDNumber(10)
    val tanggalPesanan : Any = dateNow.getDate()
    val namaPemesan : Any? = sharepref.getName()
    val nomorHandphone : Any? = sharepref.getPhoneNum()

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundutama),
            contentDescription = "App Backgroud",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .fillMaxWidth()
            .fillMaxSize()) {
            Row{
                Column{
                    Text(
                        text = "Project Choose",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Project Check Out",
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


            Text(
                text = "Project Review",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 30.dp),
                color = Color.White
            )

            Card(
                modifier = Modifier
                    .size(500.dp, 200.dp)
                    .padding(top = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    Column() {
                        Text(
                            sharepref.getProductName(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Product",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Image(
                        painter = painterResource(sharepref.getImageProduct()),
                        contentDescription = "photoproduct",
                        modifier = Modifier
                            .size(100.dp, 240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }

            Text(
                text = "Data Pemesan",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 30.dp),
                color = Color.White
            )

            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.kode_pesanan,kodePesanan),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.tanggal_pesanan,tanggalPesanan),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Color.White
                )
                 Text(
                     text = stringResource(id = R.string.nama_pemesan,namaPemesan!!),
                     fontWeight = FontWeight.Bold,
                     modifier = Modifier.padding(top = 10.dp),
                     color = Color.White
                 )
                 Text(
                     text = stringResource(id = R.string.nomor_handphone,nomorHandphone!!),
                     fontWeight = FontWeight.Bold,
                     modifier = Modifier.padding(top = 10.dp),
                     color = Color.White
                 )
            }
            OutlinedButton(
                onClick = {
                    // we save a data in here and exit into next page
                      val firebase = FireBase()
                      firebase.initDatabase()
                      firebase.writeDataCheckOut(
                          kodePesanan.toString(),
                          namaPemesan.toString(),
                          nomorHandphone.toString(),
                          tanggalPesanan.toString(),
                          sharepref.getProductName()
                      )

                      // intent into ConfirmResult
                      context.startActivity(Intent(context,ConfirmResult::class.java))
                      activity.finish()
                },

                modifier = Modifier
                    .padding(top = 20.dp, start = 50.dp, end = 50.dp)
                    .fillMaxWidth(),
                shape = CutCornerShape(10)
            ) {
                Text(
                    "Confirm Order",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            }


        }

    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview5() {
    MyApplicationTheme {
        CheckOutConfirmProduct()
    }
}