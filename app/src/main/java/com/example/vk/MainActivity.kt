package com.example.vk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.content.Context
import  androidx.datastore.preferences.preferencesDataStore
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan.Companion.FullLine
import androidx.compose.foundation.clickable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.layout.ContentScale
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import com.google.gson.Gson
import androidx.datastore.preferences.core.edit
import coil.decode.GifDecoder
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.request.CachePolicy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import androidx.lifecycle.viewmodel.compose.viewModel
val Context.dataStore by preferencesDataStore("gif_cache")

data class GiphyResponse(val data: List<GifObject>)
data class GifObject(val images: GifImages)
data class GifImages(val original: OriginalImage)
data class OriginalImage(val url: String)

interface GiphyApi {
    @GET("v1/gifs/trending")
    suspend fun trending(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): GiphyResponse
}
class Repository(
    private val api: GiphyApi,
    private val context: Context

){
    suspend fun loadPage(page: Int): List<String> {
        val offset = page * 20

        val resp = api.trending(
            apiKey = BuildConfig.GiphyK,
            limit = 20,
            offset = offset
        )

        val urls = resp.data.map { it.images.original.url }
        saveCache(urls)
        return urls
    }
    suspend fun readCache(): List<String> {
        val prefs = context.dataStore.data.first()
        val json = prefs[stringPreferencesKey("cache")] ?: ""
        return if (json.isEmpty()) listOf()
        else Gson().fromJson(json, Array<String>::class.java).toList()
    }

    private suspend fun saveCache(list: List<String>) {
        context.dataStore.edit { prefs ->
            prefs[stringPreferencesKey("cache")] = Gson().toJson(list)
        }
    }

}
class GifViewModel(
    private val repo: Repository
) : ViewModel() {

    var gifs by mutableStateOf<List<String>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    private var page = 0

    init {
        loadInitial()
    }

    private fun loadInitial() {
        viewModelScope.launch {
            val cached = repo.readCache()
            if (cached.isNotEmpty()) {
                gifs = cached
            } else {
                loadNext()
            }
        }
    }

    fun loadNext() {
        if (isLoading) return

        isLoading = true
        isError = false

        viewModelScope.launch {
            try {
                val newData = repo.loadPage(page)
                gifs = gifs + newData
                page++
            } catch (e: Exception) {
                isError = true
            } finally {
                isLoading = false
            }
        }
    }
}
class MainActivity : ComponentActivity() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(GiphyApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            val vm: GifViewModel = viewModel(initializer = {GifViewModel(Repository(api, context))})
            MyScreen(vm)
        }
    }
}


@Composable
private fun MyScreen(vm: GifViewModel){
    val context = LocalContext.current
    when {
        vm.isError && vm.gifs.isEmpty() ->
            ErrorScreen(onRetry = vm::loadNext)

        vm.gifs.isEmpty() ->
            LoadingScreen()
        else -> ContentScreen(
            gifs = vm.gifs,
            isLoadingMore = vm.isLoading,
            onBottomReached = vm::loadNext,
            onImageClick = { index ->
                Toast.makeText(context, "GIF №${index + 1}", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
@Composable
fun ErrorScreen(onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource( R.string.err))
            Spacer(Modifier.height(8.dp))
            Button(onClick = onRetry) { Text(stringResource( R.string.retry)) }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentScreen(
    gifs: List<String>,
    onBottomReached: () -> Unit,
    onImageClick: (Int) -> Unit,
    isLoadingMore: Boolean
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(gifs) { index, url ->
            AsyncImageItem(url = url, index = index, onClick = onImageClick)
            if (index == gifs.lastIndex) onBottomReached()
        }

        if (isLoadingMore) {
            item(span = FullLine) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(Modifier.padding(16.dp))
                }
            }
        }
    }
}
@Composable
fun AsyncImageItem(url: String, index: Int, onClick: (Int) -> Unit) {
    var isLoading by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((100..200).random().dp)
            .clickable { onClick(index) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .allowHardware(false)
                .decoderFactory { result, options, _ -> GifDecoder(result.source, options) }
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            onState = { state ->
                isLoading = state is AsyncImagePainter.State.Loading
            }
        )
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

