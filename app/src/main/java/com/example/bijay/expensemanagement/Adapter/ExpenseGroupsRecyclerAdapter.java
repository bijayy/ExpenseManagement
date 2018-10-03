package com.example.bijay.expensemanagement.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bijay.expensemanagement.Models.ExpensesGroupModel;
import com.example.bijay.expensemanagement.R;

import java.util.List;

public class ExpenseGroupsRecyclerAdapter extends RecyclerView.Adapter<ExpenseGroupsRecyclerAdapter.MyViewHolder> {

    private static final String TAG = ExpenseGroupsRecyclerAdapter.class.getSimpleName();

    //Variable to inflate the view out of the "expenses_group_items.xml"
    private LayoutInflater layoutInflater = null;
    private List<ExpensesGroupModel> expensesGroupModels = null;

    public ExpenseGroupsRecyclerAdapter(Context context, List<ExpensesGroupModel> expensesGroupModels) {
        this.layoutInflater = LayoutInflater.from(context);
        this.expensesGroupModels = expensesGroupModels;

        Log.d(TAG, "[ExpenseGroupsRecyclerAdapter] constructor initialization is done, expensesGroupModels: " + this.expensesGroupModels);
    }

    //Getting view and passing that view to MyViewHolder class and returning the MyViewHolder object for recycler view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.expenses_group_items, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        Log.d(TAG, "[onCreateViewHolder] View initialization is done");
        return myViewHolder;
    }

    //Return size of emploee data
    @Override
    public int getItemCount() {
        int size = this.expensesGroupModels.size();

        Log.d(TAG, "[getItemCount] expensesGroupModels size: " + size);
        return size;
    }

    //Binding the view with the data present in the recycler view
    //Setting the listener on each item of the recycler view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ExpensesGroupModel expensesGroupModel = this.expensesGroupModels.get(position);
        holder.sendData(expensesGroupModel);
        holder.setListener();

        Log.d(TAG, "[onBindViewHolder] ViewHolder: "+ holder +" and expense group position: " + position);
    }

    //MyViewHolder class which initializes the Button present in the "recycler_view_item.xml" and assign the data into this.
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvExpenseGroupId;
        TextView tvExpenseGroupName;
        Button editExpenseGroup;
        Button deleteExpenseGroup;

        //Getting button present in the "recycler_view_item.xml"
        MyViewHolder(View viewItem) {
            super(viewItem);
            tvExpenseGroupId = viewItem.findViewById(R.id.tvExpenseGroupId);
            tvExpenseGroupName = viewItem.findViewById(R.id.tvExpenseGroupName);
            editExpenseGroup = viewItem.findViewById(R.id.btnEditExpenseGroup);
            deleteExpenseGroup = viewItem.findViewById(R.id.btnDeleteExpenseGroup);

            Log.d(TAG, "[MyViewHolder] constructor initialization is done");
        }

        //Setting button text present in the "recycler_view_item.xml" with employee name
        void sendData(ExpensesGroupModel expensesGroupModel) {
            tvExpenseGroupId.setText(expensesGroupModel.ID);
            tvExpenseGroupName.setText(expensesGroupModel.GroupName);

            Log.d(TAG, "[sendData] group id: "+ expensesGroupModel.ID +" group name: " + expensesGroupModel.GroupName);
        }

        //Setting click listener on button present in the "recycler_view_item.xml"
        void setListener() {
            editExpenseGroup.setOnClickListener(this);
            deleteExpenseGroup.setOnClickListener(this);

            Log.d(TAG, "[setListener] is done");
        }

        //Displaying Toast on click of button present in the "recycler_view_item.xml"
        @Override
        public void onClick(View view) {
            Toast.makeText(layoutInflater.getContext(), "clicked: ${(v as Button).text}", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "[onClick] is triggered");
        }
    }
}