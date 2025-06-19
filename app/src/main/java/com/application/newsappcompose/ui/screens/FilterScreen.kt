package com.application.newsappcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.application.newsappcompose.R
import com.application.newsappcompose.common.NoInternetException
import com.application.newsappcompose.data.database.entity.Source
import com.application.newsappcompose.data.model.Country
import com.application.newsappcompose.data.model.Language
import com.application.newsappcompose.ui.base.ShowError
import com.application.newsappcompose.ui.base.ShowLoading
import com.application.newsappcompose.ui.base.UIState
import com.application.newsappcompose.ui.components.CountryListLayout
import com.application.newsappcompose.ui.components.LanguageListLayout
import com.application.newsappcompose.ui.components.SourceListLayout
import com.application.newsappcompose.ui.viewmodels.filters.CountryFilterViewModel
import com.application.newsappcompose.ui.viewmodels.filters.LanguageFilterViewModel
import com.application.newsappcompose.ui.viewmodels.filters.SourceFilterViewModel


@Composable
fun CountryScreen(
    countryFilterViewModel: CountryFilterViewModel = hiltViewModel(),
    countryClicked: (Country) -> Unit
) {
    val countryUiState: UIState<List<Country>> by countryFilterViewModel.countryItem.collectAsStateWithLifecycle()

    when (countryUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((countryUiState as UIState.Failure<List<Country>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(
                text = errorText,
                retryEnabled = true
            ) {
                countryFilterViewModel.getCountries()
            }
        }

        is UIState.Success -> {
            CountryListLayout(countryList = (countryUiState as UIState.Success<List<Country>>).data) {
                countryClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }

}

@Composable
fun LanguageScreen(
    languageFilterViewModel: LanguageFilterViewModel = hiltViewModel(),
    languageClicked: (Language) -> Unit
) {
    val languageUiState: UIState<List<Language>> by languageFilterViewModel.languageItem.collectAsStateWithLifecycle()

    when (languageUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((languageUiState as UIState.Failure<List<Language>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(
                text = errorText,
                retryEnabled = true
            ) {
                languageFilterViewModel.getLanguage()
            }
        }

        is UIState.Success -> {
            LanguageListLayout(languageList = (languageUiState as UIState.Success<List<Language>>).data) {
                languageClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }

}

@Composable
fun SourceScreen(
    sourceFilterViewModel: SourceFilterViewModel = hiltViewModel(),
    sourceClicked: (Source) -> Unit
) {
    val sourceUiState: UIState<List<Source>> by sourceFilterViewModel.sourceItem.collectAsStateWithLifecycle()

    when (sourceUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((sourceUiState as UIState.Failure<List<Source>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(
                text = errorText,
                retryEnabled = true
            ) {
                sourceFilterViewModel.getSources()
            }
        }

        is UIState.Success -> {
            SourceListLayout(sourceList = (sourceUiState as UIState.Success<List<Source>>).data) {
                sourceClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }

}