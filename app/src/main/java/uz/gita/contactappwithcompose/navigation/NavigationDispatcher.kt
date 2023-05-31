package uz.gita.contactappwithcompose.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.contactappwithcompose.navigation.AppNavigator
import uz.gita.contactappwithcompose.navigation.AppScreen
import uz.gita.contactappwithcompose.navigation.NavigationArg
import uz.gita.contactappwithcompose.navigation.NavigationHandler
import uz.gita.contactappwithcompose.utils.myLog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor():  AppNavigator, NavigationHandler {
    override val navBuffer: MutableSharedFlow<NavigationArg> = MutableSharedFlow()

    private suspend fun navigate(arg: NavigationArg){
        navBuffer.emit(arg)
    }


    override suspend fun navigateTo(screen: AppScreen) = navigate {
        push(screen)
    }


    override suspend fun back() = navigate{
        pop()
    }
}