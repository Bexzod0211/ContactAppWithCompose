package uz.gita.contactappwithcompose.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeletedEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val contactId:Int
)
