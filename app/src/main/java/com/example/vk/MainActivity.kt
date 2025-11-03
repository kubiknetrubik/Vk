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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.foundation.layout.width
import  androidx.compose.foundation.layout.aspectRatio
import androidx.compose.ui.unit.sp

object AppDemens{
    val toppadding=10.dp
    val startpadding=10.dp
    val bottompadding= 80.dp
    val endpadding = 10.dp
    val buttonpadding = 70.dp
    val horspace = 30.dp
    val verspace = 30.dp
    val verticalpadding = 30.dp
    val horizontalpadding = 10.dp
    val text_size = 30.sp
    val verticalpositioncolumnquantity=3
    val horizontalpositioncolumnquantity=4

}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        columnq = AppDemens.horizontalpositioncolumnquantity
    } else{
        columnq = AppDemens.verticalpositioncolumnquantity
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = AppDemens.horizontalpadding, vertical = AppDemens.verticalpadding)
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnq),
            contentPadding = PaddingValues(start = AppDemens.startpadding, end = AppDemens.endpadding, top= AppDemens.toppadding, bottom= AppDemens.bottompadding),
            verticalArrangement = Arrangement.spacedBy(AppDemens.verspace),
            horizontalArrangement =Arrangement.spacedBy(AppDemens.horspace),
            modifier = Modifier.fillMaxSize()
                .padding(bottom = AppDemens.buttonpadding)
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
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(color = backgroundColor)
                ){
                    Text(
                        text=number.toString(),
                        color = colorResource( R.color.text_color),
                        fontSize = AppDemens.text_size,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )


                }

            }
        }
        Button(onClick = addItem,
            modifier = Modifier
                .align(Alignment.BottomEnd)

        ){
            Icon(
                painter = painterResource(R.drawable.add_icon),
                contentDescription = null
            )

        }
    }


}