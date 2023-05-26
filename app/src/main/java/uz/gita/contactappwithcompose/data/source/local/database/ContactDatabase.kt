package uz.gita.contactappwithcompose.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.contactappwithcompose.data.source.local.dao.ContactDao
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactDatabase : RoomDatabase(){
    abstract fun getContactDao():ContactDao
}