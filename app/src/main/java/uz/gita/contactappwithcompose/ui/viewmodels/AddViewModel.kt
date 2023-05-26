package uz.gita.contactappwithcompose.ui.viewmodels

import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

interface AddViewModel {
    fun addContact(contact:ContactEntity)
}