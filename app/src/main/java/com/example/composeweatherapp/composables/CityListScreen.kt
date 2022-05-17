package com.example.composeweatherapp.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeweatherapp.service.City
import com.example.composeweatherapp.WeatherViewModel

@Composable
fun CityListScreen(
    navController: NavController?,
    weatherViewModel: WeatherViewModel = viewModel()
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (weatherViewModel.errorMessage == "") {
            CityList(
                weatherViewModel.cityList,
                onCityClick = {
                    weatherViewModel.getCityWeather(it)
                    navController?.navigate("details")
                },
                modifier = Modifier.padding(8.dp)
            )
        } else {
            Text(text = weatherViewModel.errorMessage)
        }
    }
}

@Composable
fun CityList(
    list: List<City>,
    onCityClick: (Int) -> Unit,
    modifier: Modifier = Modifier
    ) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(list) { city ->
            CityListItem(item = city, onClick = onCityClick)
        }
    }
}

@Composable
fun CityListItem(item: City, onClick : (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onClick(item.id) }
            .height(80.dp)
        ,
        elevation = 10.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                Modifier
                    .padding(8.dp),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = item.temp.toString(),
                Modifier.padding(8.dp),
                fontSize = 16.sp
            )
        }
    }
}
