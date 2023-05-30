package uz.gita.contactappwithcompose.data.model

import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity

data class ContactData(
    val id:Int = 0,
    val firstName:String,
    val lastName:String,
    val phone:String
){
    fun toEntity() = ContactEntity(id,firstName, lastName, phone)
}
