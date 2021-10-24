package com.example.capstoneproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.capstoneproject.Common.Common;
import com.example.capstoneproject.Interface.ItemClickListener;
import com.example.capstoneproject.Model.Order;
import com.example.capstoneproject.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;

public class OrderStatus extends AppCompatActivity {


    public RecyclerView recyclerView;

    FirebaseRecyclerAdapter<Order,OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference orders;

    String selectedFilter = "all";

    Query collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        //Firebase
        database = FirebaseDatabase.getInstance("https://capstoneproject-c2dbe-default-rtdb.asia-southeast1.firebasedatabase.app");
        orders = database.getReference("Order");

        recyclerView = findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadOrders(Common.currentUser.getCustID());
        adapter.notifyDataSetChanged();


    }

    private void loadOrders(String phone) {
        adapter = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(
                Order.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                orders.orderByChild("custID").equalTo(Common.currentUser.getCustID())
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder orderViewHolder, Order order, int i) {
                orderViewHolder.txtOrderId.setText(String.format("Order # ")+ adapter.getRef(i).getKey());
                orderViewHolder.txtOrderStatus.setText(convertCodeToStatus(order.getStatus()));
                orderViewHolder.txtOrderPrice.setText(order.getOrderPrice());
                orderViewHolder.txtOrderType.setText(String.format("Order Type: ") + order.getOrderType());
                orderViewHolder.txtOrderDate.setText(Common.getDate(Long.parseLong(adapter.getRef(i).getKey())));

                if(convertCodeToStatus(order.getStatus()).equals("Placed")) {
                    orderViewHolder.txtOrderStatus.setTextColor(Color.parseColor("#29B438"));
                } else if ((convertCodeToStatus(order.getStatus()).equals("Delivering")) || (convertCodeToStatus(order.getStatus()).equals("Ready to Pickup"))) {
                    orderViewHolder.txtOrderStatus.setTextColor(Color.parseColor("#EAA825"));
                } else if (convertCodeToStatus(order.getStatus()).equals("Completed")) {
                    orderViewHolder.txtOrderStatus.setTextColor(Color.parseColor("#EE1010"));
                } else {
                    orderViewHolder.txtOrderStatus.setTextColor(Color.parseColor("#080808"));
                }

                orderViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent orderDetail = new Intent(OrderStatus.this, OrderDetail.class);
                        Common.currentOrder = order;
                        orderDetail.putExtra("OrderId",adapter.getRef(position).getKey());
                        startActivity(orderDetail);
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }
    private String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "Preparing";
        else if(status.equals("2"))
            return "Delivering";
        else if(status.equals("3"))
            return "Ready to Pickup";
        else if(status.equals("4"))
            return "Completed";
        else
            return "Cancelled";
    }

    private void filterList(String status) {
        selectedFilter = status;

    }

    public void allFilterTapped(View view) {
    }

    public void processingFilterTapped(View view) {
    }

    public void completedFilterTapped(View view) {
    }

    public void cancelledFilterTapped(View view) {
    }
}