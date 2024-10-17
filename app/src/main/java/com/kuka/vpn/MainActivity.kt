package com.kuka.vpn

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import de.blinkt.openvpn.api.IOpenVPNAPIService
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    private var mService: IOpenVPNAPIService? = null
    private companion object {
        private const val ICS_OPENVPN_PERMISSION = 101
    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            className: ComponentName,
            service: IBinder
        ) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  We are communicating with our
            // service through an IDL interface, so get a client-side
            // representation of that from the raw service object.

            mService = IOpenVPNAPIService.Stub.asInterface(service)

            try {
                // Request permission to use the API
                val i = mService?.prepare(packageName)
                if (i != null) {
                    startActivityForResult(i, ICS_OPENVPN_PERMISSION)
                } else {
                    onActivityResult(ICS_OPENVPN_PERMISSION, RESULT_OK, null)
                }
            } catch (e: RemoteException) {
                Log.e("MainActivity", "openvpn service connection failed: " + e.message)
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null
        }
    }

    private fun bindService() {
        val icsopenvpnService = Intent(IOpenVPNAPIService::class.java.name)
        icsopenvpnService.setPackage("com.kuka.vpn");
        bindService(icsopenvpnService, mConnection, BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        bindService()
        findViewById<TextView>(R.id.text_hello).setOnClickListener {
            startVpn()
        }
    }

    /**
     * Start the VPN
     */
    private fun startVpn() {
        try {
            // .ovpn file
            val conf: InputStream = assets.open("vpnbook-ca149-udp25000.ovpn")
            val isr = InputStreamReader(conf)
            val br = BufferedReader(isr)
            var config = ""
            var line: String?

            while (true) {
                line = br.readLine()
                if (line == null) break
                config += line + "\n"
            }

            br.readLine()

            OpenVpnApi.startVpn(
                this,
                config,
                "ca",
                "vpnbook",
                "b6xnvt9"
            )

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

}