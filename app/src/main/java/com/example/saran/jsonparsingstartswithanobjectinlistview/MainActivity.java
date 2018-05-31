package com.example.saran.jsonparsingstartswithanobjectinlistview;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    StringRequest stringRequest;
    RequestQueue requestQueue;

    ListView lv;
    String url = "http://siva123.000webhostapp.com/php/read_json.php";

    // String [] country = {"USA","India","Australia"};

    ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.listview);


        ArrayAdapter<String> ad = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {


                        try
                        {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject nameset = (JSONObject)jsonArray.get(i);
                                String namestring = nameset.getString("name");
                                arrayList.add(namestring);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                        JSONObject jsonObject;
//
//                        JSONArray jsonArray;
//
//
//
//                            try {
//                                jsonObject = new JSONObject(response);
//                                jsonArray = jsonObject.getJSONArray("worldpopulation");
//
//                                for (int i = 0; i <jsonArray.length() ; i++)
//                                {
//                                    JSONObject country = (JSONObject)jsonArray.get(i);
//                                    String countries1 = country.getString("country");
//                                    arrayList.add(countries1);
//
//
//                                }
//
//
//
//                        }
//                        catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }


                    } /*catch (JSONException e) {
                       e.printStackTrace();
                   }*/


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "the error is"+error, Toast.LENGTH_SHORT).show();

            }
        })
        {

        };
        requestQueue.add(stringRequest);




    }
}
