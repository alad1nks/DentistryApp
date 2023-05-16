package com.example.dentistryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dentistryapp.navigation.DentistryNavigationItem
import com.example.dentistryapp.ui.model.HomeItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _items: MutableLiveData<List<HomeItemUi>> = MutableLiveData(
        listOf(
            HomeItemUi("Записаться на приём", true) { navController ->
                navController.navigate(DentistryNavigationItem.ServicesScreen.screenRoute)
            },
            HomeItemUi("Рекомендовать друзьям", true) {

            },
            HomeItemUi("Отзывы", false) {

            }
        )
    )
    val items: LiveData<List<HomeItemUi>> = _items
}