package com.idisfkj.androidapianalysis

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.os.Handler

/**
 * Created by idisfkj on 2018/5/25.
 * Email : idisfkj@gmail.com.
 */
class ContactsViewModel(application: Application, private val defTitle: String = "Contacts") : AndroidViewModel(application) {
    val contactsList: MutableLiveData<List<ContactsModel>> by lazy { MutableLiveData<List<ContactsModel>>() }
    val message: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val title: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val MDELAY_MILLIS = 3000L

    private val mRemoteData = mutableListOf(
            createContactsData(1, "张一", "15689203870"),
            createContactsData(2, "张二", "15689203871"),
            createContactsData(3, "张三", "15689203872"),
            createContactsData(4, "张四", "15689203873"),
            createContactsData(5, "张五", "15689203874"),
            createContactsData(6, "张六", "15689203875"),
            createContactsData(7, "张七", "15689203876"),
            createContactsData(8, "张八", "15689203877"),
            createContactsData(9, "张九", "15689203878")
    )

    fun start() {
        if (contactsList.value?.size ?: 0 <= 0) {
            message.value = "数据请求中，请稍后！"
            title.value = defTitle
            Handler().postDelayed({
                contactsList.value = mRemoteData
                Thread(Runnable {
                    title.postValue("Display Contacts")
                }).start()
//                Log.d("idisfkj", "background request")
                message.value = "数据加载完成~"
            }, MDELAY_MILLIS)
        }
    }

    private fun createContactsData(id: Int, name: String, phone: String): ContactsModel = ContactsModel(id, name, phone)
}