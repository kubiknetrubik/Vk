package com.example.vk.ui.welcome

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vk.R
import com.example.vk.navigation.AppScreens
import com.example.vk.ui.components.buttons.ContinueButton
import com.example.vk.ui.theme.AppTextColor
import com.example.vk.ui.theme.Error
import com.example.vk.ui.theme.OrangePrimary
import com.example.vk.ui.theme.SignupBackground

@Composable
fun WelcomeScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SignupBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))


            Image(
                painter = painterResource(id = R.drawable.fox_welcomes),
                contentDescription = "Welcome Fox",
                modifier = Modifier.size(412.dp, 435.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Box(
                modifier = Modifier
                    .width(500.dp)
                    .height(70.dp)
                    .padding(horizontal = 32.dp)
                    .border(2.dp, OrangePrimary, RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Добро пожаловать в ")
                        withStyle(style = SpanStyle(color = Error)) {
                            append("pixi!")
                        }
                        append(" Это Ваш новый друг, Лисёнок. Ставьте и выполняйте задачи, чтобы заботиться о нём.")
                    },
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTextColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            ContinueButton(
                onClick = {
                    // Переход на экран первого входа
                    navController.navigate(AppScreens.FirstEntryScreen.route)
                }
            )
        }


        Image(
            painter = painterResource(id = R.drawable.fox_icon),
            contentDescription = "Fox Icon",
            modifier = Modifier.size(121.dp, 122.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}