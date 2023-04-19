/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.cameraxbasic.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.example.cameraxbasic.R
import com.android.example.cameraxbasic.databinding.FragmentGalleryBinding
import com.android.example.cameraxbasic.utils.MediaStoreFile
import com.android.example.cameraxbasic.utils.MediaStoreUtils
import com.android.example.cameraxbasic.utils.padWithDisplayCutout
import com.android.example.cameraxbasic.utils.showImmersive
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

/** Fragment used to present the user with a gallery of photos taken */
class GalleryFragment internal constructor() : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)
        binding = FragmentGalleryBinding.inflate(layoutInflater)

        // get shared preferences
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        // get references to the EditText views and load the saved preferences if any
        binding.adresse.setText(sharedPreferences.getString("adresse", ""))
        binding.port.setText(sharedPreferences.getString("port", ""))

        // get reference to the save button and set a click listener to save the input values in preferences
        binding.saveButton.setOnClickListener {
            with(sharedPreferences.edit()) {
                putString("adresse", binding.adresse.text.toString())
                putString("port", binding.port.text.toString())
                apply()
            }
        }
    }
}
