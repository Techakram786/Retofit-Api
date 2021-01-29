package com.imran.api;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Showaccount extends Fragment {
RecyclerView recyclerView;
String showuserdata;
List<Getdata> list=new ArrayList<>();
RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view=inflater.inflate(R.layout.fragment_showaccount, container, false);


      initview(view);
      recycler();
      api();
      showdata();
      return view;
    }


    private void initview(View view)
    {
        recyclerView=view.findViewById(R.id.recycler);
        requestQueue= Volley.newRequestQueue(getActivity());
    }

    private void recycler()
    {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void api()
    {
        showuserdata="https://techakram786.000webhostapp.com/data.php";

    }

    private void showdata()
    {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("please wait..");
        pd.show();

        StringRequest myRequest = new StringRequest(Request.Method.POST,showuserdata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                pd.dismiss();
               // ArrayList arrayList=new ArrayList();

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jobj=jsonArray.getJSONObject(i);
                        String id=jobj.getString("id");
                        String name=jobj.getString("name");
                        String address=jobj.getString("address");

                        list.add(new Getdata(id,name,address));

                        Log.e("error",name.toString()+address.toString());
                    }
                    Adapter adapter=new Adapter(list);
                    recyclerView.setAdapter(adapter);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> mymap=new HashMap<String, String>();

                mymap.put("type_id","1");
                return mymap;
            }
        };

        requestQueue.add(myRequest);
    }
}