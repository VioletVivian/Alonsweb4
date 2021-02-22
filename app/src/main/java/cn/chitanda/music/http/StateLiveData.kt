package cn.chitanda.music.http

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.chitanda.music.http.bean.BaseJson

class StateLiveData<T:BaseJson<*>> : MutableLiveData<RequestStatus<T>>(RequestStatus())

abstract class IStateObserver<T:BaseJson<*>> : Observer<RequestStatus<T>> {
    override fun