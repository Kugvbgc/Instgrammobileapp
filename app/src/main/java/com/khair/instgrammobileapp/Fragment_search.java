package com.khair.instgrammobileapp;

import static com.khair.instgrammobileapp.R.id.recyclerView;
import static com.khair.instgrammobileapp.R.id.searchView;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment_search extends Fragment {

    RecyclerView recyclerView12;
    SearchView searchView12;
    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;
    MyAdApter myAdApter=new MyAdApter(arrayList);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_search, container, false);
        if (container!=null){
            container.removeAllViews();
        }


       recyclerView12=myView.findViewById(R.id.recyclerView);
        searchView12=myView.findViewById(R.id.searchView);

        SearchView_item();


        searchView12.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }
        });



        return myView;
    }

    /////////////////////////////////
    public class MyAdApter extends RecyclerView.Adapter<MyAdApter.MyViewHolder>{
        ArrayList<HashMap<String,String>>searchList;

        public MyAdApter(ArrayList<HashMap<String, String>> arrayList) {
            this.searchList = arrayList;
        }
        public void setFilteredList(ArrayList<HashMap<String, String>> filteredList) {
            this.searchList = filteredList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=getLayoutInflater();
            View myView=inflater.inflate(R.layout.searchlist,parent,false);



            return new MyViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            hashMap=searchList.get(position);
            String title=hashMap.get("name");
            String images=hashMap.get("images");

            Picasso.get().load("https://abulk77912.000webhostapp.com/apps/home/"+images).into(holder.imageView);
            holder.textView.setText(title);

        }

        @Override
        public int getItemCount() {
            return searchList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView textView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView=itemView.findViewById(R.id.imageView);
                textView=itemView.findViewById(R.id.textView);
            }
        }

    }
    ////=======================================================
    private void fileList(String newText) {
        ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
        for (HashMap<String, String> detailsItem : arrayList) {
            if (detailsItem.get("name").toLowerCase().contains(newText.toLowerCase())) {
                arrayList1.add(detailsItem);
            }
        }
        if (arrayList1.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            myAdApter.setFilteredList(arrayList1);
        }
    }
    public void SearchView_item(){

        arrayList=new ArrayList<>();

        String homelist_url="https://abulk77912.000webhostapp.com/apps/home_view.php";

        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, homelist_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int x=0;x<jsonArray.length();x++){
                    try {
                        JSONObject jsonObject=jsonArray.getJSONObject(x);
                        String images_link=jsonObject.getString("images_link");
                        String title=jsonObject.getString("title");

                        hashMap=new HashMap<>();
                        hashMap.put("images",images_link);
                        hashMap.put("name",title);
                        arrayList.add(hashMap);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    MyAdApter myAdApter1=new MyAdApter(arrayList);
                    recyclerView12.setAdapter(myAdApter1);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerView12.setLayoutManager(gridLayoutManager);



                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(arrayRequest);





    }

    ///////////////

}