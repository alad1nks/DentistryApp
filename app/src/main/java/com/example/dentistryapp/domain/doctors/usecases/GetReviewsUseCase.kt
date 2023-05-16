package com.example.dentistryapp.domain.doctors.usecases

import com.example.dentistryapp.domain.model.ReviewDomain

interface GetReviewsUseCase {
    suspend fun getReviews(id: Int): List<ReviewDomain>
}