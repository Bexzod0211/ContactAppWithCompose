package uz.gita.contactappwithcompose.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import uz.gita.contactappwithcompose.navigation.AppNavigator
import uz.gita.contactappwithcompose.ui.usecase.AddUseCase
import uz.gita.contactappwithcompose.ui.viewmodels.AddContactContract
import uz.gita.contactappwithcompose.utils.myLog
import javax.inject.Inject

@HiltViewModel
class AddViewModelImpl @Inject constructor(
    private val useCase: AddUseCase,
    private val direction:AddContactContract.Direction
) : AddContactContract.ViewModel, ViewModel() {
    /* override val messageLiveData: MutableLiveData<String> = MutableLiveData()
    override val popScreenLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun addContact(id: Int, firsName: String, lastName: String, phone: String): Boolean {
        if (firsName.trim().isEmpty()){
            messageLiveData.value = "Please enter firstname"
            return false
        }
        if (lastName.trim().isEmpty()){
            messageLiveData.value = "Please enter lastname"
            return false
        }
        if (phone.trim().isEmpty()){
            messageLiveData.value = "Please enter phone number"
            return false
        }

        if (!phone.trim().startsWith("+998")||phone.trim().length != 13 ){
            messageLiveData.value = "Phone number is not correct"
            return false
        }

        useCase.addContact(ContactEntity(id,firsName,lastName,phone))
        popScreenLiveData.value = Unit
        return true
    }*/
    override val uiState: MutableStateFlow<AddContactContract.UIState> = MutableStateFlow(AddContactContract.UIState())

    override fun onEventDispatcher(intent: AddContactContract.Intent) {
        when (intent) {
            is AddContactContract.Intent.AddContact -> {
                if (intent.firstName.trim().isEmpty()) {
                    uiState.update {
                        it.copy(errorMessage = "Please enter firstname")
                    }
                    return
                }
                if (intent.lastName.trim().isEmpty()) {
                    uiState.update {
                        it.copy(errorMessage = "Please enter lastname")
                    }
                    return
                }
                if (intent.phone.trim().isEmpty()) {
                    uiState.update {
                        it.copy(errorMessage = "Please enter phone number")
                    }
                    return
                }
                if (!intent.phone.trim().startsWith("+998") || intent.phone.trim().length != 13) {
                    uiState.update {
                        it.copy(errorMessage = "Phone number is not correct")
                    }
                    return
                }
                useCase.addContact(ContactEntity(intent.id, intent.firstName, intent.lastName, intent.phone))
                viewModelScope.launch {
                    uiState.update {
                        myLog("sending message")
                        it.copy(message = "Contact has been successfully ")
                    }
                    delay(10)
                    direction.navigateToHomeScreen()
                }
            }

            is AddContactContract.Intent.ClearMessage -> {
                uiState.update {
                    it.copy(errorMessage = "", message = "")
                }
            }

            is AddContactContract.Intent.PopScreen -> {
                uiState.update {
                    it.copy(popScreenState = true)
                }
            }

            is AddContactContract.Intent.ClearPopScreen -> {
                uiState.update {
                    it.copy(popScreenState = false)
                }
            }
        }
    }

}