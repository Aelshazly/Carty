package com.neovisionaries.bluetooth.ble;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StandardGattService {
    ALERT_NOTIFICATION_SERVICE("Alert Notification Service", "alert_notification", 6161),
    AUTOMATION_IO("Automation IO", "automation_io", 6165),
    BATTERY_SERVICE("Battery Service", "battery_service", 6159),
    BLOOD_PRESSURE("Blood Pressure", "blood_pressure", 6160),
    BODY_COMPOSITION("Body Composition", "body_composition", 6171),
    BOND_MANAGEMENT("Bond Management", "bond_management", 6174),
    CONTINUOUS_GLUCOSE_MONITORING("Continuous Glucose Monitoring", "continuous_glucose_monitoring", 6175),
    CURRENT_TIME_SERVICE("Current Time Service", "current_time", 6149),
    CYCLING_POWER("Cycling Power", "cycling_power", 6168),
    CYCLING_SPEED_AND_CADENCE("Cycling Speed and Cadence", "cycling_speed_and_cadence", 6166),
    DEVICE_INFORMATION("Device Information", "device_information", 6154),
    ENVIRONMENTAL_SENSING("Environmental Sensing", "environmental_sensing", 6170),
    GENERIC_ACCESS("Generic Access", "generic_access", 6144),
    GENERIC_ATTRIBUTE("Generic Attribute", "generic_attribute", 6145),
    GLUCOSE("Glucose", "glucose", 6152),
    HEALTH_THERMOMETER("Health Thermometer", "health_thermometer", 6153),
    HEART_RATE("Heart Rate", "heart_rate", 6157),
    HTTP_PROXY("HTTP Proxy", "http_proxy", 6179),
    HUMAN_INTERFACE_DEVICE("Human Interface Device", "human_interface_device", 6162),
    IMMEDIATE_ALERT("Immediate Alert", "immediate_alert", 6146),
    INDOOR_POSITIONING("Indoor Positioning", "indoor_positioning", 6177),
    INTERNET_PROTOCOL_SUPPORT("Internet Protocol Support", "internet_protocol_support", 6176),
    LINK_LOSS("Link Loss", "link_loss", 6147),
    LOCATION_AND_NAVIGATION("Location and Navigation", "location_and_navigation", 6169),
    NEXT_DST_CHANGE_SERVICE("Next DST Change Service", "next_dst_change", 6151),
    OBJECT_TRANSFER("Object Transfer", "object_transfer", 6181),
    PHONE_ALERT_STATUS_SERVICE("Phone Alert Status Service", "phone_alert_status", 6158),
    PULSE_OXIMETER("Pulse Oximeter", "pulse_oximeter", 6178),
    REFERENCE_TIME_UPDATE_SERVICE("Reference Time Update Service", "reference_time_update", 6150),
    RUNNING_SPEED_AND_CADENCE("Running Speed and Cadence", "running_speed_and_cadence", 6164),
    SCAN_PARAMETERS("Scan Parameters", "scan_parameters", 6163),
    TRANSPORT_DISCOVERY("Transport Discovery", "transport_discovery", 6180),
    TX_POWER("Tx Power", "tx_power", 6148),
    USER_DATA("User Data", "user_data", 6172),
    WEIGHT_SCALE("Weight Scale", "weight_scale", 6173);
    
    private static final String SPECIFICATION_TYPE_PREFIX = "org.bluetooth.service.";
    private static final Pattern UUID_PATTERN = null;
    private static final Map<Integer, StandardGattService> sNumberToInstanceMap = null;
    private final String mName;
    private final int mNumber;
    private final String mShortType;
    private transient String mType;

    static {
        int i;
        StandardGattService[] values;
        UUID_PATTERN = Pattern.compile("0000([0-9a-fA-F]{4})[-]?0000[-]?1000[-]?8000[-]?00805[fF]9[bB]34[fF][bB]");
        sNumberToInstanceMap = new HashMap();
        for (StandardGattService instance : values()) {
            sNumberToInstanceMap.put(Integer.valueOf(instance.getNumber()), instance);
        }
    }

    private StandardGattService(String name, String shortType, int number) {
        this.mName = name;
        this.mShortType = shortType;
        this.mNumber = number;
    }

    public String getName() {
        return this.mName;
    }

    public String getShortType() {
        return this.mShortType;
    }

    public String getType() {
        String str = this.mType;
        if (str != null) {
            return str;
        }
        synchronized (this) {
            if (this.mType == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(SPECIFICATION_TYPE_PREFIX);
                sb.append(this.mShortType);
                this.mType = sb.toString();
            }
        }
        return this.mType;
    }

    public int getNumber() {
        return this.mNumber;
    }

    public static StandardGattService getByNumber(int number) {
        return (StandardGattService) sNumberToInstanceMap.get(Integer.valueOf(number));
    }

    public static StandardGattService getByUuid(String uuid) {
        if (uuid == null) {
            return null;
        }
        Matcher matcher = UUID_PATTERN.matcher(uuid);
        if (!matcher.matches()) {
            return null;
        }
        try {
            return getByNumber(Integer.parseInt(matcher.group(1), 16));
        } catch (Exception e) {
            return null;
        }
    }

    public static StandardGattService getByUuid(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return getByUuid(uuid.toString());
    }
}
