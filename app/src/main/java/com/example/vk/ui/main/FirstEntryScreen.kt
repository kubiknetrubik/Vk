package com.example.vk.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk.R
import com.example.vk.ui.components.bars.BottomBar
import com.example.vk.ui.theme.SignupBackground
import com.example.vk.datacontrol.Task
import com.example.vk.datacontrol.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TaskViewModel (
    private val repo: TasksRepository
): ViewModel(){
    private fun load() {
        viewModelScope.launch {
            val tasks = repo.loadTasks()
        }
    }
}
@Composable
fun TaskItem(task:Task){

}
@Preview
@Composable
fun FirstEntryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SignupBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.main_fox),
            contentDescription = "fox",
            modifier = Modifier.size(380.dp, 346.dp)
        )
        LazyColumn {
            items(tasks){task->
                TaskItem(task=task)
            }
        }

        BottomBar()
        Spacer(modifier = Modifier.height(16.dp))
    }
}