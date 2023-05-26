package uz.gita.contactappwithcompose.ui.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import uz.gita.contactappwithcompose.domain.repository.AppRepository
import uz.gita.contactappwithcompose.ui.usecase.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : HomeUseCase {
    override fun getAllContacts(): Flow<List<ContactData>>  = flow{
        emit(repository.getAllContacts())
    }

    override fun deleteContact(contact: ContactEntity): Flow<Unit>  = flow{
        repository.deleteContact(contact)
        emit(Unit)
    }
}