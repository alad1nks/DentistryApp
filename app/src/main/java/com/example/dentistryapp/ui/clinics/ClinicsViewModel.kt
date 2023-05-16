package com.example.dentistryapp.ui.clinics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistryapp.domain.clinics.usecases.ClinicsUiFactory
import com.example.dentistryapp.domain.clinics.usecases.GetSearchClinicsUseCase
import com.example.dentistryapp.ktx.runCatchingNonCancellation
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
import javax.inject.Inject

@HiltViewModel
class ClinicsViewModel @Inject constructor(
    private val searchUseCase: GetSearchClinicsUseCase
) : ViewModel() {

    private val _searchState: MutableLiveData<ClinicsScreenState> =
        MutableLiveData(ClinicsScreenState.Init)
    val searchState: LiveData<ClinicsScreenState> get() = _searchState
    val searchQueryState: MutableSharedFlow<String> = MutableSharedFlow()

    init {
        subscribeToSearchQueryChanges()
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

    private suspend fun search(query: String): ClinicsScreenState {
        _searchState.postValue(ClinicsScreenState.Loading)
        val result = runCatchingNonCancellation {
            ClinicsUiFactory.create(
                searchResult = searchUseCase.search(query),
            )
        }.getOrNull()

        return result?.let { ClinicsScreenState.Data(it) }
            ?: ClinicsScreenState.Error
    }

}