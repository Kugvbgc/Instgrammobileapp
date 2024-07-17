package com.khair.instgrammobileapp;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

import de.hdodenhof.circleimageview.CircleImageView;


public class Fast_Fragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    ArrayList<HashMap<String,String>>homeList=new ArrayList<>();
    HashMap<String,String>homeMap;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.fragment_fast_, container, false);
        if (container!=null){
            container.removeAllViews();
        }
        recyclerView = myView.findViewById(R.id.story_recyclerView);
        recyclerView2= myView.findViewById(R.id.recyclerView2);

        // Create a horizontal LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Create item list
      lodData();


        // Set adapter

      //////////////////////////////////////////////

        home_itemLode();



        return myView;
    }
    //==================================================


///===============================

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        public  class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView profile_image;
            TextView textView;

            public ViewHolder(View view) {
                super(view);
                profile_image = view.findViewById(R.id.profile_image);
                textView = view.findViewById(R.id.textView);
            }
        }



        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            hashMap=arrayList.get(position);
            String title=hashMap.get("title");
            String Images=hashMap.get("images_link");

            Picasso.get().load("https://abulk77912.000webhostapp.com/apps/home/"+Images).into(holder.profile_image);






            holder.textView.setText(title);



            holder.profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), profile_image.class));
                    Bitmap bitmap= ((BitmapDrawable) holder.profile_image.getDrawable()).getBitmap();
                    profile_image.bitmap=bitmap;

                    //https://abulk77912.000webhostapp.com/apps/home/

                }
            });
        }

        @Override
        public int getItemCount() {



            return arrayList.size();
        }
    }
 /////////////////stroy adapter end here ====================================
 public class MyAdApter1 extends RecyclerView.Adapter<MyAdApter1.MyViewHolder1>{

     public class MyViewHolder1 extends RecyclerView.ViewHolder{
         ImageView imageView1,imageView2,imageView3,imageView4,item_images;
         CircleImageView circleImageView;
         TextView textView1,textView2;

         public MyViewHolder1(@NonNull View itemView) {
             super(itemView);

             imageView1=itemView.findViewById(R.id.imageView1);
             imageView2=itemView.findViewById(R.id.imageView2);
             imageView3=itemView.findViewById(R.id.imageView3);
             imageView4=itemView.findViewById(R.id.imageView4);
             item_images=itemView.findViewById(R.id.item_images);
             circleImageView=itemView.findViewById(R.id.profile_image);
             textView1=itemView.findViewById(R.id.textView1);
             textView2=itemView.findViewById(R.id.textView2);

         }
     }

     @NonNull
     @Override
     public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         LayoutInflater inflater=getLayoutInflater();
         View view=inflater.inflate(R.layout.item_home,parent,false);




         return  new MyViewHolder1(view);
     }

     @Override
     public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {

         homeMap=homeList.get(position);
        // String title=homeMap.get("title");
         String title=homeMap.get("title");
         String Images_link=homeMap.get("images");

         Picasso.get().load("https://abulk77912.000webhostapp.com/apps/home/"+Images_link).into(holder.item_images);
         holder.textView1.setText(title);
        // holder.textView2.setText(title);
         Picasso.get().load("https://abulk77912.000webhostapp.com/apps/home/"+Images_link).into(holder.circleImageView);




     }

     @Override
     public int getItemCount() {

         return homeList.size();
     }



 }
///========================home itemList end here /////////////////////////////////////

 public void home_itemLode(){
        homeList=new ArrayList<>();

     String homelist_url="https://abulk77912.000webhostapp.com/apps/home_view.php";

     JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, homelist_url, null, new Response.Listener<JSONArray>() {
         @Override
         public void onResponse(JSONArray jsonArray) {
             for (int x=0;x<jsonArray.length();x++){
                 try {
                     JSONObject jsonObject=jsonArray.getJSONObject(x);
                     String images_link=jsonObject.getString("images_link");
                     String title=jsonObject.getString("title");

                     homeMap=new HashMap<>();
                     homeMap.put("images",images_link);
                     homeMap.put("title",title);
                     homeList.add(homeMap);

                 } catch (JSONException e) {
                     throw new RuntimeException(e);
                 }

                 MyAdApter1 myAdApter1=new MyAdApter1();
                 recyclerView2.setAdapter(myAdApter1);
                 recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));



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
 //////////////////////////////////////////
    public void lodData (){

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
                        hashMap.put("images_link",images_link);
                        hashMap.put("title",title);
                        arrayList.add(hashMap);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    MyAdapter myAdapter=new MyAdapter();
                    recyclerView.setAdapter(myAdapter);



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

  //////////////////////////////////////////////
}











///////////////////////////////////
