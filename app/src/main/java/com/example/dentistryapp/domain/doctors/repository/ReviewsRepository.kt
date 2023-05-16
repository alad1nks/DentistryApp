package com.example.dentistryapp.domain.doctors.repository

import com.example.dentistryapp.domain.model.ReviewDomain

interface ReviewsRepository {
    suspend fun getReviews(id: Int): List<ReviewDomain>
    suspend fun createReview(id: Int, score: Int, description: String)
}