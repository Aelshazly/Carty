package com.navibees.core;

public class NaviBeesConstants {
    public static final int ACTIVITIES_FRAGMENT_ALL_INDEX = 0;
    public static final int ACTIVITIES_FRAGMENT_CURRENT_INDEX = 1;
    public static final String APP_CONFIGURATIONS_LAST_MODIFICATION_DATE_KEY = "com.navibees.appConfigurations.modificationDate";
    public static final String BACKGRORUND_ANALYTICS_REGION_ID = "navibees-background-analytics-region";
    public static final String BACKGROUND_ANALYTICS_ENABLED_KEY = "navibees_background_analytics_enabled_key";
    public static final String BACKGROUND_ANALYTICS_LAST_TIMESTAMP_KEY = "navibees_background_analytics_last_timestamp_key";
    public static final String BACKGROUND_MONITORED_REGIONS_KEY = "com.navibees.background.monitored.regions";
    public static final String BEACONS_CONFIGURATIONS_LAST_MODIFICATION_DATE_KEY = "com.navibees.beaconNodesConfigurations.modificationDate";
    public static final String BUILDING_FILE = "building_%s.json";
    public static final String BUILDING_META_DATA_LAST_MODIFICATION_DATE_KEY = "com.navibees.buildingMetaData.modificationDate";
    public static final String BUILDING_SYNC_ALL_PREFIX = "sync_all_";
    public static boolean CAMERA_MOVING = false;
    public static final int CELL_MARGIN_DP = 16;
    public static final int CELL_SIZE_DP = 56;
    public static final String ENABLE_ROUTING_WHEN_OUT_OF_VENUE = "com.navibees.enableRoutingWhenOutOfVenue";
    public static final String FONT_FOR_LANGUAGE_KEY = "com.navibees.font_for_%s_language";
    public static final String IS_APP_IN_FOREGROUND_KEY = "com.navibees.is.app.in.foreground";
    public static final String LAST_VENUE_ID_KEY = "com.navibees.last.venue.id";
    public static final String MAP_RESOURCES_APP_VERSION_KEY = "com.navibees.mapResources.app.version";
    public static final String MAP_RESOURCES_FOLDER = "MapResources";
    public static final String MAP_RESOURCES_FOLDER_IMAGE = "Images";
    public static final String MAP_RESOURCES_FOLDER_META_DATA = "Metadata";
    public static final String MAP_RESOURCES_FOLDER_NETWORK_DATASETS = "NetworkDatasets";
    public static final String MAP_RESOURCES_FOLDER_TILED_LAYERS = "TiledMaps";
    public static final String MONITORED_REGION_ACTION = "com.navibees.monitored.region.action";
    public static final String MONITORED_REGION_ACTION_TYPE_ENTER_VALUE = "ENTER";
    public static final String MONITORED_REGION_ACTION_TYPE_EXIT_VALUE = "EXIT";
    public static final String MONITORED_REGION_ACTION_TYPE_KEY = "com.navibees.monitored.region.action.type";
    public static final String MONITORED_REGION_INTERNAL_ACTION = "com.navibees.monitored.internal.action";
    public static final String MONITORED_REGION_OBJECT_KEY = "com.navibees.monitored.region.object";
    public static final String MONITORED_REGION_UNIQUE_IDENTIFIER_KEY = "com.navibees.monitored.region.uniqueIdentifier";
    public static final String NAVIBEES_APP_FILE = "NavibeesApplication.json";
    public static final String NETWORK_ACTIVITY_ACTION = "com.navibees.network.activity.action";
    public static final int NETWORK_ACTIVITY_ACTION_END = 2;
    public static final int NETWORK_ACTIVITY_ACTION_START = 1;
    public static final String NETWORK_ACTIVITY_ACTION_TYPE = "com.navibees.network.activity.type";
    public static final int PERMISSION_REQUEST_BACKGROUND_LOCATION = 8;
    public static final int REQUEST_CODE_ACTIVITY = 2;
    public static final int REQUEST_CODE_BACKGROUND_ANALYTICS_SERVICE = 7;
    public static final int REQUEST_CODE_DIRECTIONS = 0;
    public static final int REQUEST_CODE_ENABLE_LOCATION = 4;
    public static final int REQUEST_CODE_PERMISSION_FINE_LOCATION = 3;
    public static final int REQUEST_CODE_PLAY_SERVICES_RESOLUTION = 6;
    public static final int REQUEST_CODE_TTS_INSTALL_MISSING_LANG = 5;
    public static final String ROUTE_TO_CATEGORY_SORT_TYPE_KEY = "com.navibees.routeTo.categorySortType";
    public static final String ROUTE_TO_DESTINATION_SEARCH_TERM_KEY = "com.navibees.routeTo.destination.search.term";
    public static final String ROUTE_TO_FACILITY_KEY = "com.navibees.route_to_facitlity";
    public static final String ROUTE_TO_FACILITY_SORT_TYPE_KEY = "com.navibees.routeTo.facilitySortType";
    public static final String ROUTE_TO_INITIAL_SEARCH_TERM_KEY = "com.navibees.routeTo.initial.search.term";
    public static final String ROUTE_TO_LOCATION_KEY = "com.navibees.route_to_location";
    public static final String ROUTE_TO_LOCATION_NAME_KEY = "com.navibees.route_to_location_name";
    public static final String ROUTE_TO_POI_KEY = "com.navibees.route_to_poi";
    public static final String ROUTE_TO_POI_SORT_TYPE_KEY = "com.navibees.routeTo.poiSortType";
    public static final String ROUTE_WAYPOINTS = "com.navibees.route_waypoints";
    public static final String SAVED_LOCATION_KEY = "com.navibees.saved_location_";
    public static final String SELECTED_ACTIVITY_POI_ID = "selected_activity_poi_id";
    public static final String SELECTED_ATIVITY_BUILDING_ID = "selected_activity_building_id";
    public static final String SELECTED_PLACE_BUILDING_KEY = "selected_place_building_id";
    public static final String SELECTED_PLACE_ID_KEY = "selected_place_id";
    public static final String SELECTED_PLACE_TYPE_KEY = "selected_place_type";
    public static final String STATUS_LAST_UPDATED_DATE = "com.navibees.server.status_last_updated_date";

