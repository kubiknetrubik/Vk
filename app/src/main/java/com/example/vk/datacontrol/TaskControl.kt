package com.example.vk.datacontrol

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)
class TasksRepository(private val context: Context){
    private val gson = Gson()
    suspend fun loadTasks(): List<Task>{
        return withContext(Dispatchers.IO) {
            delay(2000)
            val jsonString = try {
                context.assets.open("tasks.json")
                    .bufferedReader()
                    .use { it.readText() }
            } catch (e: Exception) {
                return@withContext emptyList<Task>()
            }

            val listType = object : TypeToken<List<Task>>() {}.type
            val tasks: List<Task> = gson.fromJson(jsonString, listType)
            return@withContext tasks
        }
    }
}