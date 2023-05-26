package uz.gita.contactappwithcompose.ui.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.ui.usecase.HomeUseCase
import uz.gita.contactappwithcompose.ui.viewmodels.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val useCase:HomeUseCase
): HomeViewModel, ViewModel() {
    override val contactsLiveData: MutableLiveData<List<ContactData>> = MutableLiveData()

    init {
        useCase.getAllContacts().onEach {
            contactsLiveData.value = it
        }
    }

}