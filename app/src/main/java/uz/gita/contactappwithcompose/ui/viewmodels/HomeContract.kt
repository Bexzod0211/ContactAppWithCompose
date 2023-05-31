package uz.gita.contactappwithcompose.ui.viewmodels

import kotlinx.coroutines.flow.StateFlow
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

/*
interface HomeViewModel {
    val contactsLiveData:LiveData<List<ContactData>>
    val messageLiveData:LiveData<String>
    fun loadAllContacts()

    fun deleteContact(contact:ContactEntity)
}*/

interface HomeContract {
    sealed interface Intent {
        object OpenAddContact:Intent
        class EditContact(val contact: ContactData):Intent
        class DeleteContact(val entity:ContactEntity):Intent
        object ClearMessage:Intent
        object ClearOpenScreen:Intent
        object LoadAllContacts:Intent
    }

    data class UIState(
        val openAddContactState:Boolean = false,
        val contacts:List<ContactData> = listOf(),
        val message:String = "",
        val editContact:ContactData? = null,
        val openEditContactState:Boolean = false
    )

    interface ViewModel {
        val uiState:StateFlow<UIState>

        fun onEventDispatcher(intent:Intent)
    }

    interface Direction {
        suspend fun navigateToAddContactScreen()
        suspend fun navigateToAddContactScreen(contact:ContactData)

    }
}