package com.example.dentistryapp.domain.doctors.usecases

import android.util.Log
import com.example.dentistryapp.domain.doctors.repository.ReviewsRepository
import com.example.dentistryapp.domain.model.ReviewDomain
import javax.inject.Inject

class GetReviewsUseCaseImpl @Inject constructor(
    private val repository: ReviewsRepository
) : GetReviewsUseCase {
    override suspend fun getReviews(id: Int): List<ReviewDomain> {
        Log.d("getReviews", id.toString())
        return repository.getReviews(id)
    }

}