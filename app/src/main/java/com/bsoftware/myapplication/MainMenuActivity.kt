package com.bsoftware.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
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
                    ShowPhotoShopData(dataviewmodel,this)
                }
            }
        }
    }
}

@Composable
fun ShowPhotoShopData(dataviewmodel : PhotoShopDataViewModelClass,lifecycleOwner: LifecycleOwner){

    var titleproduct : String?
    var descproduct  : String?
    var priceproduct : String?
    var photo        : String?


    LazyColumn{
        dataviewmodel.PhotoshopData.observe(lifecycleOwner, Observer {photoshopdata ->
            for(dataPhoto in photoshopdata){
                titleproduct = dataPhoto.titleProduct
                descproduct = dataPhoto.descProduct
                priceproduct = dataPhoto.priceProduct
                photo = dataPhoto.photo

                Log.d("data",titleproduct!!)
                Log.d("data",descproduct!!)
                Log.d("data",priceproduct!!)
                Log.d("data",photo!!)

                items(dataPhoto.id!!){ id ->
                    Card(
                        modifier = Modifier.size(width = 200.dp, height = 200.dp)
                    ) {
                       Column {
                           Text(text = titleproduct!!)
                           Text(text = descproduct!!)
                           Text(text = priceproduct!!)
                           Text(text = photo!!)
                       }
                    }
                }
            }
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    MyApplicationTheme {
        //showPhotoShopData()
    }
}