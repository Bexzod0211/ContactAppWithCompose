package uz.gita.contactappwithcompose.data

import retrofit2.Response
import retrofit2.http.*
import uz.gita.contactappwithcompose.data.request.AddContactRequest
import uz.gita.contactappwithcompose.data.request.EditContactRequest
import uz.gita.contactappwithcompose.data.response.ContactDataResponse

interface Api {

    @GET("/api/v1/contact")
    suspend fun getAllContacts():Response<List<ContactDataResponse>>

    @POST("/api/v1/contact")
    suspend fun addContact(@Body request:AddContactRequest):Response<ContactDataResponse>

    @PUT("/api/v1/contact")
    suspend fun updateContact(@Body request: EditContactRequest):Response<ContactDataResponse>

    @DELETE("/api/v1/contact")
    suspend fun deleteContact(@Query("id") id:Int):Response<Unit>
}