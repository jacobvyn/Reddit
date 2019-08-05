package com.jacob.reddit.ui.fragments.details.presenter

import com.jacob.reddit.core.CorePresenter
import com.jacob.reddit.repository.RedditRepository
import com.jacob.reddit.ui.fragments.details.DetailsView

class DetailsPresenter(repository: RedditRepository) : CorePresenter<DetailsView>(repository)