package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppinglist.bean.ShoppingItem
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.dialog.CustomAlertDialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                ScaffoldBuilder()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldBuilder() {

    var showDialog by remember {
        mutableStateOf(false)
    }

    var shoppingList by remember {
        mutableStateOf(listOf<ShoppingItem>())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Shopping LIst")
                }
            )
        },
        /*bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },*/
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        ShoppingListApp(
            innerPadding = innerPadding,
            listOfShoppingItems = shoppingList
        )
    }

    if (showDialog) {
        CustomAlertDialog(
            dismissCustomAlert = { showDialog = false },
            dialogTitle = "Add shopping item",
            dialogItemNameValue = "Item name",
            dialogItemQuantityValue = "Quantity",
            shoppingList = shoppingList,
            setShoppingList = { shoppingList = it }
        )
    }
}


@Preview
@Composable
fun ScaffoldExamplePreview() {
    ShoppingListTheme {
        ScaffoldBuilder()
    }
}