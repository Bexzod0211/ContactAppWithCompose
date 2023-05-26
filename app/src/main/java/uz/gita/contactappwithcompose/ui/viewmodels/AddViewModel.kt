package uz.gita.contactappwithcompose.ui.viewmodels

import androidx.lifecycle.LiveData

interface AddViewModel {
    val messageLiveData:LiveData<String>
    val popScreenLiveData:LiveData<Unit>
    fun addContact(id:Int,firsName:String,lastName:String,phone:String):Boolean
}