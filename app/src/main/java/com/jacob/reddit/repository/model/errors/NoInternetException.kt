package com.jacob.reddit.repository.model.errors

import java.io.IOException

class NoInternetException(error: String) : IOException(error)
