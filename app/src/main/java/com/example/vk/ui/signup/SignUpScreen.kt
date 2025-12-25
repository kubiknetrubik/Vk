package com.example.vk.ui.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk.R
import com.example.vk.ui.components.buttons.AppleSignInButton
import com.example.vk.ui.components.buttons.EmailSignInButton
import com.example.vk.ui.components.buttons.GoogleSignInButton
import com.example.vk.ui.components.buttons.SkipButton
import com.example.vk.ui.components.buttons.VkSignInButton
import com.example.vk.ui.theme.BrownText
import com.example.vk.ui.theme.VkTheme
import com.example.vk.ui.theme.SignupBackground

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onNavigateToApple: () -> Unit = {},
    onNavigateToGoogle: () -> Unit = {},
    onNavigateToVk: () -> Unit = {},
    onNavigateToEmail: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onSkipClick: () -> Unit = {}
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SignupBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))


        Image(
            painter = painterResource(id = R.drawable.main_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(282.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))


        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(44.dp)


        GoogleSignInButton(
            modifier = buttonModifier,
            onClick = onNavigateToGoogle
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppleSignInButton(
            modifier = buttonModifier,
            onClick = onNavigateToApple
        )

        Spacer(modifier = Modifier.height(12.dp))

        VkSignInButton(
            modifier = buttonModifier,
            onClick = onNavigateToVk
        )

        Spacer(modifier = Modifier.height(12.dp))

        EmailSignInButton(
            modifier = buttonModifier,
            onClick = onNavigateToEmail
        )

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Уже есть аккаунт?",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = BrownText,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                onSignInClick()
                Toast.makeText(context, "Sign in clicked", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))


        SkipButton(
            onClick = onSkipClick
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    VkTheme {
        SignUpScreen()
    }
}