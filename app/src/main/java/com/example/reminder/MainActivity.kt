package com.example.reminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setMinute(0)
                .setHour(12)
                .setTitleText("Set the time of alarm")
                .build().apply {
                    addOnPositiveButtonClickListener {
                        val calendar = Calendar.getInstance()
                        calendar.set(Calendar.SECOND, 0)
                        calendar.set(Calendar.MILLISECOND, 0)
                        calendar.set(Calendar.MINUTE, this.minute)
                        calendar.set(Calendar.HOUR_OF_DAY, this.hour)
                    }
                }
            materialTimePicker.show(supportFragmentManager,"tag_picker")
        }
        setContentView(binding.root)
    }
}