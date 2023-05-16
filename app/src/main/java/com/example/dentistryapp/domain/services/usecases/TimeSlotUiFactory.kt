package com.example.dentistryapp.domain.services.usecases

import com.example.dentistryapp.domain.model.TimeSlotDomain
import com.example.dentistryapp.ui.model.TimeSlotItemUi

object TimeSlotUiFactory {
    fun create(timeSlots: List<TimeSlotDomain>): List<TimeSlotItemUi> {
        return timeSlots.map {
            TimeSlotItemUi("Начало: ${it.timeBegin.substring(11, 16)}", "Конец: ${it.timeEnd.substring(11, 16)}", it.timeBegin)
        }
    }
}