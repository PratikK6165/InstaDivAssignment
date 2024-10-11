package com.instadivassignment

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()

    fun fetchTags(onTagsFetched: (List<String>) -> Unit) {
        db.collection("tags")
            .get()
            .addOnSuccessListener { result ->
                val tags = result.map { it.getString("name") ?: "" }
                onTagsFetched(tags)
            }
    }

}