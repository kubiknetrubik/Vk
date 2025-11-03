package com.example.vk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vk.ui.theme.VkTheme
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.res.colorResource
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreen(){
    var itemCount by rememberSaveable { mutableIntStateOf(0) }
    val list = (0 until itemCount).toList()
    val addItem: () -> Unit = { itemCount++ }
    val configuration = LocalConfiguration.current
    var columnq=0
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
        columnq = 4
    } else{
        columnq = 3
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp, vertical = 10.dp)
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnq),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.fillMaxSize()
        ){
            items(
                count = list.size,
                key={it}
            ){number ->
                val backgroundColor = if(number%2==0){
                    colorResource( R.color.red_square)
                }else{
                    colorResource( R.color.blue_square)
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = backgroundColor)
                    .padding(horizontal = 3.dp, vertical = 7.dp)
                ){
                    Text(
                        text=number.toString(),
                        color = colorResource( R.color.text),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )


                }

            }
        }
        Button(onClick = addItem,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.add_icon),
                contentDescription = null
            )

        }
    }


}