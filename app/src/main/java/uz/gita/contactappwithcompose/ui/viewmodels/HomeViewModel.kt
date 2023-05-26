package uz.gita.contactappwithcompose.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappwithcompose.data.model.ContactData

interface HomeViewModel {
    val contactsLiveData:LiveData<List<ContactData>>

}