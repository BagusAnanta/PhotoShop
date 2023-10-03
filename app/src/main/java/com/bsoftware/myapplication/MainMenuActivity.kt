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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
                    ShowPhotoShopData(dataviewmodel = dataviewmodel, lifecycleOwner = this)
                }
            }
        }
    }
}

@Composable
fun ShowPhotoShopData(dataviewmodel : PhotoShopDataViewModelClass,lifecycleOwner: LifecycleOwner){
    var id : Int?
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
           id = data.id

           Log.d("data",titledata!!)
           Log.d("data",descdata!!)
           Log.d("data",pricedata!!)
           Log.d("data",photo!!)
           Log.d("data",id.toString())

           itemdata.add(data)

       }
   })
    Column {
        LazyRow(
            contentPadding = PaddingValues(10.dp)
        ){
            items(itemdata){item ->
                ContentProduct(
                    item.titleProduct,
                    item.descProduct,
                    item.photo
                )
            }
        }
    }
    dataviewmodel.getPhotoShopData()
}

@Composable
fun ContentProduct(titleProduct : String?,descProduct : String?,photoUrl : String?){

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photoUrl)
            .size(Size.ORIGINAL)
            .build()
    )

   Card(
       modifier = Modifier.size(width = 200.dp, height = 250.dp)
   ) {
      Column{
          Image(
              painter = painter,
              contentDescription = "exampleimage",
          )
          Text(
              text = titleProduct!!,
              modifier = Modifier.padding(start = 5.dp, top = 5.dp),
              style = TextStyle(
                  fontSize = 15.sp,
                  color = Color.Black
              )

          )
          Text(
              text = descProduct!!,
              modifier = Modifier.padding(start = 5.dp, top = 10.dp, end = 5.dp),
              style = TextStyle(
                  fontSize = 12.sp,
                  color = Color.Black
              )
          )
      }
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MyApplicationTheme {
        ContentProduct(
            "Hello",
            "This is example of desc product you can place in here",
            "https://img.freepik.com/free-photo/top-view-small-sour-blue-black-sloe-bucket-with-leaves-grey-background-with-copy-space-jpg_141793-20509.jpg?w=740&t=st=1696296166~exp=1696296766~hmac=3a70411d22d957bb92c35e89847943bfc87d82b72321dd23107c3b5116dd6b94"
        )
    }
}