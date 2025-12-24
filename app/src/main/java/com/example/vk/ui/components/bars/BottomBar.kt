package com.example.vk.ui.components.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vk.R

@Composable
fun BottomBar(
    onNavigatetoSettings: () -> Unit = {},
    onNavigatetoTasks: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(110.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.shop),
            contentDescription = stringResource(R.string.shop),
            modifier = Modifier
                .size(60.dp, 90.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Image(
            painter = painterResource(id = R.drawable.fox_icon),
            contentDescription =stringResource(R.string.mainmenu),
            modifier = Modifier
                .size(121.dp, 122.dp)
                .clickable{onNavigatetoTasks()}
        )

        Spacer(modifier = Modifier.width(2.dp))

        Image(
            painter = painterResource(id = R.drawable.settings),
            contentDescription = stringResource(R.string.settings),
            modifier = Modifier
                .size(60.dp, 91.dp)
                .clickable{onNavigatetoSettings()}
        )
    }
}
