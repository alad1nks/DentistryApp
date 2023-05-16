package com.example.dentistryapp.domain.doctors.usecases

interface CreateReviewUseCase {
    suspend fun createReview(id: Int, score: Int, description: String)
}