package com.shiraj.gui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.shiraj.base.activity.BaseActivity
import com.shiraj.gui.databinding.ActivityPokemonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : BaseActivity() {
    override val layoutResId: Int
        get() = R.layout.activity_pokemon

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = ActivityPokemonBinding::inflate

    override val binding: ActivityPokemonBinding
        get() = super.binding as ActivityPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}