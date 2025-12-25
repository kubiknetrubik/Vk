package com.example.vk.ui.registration

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vk.R
import com.example.vk.ui.components.buttons.ContinueButton
import com.example.vk.ui.components.fields.PasswordInputField
import com.example.vk.ui.components.fields.TextInputField
import com.example.vk.ui.theme.BrownText
import com.example.vk.ui.theme.Error
import com.example.vk.ui.theme.OrangePrimary
import com.example.vk.ui.theme.SignupBackground

@Composable
fun EmailRegistrationScreen(navController: NavController) {
    val context = LocalContext.current

    var login by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var onclick by remember { mutableStateOf(false) }


    val passwordsMatch = password == confirmPassword || confirmPassword.isEmpty()
    

    val showError = !passwordsMatch && confirmPassword.isNotEmpty()

    Column(
        modifier = Modifier
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
            modifier = Modifier.size(282.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        val inputModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)


        TextInputField(
            modifier = inputModifier,
            placeholder = "Логин",
            value = login,
            onValueChange = { login = it }
        )

        Spacer(modifier = Modifier.height(12.dp))


        TextInputField(
            modifier = inputModifier,
            placeholder = "Почта",
            value = email,
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.height(12.dp))


        PasswordInputField(
            modifier = inputModifier,
            placeholder = "Пароль",
            value = password,
            onValueChange = { password = it },
            passwordVisible= onclick,
            onclickpass = {onclick = !onclick}

        )
        Spacer(modifier = Modifier.height(12.dp))
        PasswordInputField(
            modifier = inputModifier,
            placeholder = "Пароль",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            passwordVisible = onclick,
            onclickpass = {onclick = !onclick}
        )

        if (showError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Пароли должны совпадать",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Уже есть аккаунт? Авторизируйтесь",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = BrownText,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                Toast.makeText(context, "Sign in clicked", Toast.LENGTH_SHORT).show()

            }
        )

        Spacer(modifier = Modifier.height(12.dp))


        ContinueButton(
            onClick = {
                Toast.makeText(context, "Continue with Email clicked", Toast.LENGTH_SHORT).show()

                navController.navigate("welcome/$login/$email/$password")

            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}