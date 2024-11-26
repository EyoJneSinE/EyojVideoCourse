package com.eniskaner.courselist.ui.util

import androidx.appcompat.widget.SearchView.OnQueryTextListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CourseSearchTextWatcher(
    private val onSearch: (String) -> Unit,
) : OnQueryTextListener {

    private var searchJob: Job? = null
    private val debounceTime: Long = 500L

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { triggerSearch(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = CoroutineScope(Dispatchers.Main).launch {
            delay(debounceTime)
            newText?.let { triggerSearch(it) }
        }
        return true
    }

    private fun triggerSearch(query: String) {
        onSearch(query)
    }
}
