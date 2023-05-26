package uz.gita.contactappwithcompose.ui.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithcompose.data.model.ContactData

interface HomeUseCase {
    fun getAllContacts(): Flow<List<ContactData>>
}