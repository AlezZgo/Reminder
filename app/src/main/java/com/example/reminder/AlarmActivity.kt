package com.example.reminder

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.databinding.ActivityAlarmBinding

class AlarmActivity: AppCompatActivity() {

    val ringtone : Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAlarmBinding.inflate(layoutInflater)
        binding.stopButton.setOnClickListener {

            var notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            var ringtone =  RingtoneManager.getRingtone(this,notificationUri)
            if(ringtone == null){
                notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                ringtone =  RingtoneManager.getRingtone(this,notificationUri)
            }
            if(ringtone != null){
                ringtone.play()
            }
        }
        setContentView(binding.root)
    }

    override fun onDestroy() {
        if(ringtone != null && ringtone.isPlaying){
            ringtone.stop()
        }


        super.onDestroy()
    }
}