package com.example.vk.ui.components.fields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk.R
import com.example.vk.ui.theme.AppTextColor
import com.example.vk.ui.theme.OrangePrimary

class PasswordVisualTransformation(private val mask: Char = '*') : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString("*".repeat(text.length)),
            OffsetMapping.Identity
        )
    }
}

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    value: String = "",
    borderColor: Color = OrangePrimary,
    onValueChange: (String) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFC2A17E),
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTextColor
                ),
                singleLine = true,
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation('*')
                }
            )
            
            // Иконка глаза
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 4.dp)
            ) {
                Icon(
                    painter = if (passwordVisible) {
                        painterResource(id = R.drawable.open_eye)
                    } else {
                        painterResource(id = R.drawable.closed_eye)
                    },
                    contentDescription = if (passwordVisible) "Скрыть пароль" else "Показать пароль",
                    tint = Color(0xFFC2A17E),
                    modifier = Modifier.size(if (passwordVisible) 24.dp else 23.57.dp, if (passwordVisible) 21.5.dp else 12.4.dp)
                )
            }
        }
    }
}