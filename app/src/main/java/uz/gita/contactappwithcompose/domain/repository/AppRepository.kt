package uz.gita.contactappwithcompose.domain.repository

import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.request.AddContactRequest
import uz.gita.contactappwithcompose.data.request.EditContactRequest
import uz.gita.contactappwithcompose.data.response.ContactDataResponse
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

interface AppRepository {

    fun addContact(contact:ContactEntity)

    fun getAllContacts():List<ContactData>

    fun deleteContact(contact: ContactEntity)

    fun updateIsSavedSet(id: Int)
    suspend fun addContact(request:AddContactRequest):Result<String>
    suspend fun getAllContactsFromServer():Result<List<ContactDataResponse>>
    suspend fun updateContact(request:EditContactRequest):Result<String>
    suspend fun deleteContact(id:Int):Result<String>

}