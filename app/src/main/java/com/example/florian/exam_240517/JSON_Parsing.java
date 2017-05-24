package com.example.florian.exam_240517;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by florian on 24/05/2017.
 */

public class JSON_Parsing extends AppCompatActivity {

    private String apiPath = "http://api.androidhive.info/contacts/";
    private ProgressDialog processDialog;
    private JSONArray resultJsonArray;
    private int success = 0;
    ListView Displayer;
    public ArrayList<Object_Classes.Contacts> list_contacts_json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_parsing);
        Displayer = (ListView) findViewById(R.id.List_JSON);
        list_contacts_json = new ArrayList<Object_Classes.Contacts>();

        new myAsyncSystem(this, this).execute();

    }

    private class myAsyncSystem extends AsyncTask <Void, Void, Void> {

        private Context myContext;
        private Activity myActivity;
        String response = "";
        HashMap<String, String> postDataParams;

        public myAsyncSystem(Context context, Activity activity) {
            myContext = context;
            myActivity = activity;
        }

        @Override
        protected Void doInBackground(Void... params) {
            postDataParams = new HashMap<String, String>();
            postDataParams.put("HTTP_ACCEPT", "application/json");

            HttpConnectionService service = new HttpConnectionService();
            response = service.sendRequest(apiPath, postDataParams);
            try {
                success = 1;
                JSONObject resultJsonObject = new JSONObject(response);
                resultJsonArray = resultJsonObject.getJSONArray("contacts");
            } catch (JSONException e) {
                success = 0;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(myContext);
            processDialog.setMessage("5 secondes bonhomme ...");
            processDialog.setCancelable(false);
            processDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (processDialog.isShowing()) {
                processDialog.dismiss();
            }

            if (success == 1) {
                if (null != resultJsonArray) {
                    //ArrayAdapter listViewAdapter = new ArrayAdapter<String>(myContext, R.layout.list_element);

                    for (int i = 0; i < resultJsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = resultJsonArray.getJSONObject(i);

                            //listViewAdapter.add(jsonObject.get("name"));

                            Object_Classes.Contacts someguy = new Object_Classes.Contacts();

                            someguy.setId(jsonObject.getString("id"));
                            someguy.setCName(jsonObject.getString("name"));
                            someguy.setMail(jsonObject.getString("email"));
                            someguy.setAdress(jsonObject.getString("address"));
                            someguy.setGender(jsonObject.getString("gender"));

                            list_contacts_json.add(someguy);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    CustomAdapter adapter = new CustomAdapter(myContext, list_contacts_json);
                    Displayer.setAdapter(adapter);
                    //Displayer.setAdapter(listViewAdapter);
                }
            }
        }

    }
}
