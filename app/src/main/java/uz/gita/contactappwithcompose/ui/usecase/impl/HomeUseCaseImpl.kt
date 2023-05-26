package uz.gita.contactappwithcompose.ui.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.ui.usecase.HomeUseCase

class HomeUseCaseImpl() : HomeUseCase {
    override fun getAllContacts(): Flow<List<ContactData>>  = flow{

    }
}