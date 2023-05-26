package uz.gita.contactappwithcompose.ui.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

interface HomeUseCase {
    fun getAllContacts(): Flow<List<ContactData>>
    fun deleteContact(contact: ContactEntity):Flow<Unit>
}