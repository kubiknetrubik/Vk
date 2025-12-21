package com.example.vk.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vk.ui.components.bars.BottomBar
import com.example.vk.ui.theme.SignupBackground

@Composable
fun FirstEntryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SignupBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        BottomBar()
        Spacer(modifier = Modifier.height(16.dp))
    }
}