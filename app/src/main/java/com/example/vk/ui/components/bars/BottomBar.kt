package com.example.vk.ui.components.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.vk.R

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .height(122.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Иконка магазина
        Image(
            painter = painterResource(id = R.drawable.shop),
            contentDescription = "Shop",
            modifier = Modifier.size(60.dp, 90.dp)
        )

        // Отступ между иконками
        Spacer(modifier = Modifier.width(2.dp))

        // Иконка лисенка
        Image(
            painter = painterResource(id = R.drawable.fox_icon),
            contentDescription = "Fox Icon",
            modifier = Modifier.size(121.dp, 122.dp)
        )

        // Отступ между иконками
        Spacer(modifier = Modifier.width(2.dp))

        // Иконка настроек
        Image(
            painter = painterResource(id = R.drawable.settings),
            contentDescription = "Settings",
            modifier = Modifier.size(60.dp, 91.dp)
        )
    }
}