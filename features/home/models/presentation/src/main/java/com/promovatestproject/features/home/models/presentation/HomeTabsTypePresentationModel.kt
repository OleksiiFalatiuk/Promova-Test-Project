package com.promovatestproject.features.home.models.presentation

import androidx.annotation.StringRes
import com.example.core.localization.R as LocalizableResources

enum class HomeTabsTypePresentationModel(@StringRes val title: Int) {
    FILMS(LocalizableResources.string.home_films_tab),
    FAVOURITES(LocalizableResources.string.home_favourites_tab)
}