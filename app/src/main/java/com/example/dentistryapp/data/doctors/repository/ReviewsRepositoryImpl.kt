package com.example.dentistryapp.data.doctors.repository

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.data.api.model.CreateReviewBody
import com.example.dentistryapp.data.api.model.GetReviewsBody
import com.example.dentistryapp.data.api.model.GetReviewsResponse
import com.example.dentistryapp.domain.doctors.repository.ReviewsRepository
import com.example.dentistryapp.domain.model.ReviewDomain
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val api: DentistryApi
) : ReviewsRepository {
    override suspend fun getReviews(id: Int): List<ReviewDomain> {
        return api.getReviews(GetReviewsBody(id)).toDomain()
    }

    override suspend fun createReview(id: Int, score: Int, description: String) {
        api.createReview(
            CreateReviewBody(
                id,
                score,
                description
            )
        )
    }

    private fun GetReviewsResponse.toDomain(): List<ReviewDomain> {
        return this.reviewList.map {
            ReviewDomain(
                id = it.id,
                cid = it.cid,
                did = it.did,
                sid = it.sid,
                score = it.score,
                description = it.description
            )
        }
    }

}