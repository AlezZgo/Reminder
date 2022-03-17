package com.example.reminder

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.databinding.ActivityAlarmBinding


class AlarmActivity : AppCompatActivity() {

    private val ringtone: Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAlarmBinding.inflate(layoutInflater)
        binding.stopButton.setOnClickListener {
            finish()
        }
        var notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var ringtone = RingtoneManager.getRingtone(this, notificationUri)
        if (ringtone == null) {
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(this, notificationUri)
        }
        ringtone?.play()
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v.vibrate(500)
        }
        setContentView(binding.root)
    }

    override fun onDestroy() {
        if (ringtone != null && ringtone.isPlaying) {
            ringtone.stop()
        }


        super.onDestroy()
    }
}