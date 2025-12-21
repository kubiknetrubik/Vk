package com.example.vk.ui.components.buttons

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk.ui.theme.OrangeContinue
import com.example.vk.ui.theme.OrangePrimary

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current

    Button(
        onClick = {
            onClick()
            Toast.makeText(context, "Continue clicked", Toast.LENGTH_SHORT).show()
        },
        modifier = modifier
            .size(287.dp, 51.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = OrangeContinue
        )
    ) {
        Text(
            text = "Продолжить",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}