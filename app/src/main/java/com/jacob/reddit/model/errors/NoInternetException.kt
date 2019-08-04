package com.jacob.reddit.model.errors

import java.io.IOException

class NoInternetException(error: String) : IOException(error)
