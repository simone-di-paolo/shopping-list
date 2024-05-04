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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import com.example.shoppinglist.bean.ShoppingItem

@Composable
fun ShoppingListItem(
    shoppingItem: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var name = shoppingItem.name
    if (name.length > 10) {
        name = name.substring(0, 7) + "..."
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onEditClick()
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
            IconButton (onClick = onEditClick) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(20.dp),
                    imageVector = Icons.Default.Delete, contentDescription = "Delete button")
            }
        }
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