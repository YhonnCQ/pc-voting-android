package com.example.pc_voting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pc_voting.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val singleItems = arrayOf("Breaking Bad", "Mr. Robot")
        var breakingBadVotes = 0
        var mrRobotVotes = 0
        binding.button.setOnClickListener {
            var checkedItem = 0
            var selectedFilm = singleItems[checkedItem]
            MaterialAlertDialogBuilder(this)
                .setTitle("Votación")
                .setNeutralButton("CANCELAR") { _, _ ->

                }
                .setPositiveButton("VOTAR") { _, _ ->
                    when (selectedFilm) {
                        singleItems[0] -> breakingBadVotes++
                        singleItems[1] -> mrRobotVotes++
                    }
                    val voters = breakingBadVotes + mrRobotVotes
                    binding.textVieww.text = "Votantes: $voters"
                    val result = when {
                        breakingBadVotes == mrRobotVotes -> {
                            "EMPATE"
                        }
                        breakingBadVotes > mrRobotVotes -> {
                            singleItems[0]
                        }
                        else -> {
                            singleItems[1]
                        }
                    }
                    Snackbar.make(binding.root, result, Snackbar.LENGTH_SHORT)
                        .show()
                }
                .setSingleChoiceItems(singleItems, checkedItem) { _, which ->
                    checkedItem = which
                    selectedFilm = singleItems[which]
                }
                .show()
        }
    }
}