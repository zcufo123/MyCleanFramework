package com.example.mytestapplicationframework.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mytestapplicationframework.data.repository.CharacterRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}
