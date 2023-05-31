package uz.gita.contactappwithcompose.navigation

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithcompose.navigation.NavigationArg

interface NavigationHandler {
    val navBuffer:Flow<NavigationArg>
}