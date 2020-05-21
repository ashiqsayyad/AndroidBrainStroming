package com.ashsample.androidconcepts.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
//https://guides.codepath.com/android/Connectivity-using-the-Bluetooth-API

object BluetoothUtil {

    public fun isBluetoothSupported():Boolean{
        val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: return false
         return true

    }

    public fun isBluetoothEnabled(): Boolean {
        val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: return false
         return bluetoothAdapter.isEnabled
    }

    public fun getPairedDevices(){
        //val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val pairedDevices: Set<BluetoothDevice>? = BluetoothAdapter.getDefaultAdapter()?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
        }
    }
}