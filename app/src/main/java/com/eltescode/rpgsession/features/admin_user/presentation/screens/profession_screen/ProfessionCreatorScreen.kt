package com.eltescode.rpgsession.features.admin_user.presentation.screens.profession_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eltescode.rpgsession.core.composable.CustomText
import com.eltescode.rpgsession.features.profession.domain.model.MainProfileDomainEnum
import com.eltescode.rpgsession.features.profession.domain.model.SecondaryProfileDomainEnum

@Preview(showSystemUi = true)
@Composable
fun ProfessionCreatorScreen(
    viewModel: ProfessionCreatorScreenViewModel = hiltViewModel()
) {
    val borderColor = Color.Black
    val textColor = Color.White
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = "Akolita",
            onValueChange = {},
            label = { Text(text = "Enter Career Name") }, modifier = Modifier.padding(8.dp))
        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .border(BorderStroke(2.dp, borderColor)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cechy główne",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(borderColor)
                        .padding(4.dp),
                    color = textColor,
                    textAlign = TextAlign.Center
                )
                MainProfileDomainEnum.values().forEach { data ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    ) {
                        Text(
                            text = data.profileName,
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.Center
                        )
                        TextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier.width(55.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                modifier = Modifier
                    .weight(1F)
                    .border(BorderStroke(2.dp, borderColor)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cechy Drugorzędne",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(borderColor)
                        .padding(4.dp),
                    color = textColor,
                    textAlign = TextAlign.Center
                )
                SecondaryProfileDomainEnum.values().forEach { data ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    ) {
                        Text(
                            text = data.profileName,
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.Center
                        )
                        TextField(
                            value = "00",
                            onValueChange = {},
                            modifier = Modifier.width(55.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
        }
        CareerRecords.values().forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            ) {
                CustomText(text = "${it.careerName}:")
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    }
}

enum class CareerRecords(var careerName: String) {
    //    CECHY_GLOWNE(careerName = "umiejętności"),
//    CECHY_DRUGORZEDNE(careerName = "umiejętności"),
    UMIEJETNOSCI(careerName = "umiejętności"),
    ZDOLNOSCI(careerName = "zdolności"),
    WYPOSAZENIE(careerName = "wyposażenie"),
    PROFESJE_WSTEPNE(careerName = "profesje wstępne"),
    PROFESJE_WYJSCIOWE(careerName = "profesje wyjściowe"),
}

data class CareerRecordsdata(var careerName: String) {

}

