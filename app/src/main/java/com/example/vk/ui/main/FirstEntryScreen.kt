package com.example.vk.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vk.R
import com.example.vk.ui.components.bars.BottomBar
import com.example.vk.ui.theme.SignupBackground
import com.example.vk.datacontrol.Task
import com.example.vk.datacontrol.TasksRepository
import com.example.vk.ui.theme.OrangePrimary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
sealed class TasksState {
    object Loading : TasksState()
    data class Success(val tasks: List<Task>) : TasksState()
    data class Error(val message: String) : TasksState()
}
class TaskViewModel (
    private val repo: TasksRepository
): ViewModel(){
    private val _uiState = MutableStateFlow<TasksState>(TasksState.Loading)
    val uiState: StateFlow<TasksState> = _uiState
    init{
        load()
    }

    fun load() {
        if (_uiState.value is TasksState.Success) return
        viewModelScope.launch {
            _uiState.value = TasksState.Loading
            try{
                val tasks = repo.loadTasks()
                if (tasks.isEmpty()){
                    _uiState.value = TasksState.Error(repo.getMessage(R.string.errtasks))
                }
                else{
                    _uiState.value = TasksState.Success(tasks)
                }

            }
            catch (e: Exception){
                _uiState.value = TasksState.Error(repo.getMessage(R.string.errnet))
            }
        }
    }
}
@Composable
fun TaskItem(task:Task){
    val bc = if (task.isCompleted) colorResource(R.color.accent) else colorResource(R.color.white)
    Row(
        modifier= Modifier
            .border(2.dp,OrangePrimary, RoundedCornerShape(10.dp))
            .height(53.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .height(23.dp)
                .width(24.dp)
                .border(2.dp,colorResource(R.color.accent), RoundedCornerShape(5.dp))
                .background(bc,shape = RoundedCornerShape(5.dp))

        ){}
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = task.title,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = task.cost.toString(),
            fontSize = 15.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.dust),
            contentDescription = stringResource(R.string.dust),
            modifier = Modifier
                .height(37.dp)
                .width(25.dp)
        )




    }


    Spacer(modifier = Modifier.height(10.dp))

}

@Composable
fun FirstEntryScreen(vm: TaskViewModel,
onNavigatetoSettings: () -> Unit = {}
) {

    val state by vm.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if(isLandscape){
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Column(){
                Image(
                    painter = painterResource(id = R.drawable.main_fox),
                    contentDescription = null,
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
                BottomBar(onNavigatetoSettings=onNavigatetoSettings)
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.width(150.dp))
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .width(380.dp)
            ) {
                when(state){
                    is TasksState.Loading ->{
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )

                    }
                    is TasksState.Success -> {
                        Spacer(modifier = Modifier.height(20.dp))
                        LazyColumn (
                            modifier = Modifier
                                .height(500.dp)
                                .width(380.dp)
                        ){
                            items((state as TasksState.Success).tasks){task->
                                TaskItem(task=task)
                            }
                        }

                    }
                    is TasksState.Error -> {
                        Text(
                            text = (state as TasksState.Error).message,
                            modifier = Modifier.align(Alignment.Center),
                            color = colorResource(R.color.error)
                        )

                    }
                }
            }
        }

    }else{
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
                contentDescription = stringResource(R.string.fox),
                modifier = Modifier.size(380.dp, 346.dp)
            )
            Box(
                modifier = Modifier
                    .height(280.dp)
                    .width(380.dp)

            ){
                when(state){
                    is TasksState.Loading ->{
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )

                    }
                    is TasksState.Success -> {
                        Spacer(modifier = Modifier.height(20.dp))
                        LazyColumn (
                            modifier = Modifier
                                .height(270.dp)
                                .width(380.dp)
                        ){
                            items((state as TasksState.Success).tasks){task->
                                TaskItem(task=task)
                            }
                        }

                    }
                    is TasksState.Error -> {
                        Text(
                            text = (state as TasksState.Error).message,
                            modifier = Modifier.align(Alignment.Center),
                            color = colorResource(R.color.error)
                        )

                    }
                }


            }

            BottomBar(onNavigatetoSettings=onNavigatetoSettings)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}