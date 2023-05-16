package com.example.dentistryapp.ui.doctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistryapp.domain.doctors.usecases.CreateReviewUseCase
import com.example.dentistryapp.domain.doctors.usecases.DoctorsUiFactory
import com.example.dentistryapp.domain.doctors.usecases.GetDoctorsUseCaseImpl
import com.example.dentistryapp.domain.doctors.usecases.GetReviewsUseCase
import com.example.dentistryapp.domain.doctors.usecases.ReviewsUiFactory
import com.example.dentistryapp.ktx.runCatchingNonCancellation
import com.example.dentistryapp.ui.model.ReviewUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel @Inject constructor(
    private val getSearchDoctorsUseCase: GetDoctorsUseCaseImpl,
    private val getReviewsUseCase: GetReviewsUseCase,
    private val createReviewUseCase: CreateReviewUseCase
) : ViewModel() {

    private val _searchState: MutableLiveData<DoctorsScreenState> =
        MutableLiveData(DoctorsScreenState.Init)
    val searchState: LiveData<DoctorsScreenState> get() = _searchState
    val searchQueryState: MutableSharedFlow<String> = MutableSharedFlow()

    private val _selectedDoctor: MutableLiveData<Int> = MutableLiveData()
    val selectedDoctor: LiveData<Int> get() = _selectedDoctor

    private val _reviews: MutableLiveData<List<ReviewUi>> = MutableLiveData()
    val reviews: LiveData<List<ReviewUi>> get() = _reviews

    init {
        subscribeToSearchQueryChanges()
    }

    fun getReviews() {
        viewModelScope.launch(Dispatchers.IO) {
            _reviews.postValue(ReviewsUiFactory.create(getReviewsUseCase.getReviews(selectedDoctor.value!!)))
        }
    }

    fun selectDoctor(id: Int) {
        _selectedDoctor.postValue(id)
    }

    fun createReview(score: Int, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createReviewUseCase.createReview(selectedDoctor.value!!, score, description)
            _reviews.postValue(ReviewsUiFactory.create(getReviewsUseCase.getReviews(selectedDoctor.value!!)))
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun subscribeToSearchQueryChanges() {
        searchQueryState
            .distinctUntilChanged()
            .debounce(500L)
            .flatMapLatest { flow { emit(search(it)) } }
            .onEach { _searchState.postValue(it) }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private suspend fun search(query: String): DoctorsScreenState {
        _searchState.postValue(DoctorsScreenState.Loading)
        val result = runCatchingNonCancellation {
            DoctorsUiFactory.create(
                searchResult = getSearchDoctorsUseCase.search(query),
            )
        }.getOrNull()

        return result?.let { DoctorsScreenState.Data(it) }
            ?: DoctorsScreenState.Error
    }
}