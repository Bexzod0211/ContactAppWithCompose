package uz.gita.contactappwithcompose.data.source.local.dao

import androidx.room.*
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

@Dao
interface ContactDao {

    @Insert
    fun addContact(contact:ContactEntity)

    @Query("SELECT * FROM contacts")
    fun getAllContacts():List<ContactData>

}