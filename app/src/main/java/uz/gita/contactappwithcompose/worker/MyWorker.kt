package uz.gita.contactappwithcompose.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import uz.gita.contactappwithcompose.domain.repository.AppRepository
import uz.gita.contactappwithcompose.utils.myLog
import javax.inject.Inject

@HiltWorker
class MyWorker @AssistedInject constructor(
   @Assisted context: Context,@Assisted workerParams:WorkerParameters
):CoroutineWorker(context, workerParams)  {

    @Inject
    lateinit var repository:AppRepository

    override suspend fun doWork(): Result {
//        myLog("doWork")
//        myLog("${repository.getAllContacts()}")
        repository.getAllContacts().onEach {contact->
            myLog("$contact")
            if (contact.isSavedToServer == 0) {
                val result = repository.addContact(contact.toRequest())
                result.onSuccess {
//                    myLog("Success")
                    repository.updateIsSavedSet(contact.id)
                }
                result.onFailure {
//                    myLog("Failure")
                    return Result.failure()
                }
            }
        }

        return Result.success()

    }
}