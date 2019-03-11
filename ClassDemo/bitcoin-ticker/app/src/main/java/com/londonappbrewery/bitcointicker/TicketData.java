package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

public class TicketData {

    private double changeRate;

    public static TicketData getTickectDataModel(JSONObject json,String currency) throws NullPointerException{
        TicketData dataModel = new TicketData();
        try{
            dataModel.changeRate = json.getJSONObject("rate").getJSONObject(currency).getDouble("rate");
        }catch (JSONException e )
        {
            System.out.print("DATA:Json fucked up");
            return null;
        }
        return dataModel;

    }
}
