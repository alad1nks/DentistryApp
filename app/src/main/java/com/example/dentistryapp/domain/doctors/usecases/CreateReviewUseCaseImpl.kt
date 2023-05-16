package com.example.dentistryapp.domain.doctors.usecases

import com.example.dentistryapp.domain.doctors.repository.ReviewsRepository
import javax.inject.Inject

class CreateReviewUseCaseImpl @Inject constructor(
    private val repository: ReviewsRepository
) : CreateReviewUseCase {
    override suspend fun createReview(id: Int, score: Int, description: String) {
        repository.createReview(id, score, description)
    }

}