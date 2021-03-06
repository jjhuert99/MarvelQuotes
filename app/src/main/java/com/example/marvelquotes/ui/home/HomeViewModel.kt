package com.example.marvelquotes.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelquotes.network.Quote
import com.example.marvelquotes.network.QuoteRepo
import com.example.marvelquotes.network.ServiceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val QuoteRepo: QuoteRepo,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    enum class QuoteStatus {ERROR, DONE}

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private val _status = MutableLiveData<QuoteStatus>()
    val status: LiveData<QuoteStatus> = _status

    private val _post = MutableLiveData<Quote?>()
    val post: LiveData<Quote?> = _post

    fun getQuotePost() {
        viewModelScope.launch(dispatcher){
            when(val response = QuoteRepo.getQuote()){
                is ServiceResult.Succes ->{
                    _post.postValue(response.data)
                    _status.postValue(QuoteStatus.DONE)
                }
                is ServiceResult.Error ->{
                    _status.postValue(QuoteStatus.ERROR)

                }
                else ->{
                    _status.postValue(QuoteStatus.ERROR)

                }
            }
        }
    }
}
