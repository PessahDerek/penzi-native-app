package com.example.penziapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.penziapp.network.PersonObj

@Composable
fun RowScope.TableCell(text: String, weight: Float = 1F) {

    Text(
        text = "",
        modifier = Modifier.weight(weight)
    )
}

@Composable
fun AllUsersList(listData: ArrayList<PersonObj> = ArrayList(), modifier: Modifier = Modifier) {

    val dataSize = remember { mutableIntStateOf(listData.size) }
    if (listData.isEmpty()) {
        Text(text = "No users found", modifier = modifier.padding(5.dp))
    } else {
        LazyColumn {
            item {
                Row(modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
                    TableCell("Id")
                    TableCell("Name")
                    TableCell("Gender")
                    TableCell("Phone")
                    TableCell("County")
                    TableCell("Town")
                }
            }
            listData.forEach { person ->
                item(listData.size) {
                    Row() {
                        Text(
                            text = "$person.id"
                        )
                        Text(
                            text = "$person.name"
                        )
                        Text(
                            text = person.gender.toString()
                        )
                        Text(
                            text = person.phone
                        )
                        Text(
                            text = person.county
                        )
                        Text(
                            text = person.town
                        )
                    }
                }
            }
        }
    }
}

