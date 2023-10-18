package com.bsoftware.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsoftware.myapplication.dataClass.CheckOutDataClass
import com.bsoftware.myapplication.firebaseCloud.FireBase
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
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
                    val firebase = FireBase()
                    AdminReportList(firebase.initDatabase())
                }
            }
        }
    }
}

@Composable
fun AdminReportList(databasepref : DatabaseReference) {
    var projectList by remember { mutableStateOf(emptyList<CheckOutDataClass>()) }

    LaunchedEffect(databasepref){
        val postListener = object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val project = mutableListOf<CheckOutDataClass>()
                for(projectSnapshot in snapshot.children){
                    val projectGet = projectSnapshot.getValue(CheckOutDataClass::class.java)
                    projectGet?.let{
                        project.add(it)
                    }
                    Log.d("Data get firebase", projectGet.toString())
                }
                projectList = project
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databasepref.addValueEventListener(postListener)
    }

    LazyColumn{
        items(projectList) {project ->
            // card in here
            CardShowAdminProjectList(checkoutProduct = project)
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