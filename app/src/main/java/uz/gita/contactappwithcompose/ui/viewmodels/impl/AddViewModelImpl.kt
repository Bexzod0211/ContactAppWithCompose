package uz.gita.contactappwithcompose.ui.viewmodels.impl

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
    override fun addContact(contact: ContactEntity) {
        useCase.addContact(contact)
    }

}