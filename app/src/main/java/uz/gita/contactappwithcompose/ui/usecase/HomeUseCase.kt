package uz.gita.contactappwithcompose.ui.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithcompose.data.response.ContactDataResponse
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

interface HomeUseCase {
    fun getAllContacts(): Flow<Result<List<ContactDataResponse>>>
    fun deleteContact(contact: ContactEntity):Flow<Unit>
}