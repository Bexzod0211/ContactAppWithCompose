package uz.gita.contactappwithcompose.data.response

import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

data class ContactDataResponse(
    val id:Int = 0,
    val firstName:String,
    val lastName:String,
    val phone:String
){
    fun toEntity() = ContactEntity(id,firstName, lastName, phone)
    fun toData() = ContactData(id,firstName, lastName, phone)
}
