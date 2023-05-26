package uz.gita.contactappwithcompose.ui.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import uz.gita.contactappwithcompose.ui.usecase.AddUseCase
import uz.gita.contactappwithcompose.ui.viewmodels.AddViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModelImpl @Inject constructor(
    private val useCase: AddUseCase
) : AddViewModel, ViewModel() {
    override val messageLiveData: MutableLiveData<String> = MutableLiveData()
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
    }

}