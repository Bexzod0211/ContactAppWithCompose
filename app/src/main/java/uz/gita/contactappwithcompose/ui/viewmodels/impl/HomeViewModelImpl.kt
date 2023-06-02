package uz.gita.contactappwithcompose.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.contactappwithcompose.ui.usecase.HomeUseCase
import uz.gita.contactappwithcompose.ui.viewmodels.HomeContract
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val useCase: HomeUseCase,
    private val direction:HomeContract.Direction
) : HomeContract.ViewModel, ViewModel() {
    //    override val contactsLiveData: LiveData<List<ContactData>>
//        get() = useCase.getAllContacts().asLiveData()
    /* override val contactsLiveData: MutableLiveData<List<ContactData>> = MutableLiveData()
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
    }*/
    override val uiState: MutableStateFlow<HomeContract.UIState> = MutableStateFlow(HomeContract.UIState())


    /*init {
        useCase.getAllContacts().onEach {contacts->
            uiState.update {
                it.copy(contacts = contacts)
            }
        }
            .launchIn(viewModelScope)
    }*/

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when(intent){
            is HomeContract.Intent.OpenAddContact->{
                viewModelScope.launch {
                    direction.navigateToAddContactScreen()
                }
//                uiState.update {
//                    it.copy(openAddContactState = true)
//                }
            }
            is HomeContract.Intent.EditContact->{
                viewModelScope.launch {
                    direction.navigateToAddContactScreen(intent.contact)
                }
//                uiState.update {
//                    it.copy(editContact = intent.contact, openEditContactState = true)
//                }
            }
            is HomeContract.Intent.DeleteContact->{
                useCase.deleteContact(intent.entity).onEach {
                    uiState.update {
                        it.copy(message = "Contact by name ${intent.entity.firstName} has been deleted")
                    }
                }.launchIn(viewModelScope)
            }
            is HomeContract.Intent.ClearMessage->{
                uiState.update {
                    it.copy(message = "")
                }
            }
            is HomeContract.Intent.ClearOpenScreen->{
                uiState.update {
                    it.copy(openAddContactState = false, openEditContactState = false)
                }
            }
            is HomeContract.Intent.LoadAllContacts->{
                useCase.getAllContacts().onEach {result ->
                    result.onSuccess {contacts->
                        uiState.update {
                            it.copy(contacts = contacts)
                        }
                    }
                    result.onFailure { e->
                        uiState.update {data->
                            data.copy(message = e.message?:"null")
                        }
                    }
                }
                    .launchIn(viewModelScope)
            }
        }
    }


}