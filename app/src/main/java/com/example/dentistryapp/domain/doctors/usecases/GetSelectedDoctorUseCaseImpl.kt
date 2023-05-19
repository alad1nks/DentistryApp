package com.example.dentistryapp.domain.doctors.usecases

import com.example.dentistryapp.data.model.GetSelectedDoctorInfoResponse
import com.example.dentistryapp.domain.doctors.repository.DoctorsRepository
import com.example.dentistryapp.ui.model.SelectedDoctorUi
import javax.inject.Inject

class GetSelectedDoctorUseCaseImpl @Inject constructor(
    private val repository: DoctorsRepository
) : GetSelectedDoctorUseCase {
    override suspend fun getSelectedDoctor(id: Int): SelectedDoctorUi {
        return repository.getSelectedDoctor(id).info.toUi()
    }

    private fun GetSelectedDoctorInfoResponse.toUi(): SelectedDoctorUi {
        val year = when (this.exp % 10) {
            1 -> "год"
            in 2..4 -> "года"
            else -> "лет"
        }
        return SelectedDoctorUi(
            name = this.name,
            post = this.post,
            experience = "Опыт: ${this.exp} $year",
            description = "Лучший специалист! Работает у нас очень долго, мы советуем записаться к нему.",
            photo = this.photo
        )
    }

}