package com.example.shoppinglist.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import com.example.shoppinglist.bean.ShoppingItem
import com.example.shoppinglist.dialog.ShoppingItemEditorAlertDialog

@Composable
fun ShoppingListItem(
    shoppingItem: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var showEditDialog by remember {
        mutableStateOf(false)
    }
    var name = shoppingItem.name
    if (name.length > 10) {
        name = name.substring(0, 7) + "..."
    }

    var itemNameToEdit by remember { mutableStateOf(shoppingItem.name) }
    var dialogItemQuantityValue by remember {
        mutableStateOf(shoppingItem.quantity)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showEditDialog = true
            }
            .padding(8.dp)
            .border(
                border = BorderStroke(
                    1.dp,
                    color = Color(0xFF018786)
                ),
                shape = RoundedCornerShape(20)
            ),
    ){
        Text(
            text = name,
            modifier = Modifier
                .width(135.dp)
                .padding(16.dp),
        )
        Text(
            text = stringResource(id = R.string.quantity_label) + ": " + shoppingItem.quantity,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton (onClick = {
                onDeleteClick()
            }) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(20.dp),
                    imageVector = Icons.Default.Delete, contentDescription = "Delete button")
            }
        }
    }

    if(showEditDialog) {
        ShoppingItemEditorAlertDialog (
            dismissCustomAlert = { showEditDialog = false },
            editDialogTitle = stringResource(id = R.string.edit_shopping_item_label),
            itemNameToEdit = itemNameToEdit,
            onItemNameChange = { itemNameToEdit = it },
            dialogItemQuantityValue = shoppingItem.quantity.toString(),
            onDialogItemChange = {
                shoppingItem.name = it.name
                shoppingItem.quantity = it.quantity
                itemNameToEdit = it.name
                dialogItemQuantityValue = it.quantity
            },
            shoppingItem = shoppingItem
        )
    }
}

@Preview
@Composable
fun ShoppingListItemPreview() {
    ShoppingListItem(
        shoppingItem = ShoppingItem(1, "Banana", 1),
        onEditClick = {},
        onDeleteClick = {}
    )
}