package uz.gita.contactappwithcompose.domain.repository

import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.database.ContactDatabase
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val database:ContactDatabase
) : AppRepository {
    private val dao = database.getContactDao()

    override fun addContact(contact: ContactEntity) {
        dao.addContact(contact)
    }

    override fun getAllContacts(): List<ContactData> {
        return dao.getAllContacts()
    }
}