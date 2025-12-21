package com.example.vk.ui.components.fields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk.ui.theme.BrownText
import com.example.vk.ui.theme.OrangePrimary

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, OrangePrimary),
        enabled = false
    ) {
        Text(
            text = placeholder,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = BrownText
        )
    }
}