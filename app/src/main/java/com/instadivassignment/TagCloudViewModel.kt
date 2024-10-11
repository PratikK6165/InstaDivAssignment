package com.instadivassignment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TagCloudViewModel: ViewModel() {
    private val _tagList = MutableStateFlow<List<String>>(emptyList())
    val tagList: StateFlow<List<String>> = _tagList

    init {
        fetchTagsFromFirebase()
    }

    private fun fetchTagsFromFirebase() {
        // Log that we're starting to fetch tags
        Log.d("TagCloudViewModel", "Fetching tags from Firebase...")

        Firebase.firestore.collection("tags")
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("TagCloudViewModel", "No tags found in Firestore")
                } else {
                    val tags = documents.map { it.getString("name") ?: "" }
                    _tagList.value = tags
                    Log.d("TagCloudViewModel", "Fetched tags: $tags")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("TagCloudViewModel", "Error fetching tags: ${exception.message}", exception)
            }
    }
}