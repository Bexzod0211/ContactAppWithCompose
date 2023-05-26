package uz.gita.contactappwithcompose.ui.usecase.impl

import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import uz.gita.contactappwithcompose.domain.repository.AppRepository
import uz.gita.contactappwithcompose.ui.usecase.AddUseCase
import javax.inject.Inject

class AddUseCaseImpl @Inject constructor(
    private val repository: AppRepository
): AddUseCase {
    override fun addContact(contact: ContactEntity) {
        repository.addContact(contact = contact)
    }

}