package com.example.dentistryapp.domain.doctors.usecases

import com.example.dentistryapp.domain.model.ReviewDomain
import com.example.dentistryapp.ui.model.ReviewUi

object ReviewsUiFactory {
    fun create(reviews: List<ReviewDomain>): List<ReviewUi> {
        return reviews.map {
            ReviewUi(
                id = it.id,
                score = "Оценка:${it.score}",
                description = it.description
            )
        }
    }
}