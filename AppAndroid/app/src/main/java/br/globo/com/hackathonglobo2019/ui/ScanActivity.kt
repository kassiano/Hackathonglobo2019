package br.globo.com.hackathonglobo2019.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import br.globo.com.hackathonglobo2019.R
import com.google.zxing.Result
import kotlinx.android.synthetic.main.scan_activity.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanActivity:Activity(),
    ZXingScannerView.ResultHandler{

    companion object {
        const val MY_PERMISSIONS_CAMERA = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_activity)


        scanView.setResultHandler(this)

        if(!requestCameraPermission()){
            askCameraPermission()
        }else{
            scanView.setAutoFocus(true)
            scanView.startCamera()
        }

    }

    fun requestCameraPermission():Boolean {
        val permision = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return permision == PackageManager.PERMISSION_GRANTED
    }

    fun askCameraPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
            MainActivity.USE_CAMERA_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_CAMERA -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scanView.setAutoFocus(true)
                    scanView.startCamera()

                } else {

                    Toast.makeText(this, "Impossivel ler código, acesso camera negado.", Toast.LENGTH_SHORT)
                        .show()
                }
                return
            }
        }
    }

    override fun handleResult(rawResult: Result?) {
        val result:String = rawResult?.text ?:""
        Log.d("QRCODE_READER",result)

        val intent = Intent().apply {
            putExtra(MainActivity.SCAN_CODE_RESULT, result)
        }

        setResult(Activity.RESULT_OK, intent)
        finish()

    }

    override fun onStop() {
        super.onStop()
        scanView.stopCamera()
    }


}