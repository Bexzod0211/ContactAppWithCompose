package uz.gita.contactappwithcompose.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import uz.gita.contactappwithcompose.ui.usecase.AddUseCase
import uz.gita.contactappwithcompose.ui.viewmodels.AddContactContract
import javax.inject.Inject

@HiltViewModel
class AddViewModelImpl @Inject constructor(
    private val useCase: AddUseCase
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
        when(intent){
            is  AddContactContract.Intent.AddContact->{
                if (intent.firstName.trim().isEmpty()){
                    uiState.update {
                        it.copy(message = "Please enter firstname")
                    }
                    return
                }
                if (intent.lastName.trim().isEmpty()){
                    uiState.update {
                        it.copy(message = "Please enter lastname")
                    }
                    return
                }
                if (intent.phone.trim().isEmpty()){
                    uiState.update {
                        it.copy(message = "Please enter phone number")
                    }
                    return
                }
                if (!intent.phone.trim().startsWith("+998")||intent.phone.trim().length != 13 ){
                    uiState.update {
                        it.copy(message = "Phone number is not correct")
                    }
                    return
                }
                useCase.addContact(ContactEntity(intent.id,intent.firstName,intent.lastName,intent.phone))
                uiState.update {
                    it.copy(message = "Contact has been successfully ", popScreenState = true)
                }
            }
            is AddContactContract.Intent.ClearMessage ->{
                uiState.update {
                    it.copy(message = "")
                }
            }
            is AddContactContract.Intent.PopScreen ->{
                uiState.update {
                    it.copy(popScreenState = true)
                }
            }
            is AddContactContract.Intent.ClearPopScreen ->{
                uiState.update {
                    it.copy(popScreenState = false)
                }
            }
        }
    }

}