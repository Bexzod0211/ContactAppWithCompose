package uz.gita.contactappwithcompose.ui.viewmodels

import kotlinx.coroutines.flow.StateFlow

/*
interface AddViewModel {
    val messageLiveData:LiveData<String>
    val popScreenLiveData:LiveData<Unit>
    fun addContact(id:Int,firsName:String,lastName:String,phone:String):Boolean
}*/


interface AddContactContract {
    sealed interface Intent {
        class AddContact(val id:Int,val firstName:String,val lastName:String, val phone:String):Intent
        object ClearMessage:Intent
        object PopScreen:Intent
        object ClearPopScreen:Intent

    }

    data class UIState(
        val errorMessage:String = "",
        val message:String = "",
        val popScreenState:Boolean = false
    )

    interface ViewModel {
        val uiState:StateFlow<UIState>

        fun onEventDispatcher(intent:Intent)
    }

    interface Direction {
        suspend fun navigateToHomeScreen()
    }
}