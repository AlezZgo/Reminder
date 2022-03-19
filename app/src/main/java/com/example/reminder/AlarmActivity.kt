package com.example.reminder

import android.annotation.SuppressLint
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.*
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {

    private var ringtone: Ringtone? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAlarmBinding.inflate(layoutInflater)
        binding.stopButton.setOnClickListener {
            finish()
        }
        var notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notificationUri)
        if (ringtone == null) {
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(this, notificationUri)
        }
        ringtone?.play()
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val powerManager =  getSystemService(POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.FULL_WAKE_LOCK,"tag")

        this.window.apply {
            addFlags(FLAG_SHOW_WHEN_LOCKED)
            addFlags(FLAG_DISMISS_KEYGUARD)
            addFlags(FLAG_TURN_SCREEN_ON)
        }


        wakeLock.acquire(1*60*1000L )

        wakeLock.release()


        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v.vibrate(5000)
        }
    }

    override fun onDestroy() {
        ringtone?.stop()
        super.onDestroy()
    }
}