package com.bsoftware.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftware.myapplication.dataClass.CheckOutDataClass
import com.bsoftware.myapplication.firebaseCloud.FireBase
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminReport : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val firebase = FirebaseDatabase.getInstance()
                    val reference = firebase.getReference("checkOut")

                    AdminReportList(reference)
                }
            }
        }
    }
}

@Composable
fun AdminReportList(databasepref : DatabaseReference) {
    var projectList = remember { mutableStateListOf<CheckOutDataClass>() }

    LaunchedEffect(databasepref){
        val postListener = object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

               if(snapshot.exists()){
                   projectList.clear()

                   for(projectSnapshot in snapshot.children){
                       val dataMap = projectSnapshot.value as? Map<*,*>?

                       if(dataMap != null){
                           val checkOutDataClass = CheckOutDataClass(
                               id = dataMap["id"] as? String ?: "",
                               name = dataMap["name"] as? String ?: "",
                               date = dataMap["date"] as? String ?: "",
                               numberPhone = dataMap["numberPhone"] as? String ?: "",
                               projectType = dataMap["projectType"] as? String ?: ""
                           )
                           projectList.add(checkOutDataClass)
                       }
                   }
               }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databasepref.addValueEventListener(postListener)
    }

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
                        text = "History",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Client history",
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

            LazyColumn{
                items(projectList) {project ->
                    // card in here
                    CardShowAdminProjectList(checkoutProduct = project)
                }
            }
        }

    }

}

@Composable
fun CardShowAdminProjectList(checkoutProduct : CheckOutDataClass){

    Card(
        modifier = Modifier
            .size(500.dp, 170.dp)
            .padding(10.dp)
    ){
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.kode_pesanan,checkoutProduct.id),
            )

            Text(
                text = stringResource(id = R.string.nama_pemesan,checkoutProduct.name),
                modifier = Modifier.padding(top = 5.dp)
            )

            Text(
                text = stringResource(id = R.string.tanggal_pesanan,checkoutProduct.date),
                modifier = Modifier.padding(top = 5.dp)
            )

            Text(
                text = stringResource(id = R.string.nomor_handphone,checkoutProduct.numberPhone),
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminReportPreview() {
    MyApplicationTheme {
        val testData = CheckOutDataClass("hd5eh3","hello","3446576","4325435","Test")
        CardShowAdminProjectList(checkoutProduct = testData)
    }
}