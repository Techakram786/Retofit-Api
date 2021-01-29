package com.imran.api;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Newaccount extends Fragment {
Spinner customertype;
String [] type;
private EditText cname,caddress,cprice,cmobileno,cuid;
String createapi;
private Button save,showaccont;
RequestQueue requestQueue;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_newaccount, container, false);
        type= getResources().getStringArray(R.array.customertype);


        initview(view);
        api();
        sent();
        getuserdata();
        spinner();

        return view;
    }

    private void spinner()
    {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customertype.setAdapter(adapter);
    }


    private void initview(View view)
    {
        cname=view.findViewById(R.id.customer_name);
        caddress=view.findViewById(R.id.customer_address);
        cprice=view.findViewById(R.id.customer_fixedprice);
        cmobileno=view.findViewById(R.id.customer_mobileno);
        cuid=view.findViewById(R.id.customer_userid);
        customertype=view.findViewById(R.id.customer_custype);
        save=view.findViewById(R.id.customer_save);
        showaccont=view.findViewById(R.id.customer_showdata);
       requestQueue= Volley.newRequestQueue(getActivity());


    }

    private void api()
    {
         createapi="https://techakram786.000webhostapp.com/insert.php";

    }
    private void sent()
    {

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd=new ProgressDialog(getActivity());
                pd.setMessage("Please Wait");
                pd.show();
                StringRequest myrequest=new StringRequest(Request.Method.POST, createapi, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        pd.dismiss();
                        Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pd.dismiss();
                        Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> mymap=new HashMap<String, String>();

                        mymap.put("name",cname.getText().toString());
                        mymap.put("mob",cmobileno.getText().toString());
                       // mymap.put("role_id",customertype.getSelectedItem().toString());
                         mymap.put("role_id","1");
                        mymap.put("u_id",cuid.getText().toString());
                        mymap.put("fixed_price",cprice.getText().toString());
                        mymap.put("address",caddress.getText().toString());

                        return mymap; //return my map
                    }
                };
                requestQueue.add(myrequest);
            }
        });

    }

    private void getuserdata()
    {
        showaccont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Showaccount sh=new Showaccount();
                FragmentManager fm= getFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                ft.replace(R.id.main,sh);
                ft.addToBackStack("");
                ft.commit();


            }
        });
    }

}