package com.example.vk.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk.R
import com.example.vk.ui.components.bars.BottomBar
import com.example.vk.ui.theme.OrangePrimary
import com.example.vk.ui.theme.SignupBackground
@Composable
fun SettingsPart(text:String){
    Row(
        modifier= Modifier
            .border(2.dp,OrangePrimary, RoundedCornerShape(10.dp))
            .height(53.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f)
        )
    }
}
@Composable
fun SettingsScreen(login: String? = "", email: String?="", password: String?="", onNavigatetoTasks: () -> Unit = {}){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SignupBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        BottomBar(onNavigatetoTasks=onNavigatetoTasks)

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(SignupBackground)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Профиль",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(text = "Логин", fontSize = 14.sp, color = colorResource(R.color.black))
        SettingsPart(login ?: "")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Электронная почта", fontSize = 14.sp, color = colorResource(R.color.black))
        SettingsPart(email ?: "")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Пароль", fontSize = 14.sp, color = colorResource(R.color.black))
        SettingsPart(password ?: "")
    }


}