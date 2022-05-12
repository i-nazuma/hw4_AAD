package at.technikum_wien.polzert.news.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    val text = MutableLiveData("---")

    init {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.collect {
                text.value = it.text
            }
        }
    }

    fun updateText(text : String) {
        viewModelScope.launch {
            userPreferencesRepository.updateText(text)
        }
    }
}

class MyViewModelFactory(private val userPreferencesRepository: UserPreferencesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(userPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
