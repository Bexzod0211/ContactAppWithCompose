package uz.gita.contactappwithcompose.domain.repository

import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

interface AppRepository {

    fun addContact(contact:ContactEntity)

    fun getAllContacts():List<ContactData>

}