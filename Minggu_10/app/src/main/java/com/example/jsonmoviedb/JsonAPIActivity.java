package com.example.jsonmoviedb;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
public class JsonAPIActivity extends AppCompatActivity{

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private ListView lv;

// URL to get contacts JSON

    private static String url="https://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> contactlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_api);
        contactlist = new ArrayList<>();
        lv = (ListView) findViewById(R.id.user_list);
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandle sh = new HttpHandle();
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url : " + jsonStr);

            if (jsonStr != null) {

                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting 350N Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(1);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");

                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key value
                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);
                        // adding contact to contact list c
                        contactlist.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json Parsin Error : " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json Parsing Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get Json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json Parsing Error gatau yg ini : ", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

            pDialog = new ProgressDialog(JsonAPIActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (pDialog.isShowing()){
                pDialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(
                    JsonAPIActivity.this, contactlist,
                    R.layout.list_item, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name, R.id.email, R.id.mobile}
            );
            lv.setAdapter(adapter);
        }
    }
}
