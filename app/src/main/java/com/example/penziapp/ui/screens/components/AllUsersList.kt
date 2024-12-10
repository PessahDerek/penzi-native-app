package com.example.penziapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.penziapp.network.PersonObj
import com.example.penziapp.ui.theme.PenziAppTheme

@Composable
fun RowScope.TableCell(text: String, weight: Float = 1F) {

    Text(
        text = text,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight)
            .height(50.dp),
//        fontSize = 12.sp,
        lineHeight = 50.sp,
    )
}

@Composable
//fun AllUsersList(listData: ArrayList<PersonObj> = ArrayList(), modifier: Modifier = Modifier) {
fun AllUsersList(listData: Collection<PersonObj> = listOf(), modifier: Modifier = Modifier) {

//    val dataSize = remember { mutableIntStateOf(listData.size) }
    if (listData.isEmpty()) {
        Text(text = "No users found", modifier = modifier.padding(5.dp))
    } else {
        LazyColumn(
            modifier.fillMaxSize().background(Color.White)
        ) {
            item {
                Row(modifier.background(MaterialTheme.colorScheme.tertiary)) {
                    TableCell("Id", 0.25f)
                    TableCell("Name", 1f)
                    TableCell("Gender", .5f)
                    TableCell("Phone", 1f)
                    TableCell("County", .5f)
                    TableCell("Town", .5f)
                }
            }
            listData.forEach { person ->
                items(listData.size) {
                    Row() {
                        Text(
                            text = "${person.id.toString()}",
                            modifier = modifier
                                .weight(.25f)
                        )
                        Text(
                            text = "${person.name.toString()}",
                            modifier = modifier
                                .weight(1f)
                        )
                        Text(
                            text = person.gender,
                            modifier = modifier
                                .weight(.5f)
                        )
                        Text(
                            text = person.phone,
                            modifier = modifier
                                .weight(1f)
                        )
                        Text(
                            text = person.county,
                            modifier = modifier
                                .weight(.5f)
                        )
                        Text(
                            text = person.town,
                            modifier = modifier
                                .weight(.5f)
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun CheckAllUsers() {
    PenziAppTheme {
        AllUsersList(
            List(5,
                init = {
                    PersonObj(
                        id=1,
                        name = "Jenny",
                        phone = "0741120439",
                        town = "Kitale",
                        county = "Uasin Gishu",
                        gender = "Male",
                        age = 22
                    )
                })
        )
    }
}