    public static final class InstructionsIconsNames {
        public static final String maneuverTypeChangeLayer = "maneuver_type_change_layer";
        public static final String maneuverTypeChangeModality = "maneuver_type_change_modality";
        public static final String maneuverTypeEnd = "maneuver_type_end";
        public static final String maneuverTypeGoDown = "maneuver_type_go_down";
        public static final String maneuverTypeGoStraight = "maneuver_type_go_straight";
        public static final String maneuverTypeGoUp = "maneuver_type_go_up";
        public static final String maneuverTypeStart = "maneuver_type_start";
        public static final String maneuverTypeTurnGentleLeft = "maneuver_type_turn_gentle_left";
        public static final String maneuverTypeTurnGentleRight = "maneuver_type_turn_gentle_right";
        public static final String maneuverTypeTurnLeft = "maneuver_type_turn_left";
        public static final String maneuverTypeTurnRight = "maneuver_type_turn_right";
        public static final String maneuverTypeTurnSharpLeft = "maneuver_type_turn_sharp_left";
        public static final String maneuverTypeTurnSharpRight = "maneuver_type_turn_sharp_right";
        public static final String maneuverTypeUTurnLeft = "maneuver_type_u_turn_left";
        public static final String maneuverTypeUTurnRight = "maneuver_type_u_turn_right";
        public static final String maneuverTypeUnknown = "maneuver_type_unknown";
        public static final String maneuverTypeWaypoint = "maneuver_type_waypoint";
    }

    public static final class MapInteraction {
        public static final String ACTION = "mapInteraction";
        public static final String ACTION_SHARE_LOCATION = "com.navibees.action_share_location";
        public static final String SHARED_LOCATION_KEY = "com.navibees.shared_location_key";
    }

    public static final class UpdateRoutingInfoToDestinationPOI {
        public static final String ACTION = "com.navibees.sdk.updateRoutingInfoToDestinationPOI";
        public static final String DESTINATION_POI_FLOOR_NAME = "com.navibees.sdk.destinationPOIFloor";
        public static final String DESTINATION_POI_NAME = "com.navibees.sdk.destinationPOI";
        public static final String DURATION = "com.navibees.sdk.remainingTravelDuration";
        public static final String LENGTH = "com.navibees.sdk.remainingTravelLength";
    }
}
