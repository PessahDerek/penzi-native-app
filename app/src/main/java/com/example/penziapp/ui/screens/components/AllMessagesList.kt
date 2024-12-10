package com.example.penziapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.penziapp.network.MessageObj
import com.example.penziapp.ui.theme.PenziAppTheme

@Composable
//fun AllMessagesList(listData: List<MessageObj>, modifier: Modifier = Modifier) {
fun AllMessagesList(listData: Collection<MessageObj>, modifier: Modifier = Modifier) {

    if (listData.isEmpty()) {
        Text("No messages found")
    } else {
        LazyColumn(
            modifier
                .fillMaxSize()
                .background(color = androidx.compose.ui.graphics.Color.White)
        ) {
            item {
                Row(
                    modifier
                        .fillMaxWidth(1f)
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.tertiary)
                ) {
                    TableCell("id", .25f)
                    TableCell("User id", .25f)
                    TableCell("Phone", 1f)
                    TableCell("Message", 1f)
//                    TableCell("Type", .3f)
//                    TableCell("Date", 1f)
                }
            }
            listData.forEach { message ->
                items(listData.size) {
                    Row() {
                        Text(
                            text = "${message.id.toString()}",
                            Modifier.weight(.25f)
                        )
                        Text(
                            text = "${message.user_id}",
                            Modifier.weight(.25f)
                        )
                        Text(
                            text = message.phone,
                            Modifier.weight(1f)
                        )
                        Text(
                            text = message.message,
                            Modifier.weight(1f)
                        )
//                        Text(
//                            text = message.msg_type,
//                            Modifier.weight(.1f)
//                        )
//                        Text(
//                            text = message.created_at,
//                            Modifier.weight(.1f)
//                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Overview() {
    PenziAppTheme {
        AllMessagesList(
            List<MessageObj>(
                5,
                init = {
                    MessageObj(
                        id = 1,
                        message = "Hello",
                        phone = "0741120439",
                        user_id = 3,
                        msg_type = "Outgoing",
                        created_at = ""
                    )
                })
        )
    }
}
