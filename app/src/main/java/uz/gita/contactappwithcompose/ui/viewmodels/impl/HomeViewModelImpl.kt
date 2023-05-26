package uz.gita.contactappwithcompose.ui.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import uz.gita.contactappwithcompose.ui.usecase.HomeUseCase
import uz.gita.contactappwithcompose.ui.viewmodels.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val useCase: HomeUseCase
) : HomeViewModel, ViewModel() {
    //    override val contactsLiveData: LiveData<List<ContactData>>
//        get() = useCase.getAllContacts().asLiveData()
    override val contactsLiveData: MutableLiveData<List<ContactData>> = MutableLiveData()
    override val messageLiveData: MutableLiveData<String> = MutableLiveData()

    override fun loadAllContacts() {
        useCase.getAllContacts().onEach {
            contactsLiveData.value = it
        }.launchIn(viewModelScope)
    }

    override fun deleteContact(contact: ContactEntity) {
        useCase.deleteContact(contact).onEach {
            messageLiveData.value = "Contact by name ${contact.firstName} has been deleted"
            useCase.getAllContacts().onEach {
                contactsLiveData.value  = it
            }.launchIn(viewModelScope)
        }
            .launchIn(viewModelScope)
    }

    init {
        useCase.getAllContacts().onEach {
            contactsLiveData.value = it
        }
            .launchIn(viewModelScope)
    }




}