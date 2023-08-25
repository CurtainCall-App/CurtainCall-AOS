package com.cmc.curtaincall.design

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmc.curtaincall.design.ui.screen.AppBarScreen
import com.cmc.curtaincall.design.ui.screen.BottomSheetScreen
import com.cmc.curtaincall.design.ui.screen.ButtonScreen
import com.cmc.curtaincall.design.ui.screen.CalendarScreen
import com.cmc.curtaincall.design.ui.screen.CardScreen
import com.cmc.curtaincall.design.ui.screen.ColorScreen
import com.cmc.curtaincall.design.ui.screen.DialogScreen
import com.cmc.curtaincall.design.ui.screen.HomeScreen
import com.cmc.curtaincall.design.ui.screen.IconScreen
import com.cmc.curtaincall.design.ui.screen.TextFieldScreen
import com.cmc.curtaincall.design.ui.screen.TimePickerScreen
import com.cmc.curtaincall.design.ui.screen.TypoGraphyScreen

private const val ROOT_GRAPH = "root_graph"
private const val HOME = "home"
private const val COLOR = "color"
private const val ICONS = "icons"
private const val TYPOGRAPHY = "typography"
private const val ALERT = "alert"
private const val BUTTON = "button"
private const val TEXT = "text"
private const val TEXT_FIELD = "text_field"
private const val APP_BAR = "app_bar"
private const val CARD = "card"
private const val BOTTOM_SHEET = "bottom_sheet"
private const val CALENDAR = "calendar"
private const val RATING_BAR = "rating_bar"
private const val TIME_PICKER = "time_picker"
private const val DIALOG = "dialog"

interface DesignSystemDestination {
    val route: String
}

object Home : DesignSystemDestination {
    override val route = HOME
}

object Color : DesignSystemDestination {
    override val route = COLOR
}

object Icons : DesignSystemDestination {
    override val route = ICONS
}

object Typography : DesignSystemDestination {
    override val route = TYPOGRAPHY
}

object Button : DesignSystemDestination {
    override val route = BUTTON
}

object TextField : DesignSystemDestination {
    override val route = TEXT_FIELD
}

object Appbar : DesignSystemDestination {
    override val route = APP_BAR
}

object Card : DesignSystemDestination {
    override val route = CARD
}

object BottomSheet : DesignSystemDestination {
    override val route = BOTTOM_SHEET
}

object Calendar : DesignSystemDestination {
    override val route = CALENDAR
}

object TimePicker : DesignSystemDestination {
    override val route = TIME_PICKER
}

object Dialog : DesignSystemDestination {
    override val route = DIALOG
}

@Composable
internal fun AppNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Home.route,
        modifier = Modifier.fillMaxSize(),
        route = ROOT_GRAPH
    ) {
        composable(route = Home.route) {
            HomeScreen(
                onNavigateColor = { navHostController.navigate(Color.route) },
                onNavigateIcons = { navHostController.navigate(Icons.route) },
                onNavigateTypography = { navHostController.navigate(Typography.route) },
                onNavigateButton = { navHostController.navigate(Button.route) },
                onNavigateTextField = { navHostController.navigate(TextField.route) },
                onNavigateAppbar = { navHostController.navigate(Appbar.route) },
                onNavigateCard = { navHostController.navigate(Card.route) },
                onNavigateBottomSheet = { navHostController.navigate(BottomSheet.route) },
                onNavigateCalendar = { navHostController.navigate(Calendar.route) },
                onNavigateTimePicker = { navHostController.navigate(TimePicker.route) },
                onNavigateDialog = { navHostController.navigate(Dialog.route) }
            )
        }

        composable(route = Color.route) {
            ColorScreen { navHostController.popBackStack() }
        }

        composable(route = Icons.route) {
            IconScreen { navHostController.popBackStack() }
        }

        composable(route = Typography.route) {
            TypoGraphyScreen { navHostController.popBackStack() }
        }

        composable(route = Button.route) {
            ButtonScreen { navHostController.popBackStack() }
        }

        composable(route = TextField.route) {
            TextFieldScreen { navHostController.popBackStack() }
        }

        composable(route = Appbar.route) {
            AppBarScreen { navHostController.popBackStack() }
        }

        composable(route = Card.route) {
            CardScreen { navHostController.popBackStack() }
        }

        composable(route = Appbar.route) {
            AppBarScreen { navHostController.popBackStack() }
        }

        composable(route = BottomSheet.route) {
            BottomSheetScreen { navHostController.popBackStack() }
        }

        composable(route = Calendar.route) {
            CalendarScreen { navHostController.popBackStack() }
        }

        composable(route = TimePicker.route) {
            TimePickerScreen { navHostController.popBackStack() }
        }

        composable(route = Dialog.route) {
            DialogScreen { navHostController.popBackStack() }
        }
    }
}
