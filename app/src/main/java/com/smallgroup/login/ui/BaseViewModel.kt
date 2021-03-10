package com.smallgroup.login.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smallgroup.login.repo.Api
import com.smallgroup.login.repo.NetworkService
import com.smallgroup.login.repo.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    var api: Api = NetworkService.retrofitService()
//
//    fun <T : Any> requestWithLiveData(
//            liveData: MutableLiveData<Event<T>>,
//            request: suspend() -> ResponseWrapper<T>) {
//
//        liveData.postValue(Event.Loading)
//
//        this.viewModelScope.launch(Dispatchers.IO){
//            try{
//                val response = request.invoke()
//                if(response.data != null){
//                    liveData.postValue(Event.Success(response.data))
//                }
//                else if (response.error != null){
//                    liveData.postValue(Event.Failure(response.error))
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                liveData.postValue(Event.Failure(null))
//            }
//        }
//
//    }
//
//    fun <T> requestWithCallback(
//            request: suspend () -> ResponseWrapper<T>,
//            response: (Event<T>) -> Unit) {
//
//        response(Event.loading())
//
//        this.viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val res = request.invoke()
//
//                launch(Dispatchers.Main) {
//                    if (res.data != null) {
//                        response(Event.success(res.data))
//                    } else if (res.error != null) {
//                        response(Event.error(res.error))
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                launch(Dispatchers.Main) {
//                    response(Event.error(null))
//                }
//            }
//        }
//    }

}