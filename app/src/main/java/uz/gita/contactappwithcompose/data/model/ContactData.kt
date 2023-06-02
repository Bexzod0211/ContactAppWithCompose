package uz.gita.contactappwithcompose.data.model

import uz.gita.contactappwithcompose.data.request.AddContactRequest
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

data class ContactData(
    val id:Int = 0,
    val firstName:String,
    val lastName:String,
    val phone:String,
    val isSavedToServer:Int = 0
){
    fun toEntity() = ContactEntity(id,firstName, lastName, phone)
    fun toRequest() = AddContactRequest(firstName, lastName, phone)
}
