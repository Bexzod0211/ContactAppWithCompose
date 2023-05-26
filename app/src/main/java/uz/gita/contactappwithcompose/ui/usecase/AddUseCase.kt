package uz.gita.contactappwithcompose.ui.usecase

import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

interface AddUseCase {

    fun addContact(contact:ContactEntity)
}