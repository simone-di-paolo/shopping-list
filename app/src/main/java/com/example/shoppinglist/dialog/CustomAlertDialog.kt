package com.example.shoppinglist.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import com.example.shoppinglist.bean.ShoppingItem

@Composable
fun CustomAlertDialog(
    dismissCustomAlert: () -> Unit,
    dialogTitle: String,
    dialogItemNameValue: String,
    dialogItemQuantityValue: String,
    shoppingList: List<ShoppingItem>,
    setShoppingList: (List<ShoppingItem>) -> Unit
) {

    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var isErrorTriggeredItemQuantity by remember { mutableStateOf(false) }
    var mutableShoppingList = shoppingList


    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column() {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text(dialogItemNameValue)},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
                OutlinedTextField(
                    value = itemQuantity,
                    onValueChange = {
                        itemQuantity = it
                        run {
                            isErrorTriggeredItemQuantity = false
                        }
                    },
                    label = { Text(dialogItemQuantityValue)},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    supportingText = {
                        if (isErrorTriggeredItemQuantity) {
                           Text(
                               modifier = Modifier.fillMaxWidth(),
                               text = stringResource(id = R.string.itemQuantityIsError),
                               color = MaterialTheme.colorScheme.error
                           )
                        }
                    })
            }
        },
        onDismissRequest = {
            dismissCustomAlert()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                        try {
                            val newItem = ShoppingItem(
                                id = shoppingList.size+1,
                                name = itemName,
                                quantity = itemQuantity.toInt(),
                                isEditing =  false)
                            setShoppingList(mutableShoppingList + newItem)
                            dismissCustomAlert()
                        } catch (e: NumberFormatException) {
                            isErrorTriggeredItemQuantity = true
                        }
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dismissCustomAlert()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Preview
@Composable
fun CustomAlertDialogPreview() {
    CustomAlertDialog(
        shoppingList = listOf<ShoppingItem>(),
        dismissCustomAlert = {},
        dialogTitle = "Add shopping item",
        dialogItemNameValue = "Item name",
        dialogItemQuantityValue = "Quantity",
        setShoppingList = {}
    )
}