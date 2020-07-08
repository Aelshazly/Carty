package com.navibees.core.blesdk.throwables;

public class BluetoothNotPermittedException extends Exception {
    public String getMessage() {
        return "Bluetooth Not Permitted";
    }
}
