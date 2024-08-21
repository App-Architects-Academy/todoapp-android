package academy.apparchitects.todoapp.ui.home

import academy.apparchitects.todoapp.R
import academy.apparchitects.todoapp.data.model.SerializableTodo
import academy.apparchitects.todoapp.data.model.Todo
import academy.apparchitects.todoapp.presentation.ui.atoms.CustomIcon
import academy.apparchitects.todoapp.presentation.ui.atoms.Label
import academy.apparchitects.todoapp.presentation.ui.molecules.EmptyScreen
import academy.apparchitects.todoapp.ui.Destinations
import academy.apparchitects.todoapp.ui.theme.AppTheme
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


/**
 * Written by Vikram Ragu on 21/08/24.
 */

sealed class BottomNavItems(
    @DrawableRes val icon: Int,
    val text: String,
) {

    data object TodoHome : BottomNavItems(
        icon = R.drawable.ic_task_list,
        text = "All",
    )

    data object TodoCompleted : BottomNavItems(
        icon = R.drawable.ic_tick,
        text = "Completed",
    )
}

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoHomeScreen(
    itemList: List<SerializableTodo> = emptyList(),
    onFabClicked: () -> Unit = {},
    onItemClicked: (SerializableTodo) -> Unit = {},
    onCompletedClicked: () -> Unit = {}
) {

    val bottomNavList = remember {
        listOf(
            BottomNavItems.TodoHome,
            BottomNavItems.TodoCompleted
        )
    }

    val navSelectedItem = remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .clipToBounds()
                    .background(color = AppTheme.colors.primary)
                    .padding(16.dp),
                content = {
                    Label(
                        modifier = Modifier,
                        text = "TODO APP",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24,
                        color = AppTheme.colors.white
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomIcon(
                        icon = R.drawable.ic_calendar,
                        size = 32,
                        color = AppTheme.colors.white
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = AppTheme.colors.primary,
                onClick = {
                    onFabClicked.invoke()
                }, content = {
                    Icon(
                        tint = AppTheme.colors.white,
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = AppTheme.colors.grey,
                content = {
                    bottomNavList.forEachIndexed { index, item ->

                        NavigationBarItem(
                            selected = navSelectedItem == item,
                            onClick = {
                                if (index == 1) {
                                    onCompletedClicked.invoke()
                                }
                            },
                            icon = {
                                CustomIcon(
                                    icon = item.icon,
                                    size = 30,
                                    color = if (index == navSelectedItem.intValue) {
                                        AppTheme.colors.primary
                                    } else {
                                        AppTheme.colors.secondary
                                    }
                                )
                            },
                            label = {
                                Label(
                                    text = item.text,
                                    color = if (index == navSelectedItem.intValue) {
                                        AppTheme.colors.primary
                                    } else {
                                        AppTheme.colors.secondary
                                    },
                                    fontSize = 14
                                )
                            }
                        )
                    }
                }
            )
        },
        content = {
            TodoItemCardList(
                modifier = Modifier.padding(it),
                itemList = emptyList<SerializableTodo>(),
            )
        }
    )
}

@Composable
private fun TodoItemCardList(
    modifier: Modifier = Modifier,
    itemList: List<SerializableTodo>
) {

    val newModifier = modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.primaryLight)

    if (itemList.isEmpty()) {

        Box(
            modifier = newModifier,
            content = {
                EmptyScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    iconTint = AppTheme.colors.white,
                    labelText = "No Items found",
                    textColor = AppTheme.colors.white
                )
            }
        )

    } else {
        LazyColumn(
            modifier = newModifier,
            content = {

            }
        )
    }
}



