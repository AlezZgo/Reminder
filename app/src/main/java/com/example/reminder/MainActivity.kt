package com.example.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {

            timePicker().show(supportFragmentManager,"tag_picker")
        }
        setContentView(binding.root)
    }

    private fun timePicker() = MaterialTimePicker.Builder()
    .setTimeFormat(TimeFormat.CLOCK_12H)
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

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val info = AlarmManager.AlarmClockInfo(calendar.timeInMillis ,pendingIntent())

            alarmManager.setAlarmClock(info,alarmActionPendingIntent())

            val simpleDateFormat = SimpleDateFormat("HH:mm",Locale.getDefault())

            Toast.makeText(applicationContext,"AlarmClock ${simpleDateFormat.format(calendar.time)}",Toast.LENGTH_LONG).show()
        }
    }

    private fun pendingIntent(): PendingIntent? {
        val alarmInfoIntent = Intent(this,MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return PendingIntent.getActivity(this,0,alarmInfoIntent,PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun alarmActionPendingIntent(): PendingIntent? {
        val intent = Intent(this,AlarmActivity::class.java )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)
    }
}