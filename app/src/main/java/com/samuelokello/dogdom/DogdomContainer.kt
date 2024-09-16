package com.samuelokello.dogdom

import android.content.Context
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.ui.article.ArticleViewModelFactory
import com.samuelokello.dogdom.ui.home.HomeViewModelFactory

class DogdomContainer(context: Context) {
    private val dataSource: DataSource    = DataSource

    val homeViewModelFactory = HomeViewModelFactory(dataSource)

    val articleViewModelFactory = ArticleViewModelFactory()
}