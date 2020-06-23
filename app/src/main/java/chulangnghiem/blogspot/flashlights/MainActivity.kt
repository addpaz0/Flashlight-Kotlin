package chulangnghiem.blogspot.flashlights

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var objCameraManager: CameraManager? = null
    private var mCameraId: String? = null
    private var ivOnOff: ImageButton? = null
    private var isTouchOn: Boolean? = null


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivOnOff = findViewById(R.id.btnLight)
        isTouchOn = false

        objCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager?
        try {
            mCameraId = objCameraManager!!.cameraIdList[0]

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
        ivOnOff!!.setOnClickListener {
            try {
                if (isTouchOn!!) {
                    turnOffLight()
                    isTouchOn = false
                } else {
                    turnOnLight()
                    isTouchOn = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun turnOnLight() {
        btnLight.setImageResource(R.drawable.flashon)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                objCameraManager!!.setTorchMode(mCameraId!!, true)

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun turnOffLight() {
        btnLight.setImageResource(R.drawable.flashoff)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                objCameraManager!!.setTorchMode(mCameraId!!, false)

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
