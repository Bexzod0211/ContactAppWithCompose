package uz.gita.contactappwithcompose.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

interface HomeViewModel {
    val contactsLiveData:LiveData<List<ContactData>>
    val messageLiveData:LiveData<String>
    fun loadAllContacts()

    fun deleteContact(contact:ContactEntity)
}