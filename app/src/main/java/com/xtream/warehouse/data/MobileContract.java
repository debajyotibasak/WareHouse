package com.xtream.warehouse.data;

import android.provider.BaseColumns;

public final class MobileContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private MobileContract() {}

    /**
     * Inner class that defines constant values for the mobiles database table.
     * Each entry in the table represents a single item.
     */
    public static final class MobileEntry implements BaseColumns{

        //Name of the database
        public final static String TABLE_NAME = "mobiles";

        //Unique ID number for mobile (TYPE: INTEGER)
        public final static String _ID = BaseColumns._ID;

        //Name of the mobile (TYPE : TEXT)
        public final static String COLUMN_MOBILE_NAME = "name";

        //Name of mobile company (TYPE: TEXT)
        public final static String COLUMN_COMPANY = "company";

        //Type of Mobile (TYPE: INTEGER)
        public final static String COLUMN_TYPE = "Type";

        //Software Version of Mobile (TYPE: TEXT)
        public final static String COLUMN_SOFTWARE_VERSION = "Version";

        //Amount of RAM present in mobile (TYPE: INTEGER)
        public final static String COLUMN_RAM = "RAM";

        //Amount of storage present in mobile (TYPE: INTEGER)
        public final static String COLUMN_STORAGE = "Storage";

        //Possible values for type of Mobile
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_SMARTPHONE = 1;
        public static final int TYPE_FEATUREPHONE = 2;

        //Possible values for RAM
        public static final int RAM_UNKNOWN = 0;
        public static final int RAM_1GBMINUS = 1;
        public static final int RAM_1GB= 2;
        public static final int RAM_2GB = 3;
        public static final int RAM_3GB = 4;
        public static final int RAM_4GB = 5;
        public static final int RAM_4GBPLUS = 6;

        //Possible values for storage
        public static final int STORAGE_UNKNOWN = 0;
        public static final int STORAGE_8GBMINUS = 1;
        public static final int STORAGE_8GB = 2;
        public static final int STORAGE_16GB = 3;
        public static final int STORAGE_32GB = 4;
        public static final int STORAGE_64GB = 5;
        public static final int STORAGE_128GB = 6;
        public static final int STORAGE_128GBPLUS = 7;
    }
}
