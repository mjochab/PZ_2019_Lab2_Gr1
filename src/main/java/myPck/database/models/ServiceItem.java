package myPck.database.models;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "service_items")
public class ServiceItem implements BaseModel{



    private int id;

    private int price;

    private int serviceName;



}
