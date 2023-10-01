package com.bsoftware.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsoftware.myapplication.ui.theme.MyApplicationTheme

class UploadProduct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InputData()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputData(){
    var titleProduct by remember { mutableStateOf("") }
    var descProduct by remember { mutableStateOf("") }
    var priceProduct by remember { mutableStateOf("") }
    var photo by remember { mutableStateOf("") }

    var imageURI by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember{ mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){uri : Uri? ->
        // image uri in here yes
        imageURI = uri
    }

    imageURI?.let{
        if(Build.VERSION.SDK_INT < 28){
            // if device low sdk
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver,it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver,it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = titleProduct,
            onValueChange = { titleProduct = it },
            label = {
                Text(text = "Product Name")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = priceProduct,
            onValueChange = { priceProduct = it },
            label = {
                Text(text = "Price")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = descProduct,
            onValueChange = { descProduct = it },
            label = {
                Text(text = "Description")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = {
                launcher.launch("image/*")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "Upload photo")
        }

        bitmap.value?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )
        }

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview4() {
    MyApplicationTheme {
       InputData()
    }
}