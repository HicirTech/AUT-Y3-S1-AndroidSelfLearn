package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

public class TicketData {

    private double changeRate;

    public static TicketData getTickectDataModel(JSONObject json,String currency) {
        TicketData dataModel = new TicketData();
        try{
            //json.toJSONArray().getJSONObject("rate").getJSONObject(currency).getString("rate");
            String jsonResult = json.getJSONObject("rates").getJSONObject(currency).getString("rate");
            System.out.println("inter"+jsonResult);

            dataModel.changeRate=Double.valueOf(jsonResult);
        }catch (JSONException e )
        {
            System.out.print("DATA:Json fucked up");
            return null;
        }
        return dataModel;
    }

    public double getChangeRate() {
        return changeRate;
    }
}
