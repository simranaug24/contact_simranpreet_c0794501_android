package com.example.contact_simranpreet_c0794501_android.MyAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact_simranpreet_c0794501_android.Model.RowModel;
import com.example.contact_simranpreet_c0794501_android.R;
import com.example.contact_simranpreet_c0794501_android.SQLiteHelper;
import com.example.contact_simranpreet_c0794501_android.UpdateActivity;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> implements Filterable {

    Context context;
    SQLiteHelper helper ;
    ArrayList<RowModel> modelList;
    ArrayList<RowModel> filterList;


    public Adapter(Context context, ArrayList<RowModel> modelList, SQLiteHelper helper ) {
        this.context = context;
        this.modelList = modelList;
        this.helper = helper;
        this.filterList = modelList;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_item,parent ,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String fName = filterList.get(position).getfName();
        String lName = filterList.get(position).getlName();
        String Email = filterList.get(position).getEmail();
        String Phone = filterList.get(position).getPhone();
        String Address = filterList.get(position).getAddress();

        final RowModel model = filterList.get(position);


        holder.row_fname.setText(fName);
        holder.row_lname.setText(lName);
        holder.row_email.setText(Email);
        holder.row_phone.setText(Phone);
        holder.row_address.setText(Address);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(context);
                builder.setTitle(" are you Sure");
                builder.setPositiveButton("YEs", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    long result = helper.DeleteContact(model.getId());
                        if (result != -1)
                        {
                            Toast.makeText(context, "Contact Deleted", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            filterList.remove(position);
                        }

                        else
                        {
                            Toast.makeText(context, "Cannot Delete this Contact", Toast.LENGTH_SHORT).show();
                        }

                    notifyDataSetChanged();


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", model.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charcater = constraint.toString();
                if (charcater.isEmpty()){
                    filterList = modelList ;
                }else {
                    ArrayList<RowModel> newfilterList = new ArrayList<>();
                    for (RowModel row: modelList){
                        if (row.getfName().toLowerCase().contains(charcater.toLowerCase()) || row.getlName().toLowerCase().contains(charcater.toLowerCase())){
                            newfilterList.add(row);
                        }
                    }

                    filterList = newfilterList ;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (ArrayList<RowModel>) results.values ;
                notifyDataSetChanged();
            }
        };
    }

    class MyHolder extends  RecyclerView.ViewHolder
    {
        TextView row_fname,row_lname,row_email,row_phone,row_address;
        Button deleteBtn;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            row_fname = itemView.findViewById(R.id.row_fname);
            row_lname = itemView.findViewById(R.id.row_lname);
            row_email = itemView.findViewById(R.id.row_email);
            row_phone = itemView.findViewById(R.id.row_phone);
            row_address = itemView.findViewById(R.id.row_address);
            deleteBtn = itemView.findViewById(R.id.row_delete_btn);

        }
    }
}
