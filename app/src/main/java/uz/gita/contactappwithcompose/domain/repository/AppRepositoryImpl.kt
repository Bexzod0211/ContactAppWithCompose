package uz.gita.contactappwithcompose.domain.repository

import com.google.gson.Gson
import uz.gita.contactappwithcompose.data.Api
import uz.gita.contactappwithcompose.data.model.ContactData
import uz.gita.contactappwithcompose.data.request.AddContactRequest
import uz.gita.contactappwithcompose.data.request.EditContactRequest
import uz.gita.contactappwithcompose.data.response.ContactDataResponse
import uz.gita.contactappwithcompose.data.response.ErrorResponse
import uz.gita.contactappwithcompose.data.source.local.database.ContactDatabase
import uz.gita.contactappwithcompose.data.source.local.entity.ContactEntity
import uz.gita.contactappwithcompose.utils.myLog
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val database: ContactDatabase,
    private val api: Api
) : AppRepository {
    private val dao = database.getContactDao()

    override fun addContact(contact: ContactEntity) {
        myLog("addContactInRepositoryImpl")
        dao.addContact(contact)
    }

    override fun getAllContacts(): List<ContactData> {
        return dao.getAllContacts()
    }

    override fun deleteContact(contact: ContactEntity) {
        dao.deleteContact(contact)
    }

    override suspend fun addContact(request: AddContactRequest): Result<String> {
        val response = api.addContact(request)

        if (response.isSuccessful) {
            response.body()?.let {
                return Result.success("Contact has been added successfully")
            }
        } else {
            try {
                response.errorBody()?.let {
                    val error = Gson().fromJson(it.string(), ErrorResponse::class.java)
                    return Result.failure(Exception(error.message))
                }
            } catch (e: Exception) {

            }
        }

        return Result.failure(Exception("exception"))
    }

    override suspend fun deleteContact(id: Int): Result<String> {
        val response = api.deleteContact(id)

        if (response.isSuccessful) {
            response.body()?.let {
                return Result.success("Contact has been deleted successfully")
            }
        } else {
            try {
                response.errorBody()?.let {
                    val error = Gson().fromJson(it.string(), ErrorResponse::class.java)
                    return Result.failure(Exception(error.message))
                }
            }catch (e:java.lang.Exception){

            }
        }
        return Result.failure(Exception("exception"))
    }

    override suspend fun getAllContactsFromServer(): Result<List<ContactDataResponse>> {
        val response = api.getAllContacts()

        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                }
            } else {
                try {
                    response.errorBody()?.let {
                        val error = Gson().fromJson(it.string(), ErrorResponse::class.java)
                        return Result.failure(Exception(error.message))
                    }
                } catch (e: java.lang.Exception) {

                }
            }
        }catch (e:Exception){

        }

        return Result.failure(Exception("exception"))

    }

    override suspend fun updateContact(request: EditContactRequest): Result<String> {
        return Result.success("")
    }

    override fun updateIsSavedSet(id: Int) {
        dao.updateIsSavedSetSaved(id)
    }
}