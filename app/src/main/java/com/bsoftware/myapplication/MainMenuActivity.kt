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
                    ShowPhotoShopData(dataviewmodel = dataviewmodel, lifecycleOwner = this)
                }
            }
        }
    }
}

@Composable
fun ShowPhotoShopData(dataviewmodel : PhotoShopDataViewModelClass,lifecycleOwner: LifecycleOwner){
   var titledata : String?
   var descdata : String?
   var pricedata : String?
   var photo     : String?

   val itemdata : MutableList<PhotoProductDataClass> = mutableListOf()

   dataviewmodel.PhotoshopData.observe(lifecycleOwner, Observer {photoData ->
       for(data in photoData){
           titledata = data.titleProduct
           descdata = data.descProduct
           pricedata = data.priceProduct
           photo = data.photo

           Log.d("data",titledata!!)
           Log.d("data",descdata!!)
           Log.d("data",pricedata!!)
           Log.d("data",photo!!)

           itemdata.add(data)

       }
   })
    Column {
        for(x in itemdata.iterator()){
            Text(text = x.titleProduct!!)
            Text(text = x.descProduct!!)
            Text(text = x.priceProduct!!)
            Text(text = x.photo!!)
        }
    }
    dataviewmodel.getPhotoShopData()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    MyApplicationTheme {

    }
}