package com.example.penziapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.penziapp.network.MessageObj

@Composable
fun AllMessagesList(listData: List<MessageObj>, modifier: Modifier = Modifier) {

    if (listData.isEmpty()) {
        Text("No messages found")
    } else {
        LazyColumn {
            item {
                Row(modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
                    TableCell("id")
                    TableCell("User id")
                    TableCell("Phone")
                    TableCell("Message")
                    TableCell("Type")
                    TableCell("Date")
                }
            }
            listData.forEach { message ->
                item(listData.size) {
                    Row() {
                        Text(
                            text = "$message.id"
                        )
                        Text(
                            text = "${message.user_id}"
                        )
                        Text(
                            text = message.phone
                        )
                        Text(
                            text = message.message
                        )
                        Text(
                            text = message.msg_type.toString()
                        )
                        Text(
                            text = message.created_at
                        )
                    }
                }
            }
        }
    }
}
