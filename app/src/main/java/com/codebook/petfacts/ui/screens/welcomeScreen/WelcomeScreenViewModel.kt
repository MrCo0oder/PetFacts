package com.codebook.petfacts.ui.screens.welcomeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.codebook.petfacts.NetworkState
import com.codebook.petfacts.data.datasrc.CatFact
import com.codebook.petfacts.data.datasrc.DogFact
import com.codebook.petfacts.data.repo.WelcomeScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val repo: WelcomeScreenRepo
) : ViewModel() {
    private val _catFact: MutableStateFlow<NetworkState<CatFact>> =
        MutableStateFlow(NetworkState.Loading())
    val catFact: StateFlow<NetworkState<CatFact>> = _catFact

    private val _dogFact: MutableStateFlow<NetworkState<DogFact>> =
        MutableStateFlow(NetworkState.Loading())
    val dogFact: StateFlow<NetworkState<DogFact>> = _dogFact

    private fun coroutineExceptionHandler() = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private var coroutineScope: CoroutineScope
    private val viewModelJob = Job()

    init {
        coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
        getCatFact()
        getDogFact()
    }

    fun getCatFact() {
        coroutineScope.launch(coroutineExceptionHandler()) {
            repo.getCatsFacts().collectLatest {
                _catFact.value = it
                Log.d("CAT", it.toString())
            }
        }
    }

    fun getDogFact() {
        coroutineScope.launch(coroutineExceptionHandler()) {
            repo.getDogsFacts().collectLatest {
                _dogFact.value = it
                Log.d("DOG", it.toString())
            }
        }
    }
}