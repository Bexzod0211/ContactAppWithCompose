package uz.gita.contactappwithcompose.ui.directions

import uz.gita.contactappwithcompose.navigation.AppNavigator
import uz.gita.contactappwithcompose.ui.viewmodels.AddContactContract
import javax.inject.Inject

class AddContactDirection @Inject constructor(
    private val appNavigator: AppNavigator
) :AddContactContract.Direction{
    override suspend fun navigateToHomeScreen() {
        appNavigator.back()
    }

}