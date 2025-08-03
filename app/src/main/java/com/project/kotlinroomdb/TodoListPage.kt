package com.project.kotlinroomdb

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.kotlinroomdb.DB.TodoEntity
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable fun TodoListPage(viewModel: TodoViewModel){

    val todoList by viewModel.todoList.observeAsState()
    var inputtext by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OutlinedTextField(
                modifier = Modifier.weight(1F),
                value = inputtext,
                onValueChange = {
                    inputtext = it
                }
            )
            Button(
                onClick = {
                    viewModel.addTodo(inputtext)
                    inputtext =""
                }
            ) {
                Text(text = "Add")
            }
        }

        todoList?.let {
            LazyColumn (
                content = {
                    itemsIndexed(it){index: Int, item: TodoEntity ->
                        TodoItem(item = item, onDelete = {
                            viewModel.deleteTodo(item.id)
                        })
                    }
                }
            )
        }?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No Items Yet",
            fontSize = 16.sp
        )
    }
}



@Composable
fun TodoItem(item: TodoEntity, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1F)
        ) {
            // Corrected DateTimeFormatter pattern
            val formatter = DateTimeFormatter.ofPattern("hh:mm a, dd/M")
                .withZone(ZoneId.systemDefault()) // Or a specific ZoneId if needed

            // Convert Instant to ZonedDateTime for formatting
            val formattedDate = item.createdAt.atZone(ZoneId.systemDefault()).format(formatter)

            Text(
                text = formattedDate, // Use the formatted date string
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = item.title,
                fontSize = 12.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "delete",
                tint = Color.White
            )
        }
    }
}

