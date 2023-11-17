package sdu.mobile.xpence.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CreateGroup(){
    var isDialogVisible by remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = { isDialogVisible = true},
        modifier = Modifier
            .height(100.dp)
            .width(200.dp)
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .width(200.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Create New Group")
        }
    }

    if (isDialogVisible) {
        CreateDialog(
            onDismiss = { isDialogVisible = false },
            /*onGroupCreated = { newGroup, newDis ->
                groups = groups + Group(newGroup, newDis)
                newGroupName = newGroup
                newGroupDescription = newDis
            }*/
        )
    }
}


@Composable
fun CreateDialog(
    onDismiss: () -> Unit,
    //onGroupCreated: (String, String) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            text()

            Spacer(modifier = Modifier.height(5.dp))

            var name by rememberSaveable { mutableStateOf("") }
            var description by rememberSaveable { mutableStateOf("") }

            groupNameTextField(name = name, onTextChange = { name = it })

            Spacer(modifier = Modifier.height(5.dp))

            groupDescriptionTextField(description = description, onTextChange = { description = it })

            Spacer(modifier = Modifier.height(10.dp))


            dropDown()

            Button(
                onClick = {
                    //TODO post request
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Done", modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Composable
fun text(){
    Text(
        text = "Create a new group",
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 20.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
    )
}

@Composable
fun groupNameTextField(
    name: String,
    onTextChange: (String) -> Unit
){
    TextField(
        value = name,
        onValueChange = onTextChange,
        label = { Text("Group name") },
        placeholder = { Text(text = "Enter the group name") },
        singleLine = true,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp)
    )
}

@Composable
fun groupDescriptionTextField(
    description: String,
    onTextChange: (String) -> Unit
){
    TextField(
        value = description,
        onValueChange = onTextChange,
        label = { Text("Group description") },
        placeholder = { Text(text = "Enter a description for the group") },
        singleLine = true,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp)
    )
}

@Composable
fun dropDown() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        "Alice Johnson",
        "John Doe",
        "Eva Davis",
        "Chris Miller",
        "Sarah White",
    )
    Box(
        modifier = Modifier.fillMaxWidth(0.7f),
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { expanded = true },
            shape = CircleShape
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Add Group members",
                textAlign = TextAlign.Center
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = {
                        Text(text = s)
                    },
                    onClick = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "drawable icons",
                        )
                    }
                )
            }
        }
    }
}
