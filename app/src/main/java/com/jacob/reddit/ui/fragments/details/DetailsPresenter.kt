package com.jacob.reddit.ui.fragments.details

import com.jacob.reddit.core.CorePresenter
import com.jacob.reddit.repository.RedditRepository

class DetailsPresenter(repository: RedditRepository) : CorePresenter<DetailsView>(repository)