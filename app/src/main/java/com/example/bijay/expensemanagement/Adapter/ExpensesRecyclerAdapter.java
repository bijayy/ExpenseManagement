package com.example.bijay.expensemanagement.Adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.PersonSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.ExpensesModel;
import com.example.bijay.expensemanagement.Models.PersonModel;
import com.example.bijay.expensemanagement.R;

import java.util.List;

public class ExpensesRecyclerAdapter extends RecyclerView.Adapter<ExpensesRecyclerAdapter.MyViewHolder> {

    private static final String TAG = ExpensesRecyclerAdapter.class.getSimpleName();

    //Variable to inflate the view out of the "expenses_group_items.xml"
    private LayoutInflater layoutInflater = null;
    private List<ExpensesModel> expensesModels = null;
    private Context context;

    public ExpensesRecyclerAdapter(Context context, List<ExpensesModel> expensesModels) {
        this.layoutInflater = LayoutInflater.from(context);
        this.expensesModels = expensesModels;
        this.context = context;

        Log.d(TAG, "[ExpensesRecyclerAdapter] constructor initialization is done, expensesModels: " + this.expensesModels);
    }

    //Getting view and passing that view to MyViewHolder class and returning the MyViewHolder object for recycler view
    @Override
    public ExpensesRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.expenses_details_list, parent, false);
        ExpensesRecyclerAdapter.MyViewHolder myViewHolder = new ExpensesRecyclerAdapter.MyViewHolder(view);

        Log.d(TAG, "[onCreateViewHolder] View initialization is done");
        return myViewHolder;
    }

    //Return size of emploee data
    @Override
    public int getItemCount() {
        int size = this.expensesModels.size();

        Log.d(TAG, "[getItemCount] expensesModels size: " + size);
        return size;
    }

    //Binding the view with the data present in the recycler view
    //Setting the listener on each item of the recycler view
    @Override
    public void onBindViewHolder(ExpensesRecyclerAdapter.MyViewHolder holder, int position) {
        ExpensesModel expensesGroupModel = this.expensesModels.get(position);
        holder.sendData(expensesGroupModel);
        holder.setListener();

        Log.d(TAG, "[onBindViewHolder] ViewHolder: "+ holder +" and expense position: " + position);
    }

    //MyViewHolder class which initializes the Button present in the "recycler_view_item.xml" and assign the data into this.
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvExpenseId;
        TextView tvExpenseDate;
        TextView tvByWhom;
        TextView tvForWhom;
        TextView tvProductName;
        TextView tvAmount;
        TextView tvPurpose;

        Button btnEditExpense;
        Button btnDeleteExpense;

        //Getting button present in the "recycler_view_item.xml"
        MyViewHolder(View viewItem) {
            super(viewItem);
            tvExpenseId = viewItem.findViewById(R.id.tvExpenseId);
            tvExpenseDate = viewItem.findViewById(R.id.tvExpenseDate);
            tvByWhom = viewItem.findViewById(R.id.tvByWhom);
            tvForWhom = viewItem.findViewById(R.id.tvForWhom);
            tvProductName = viewItem.findViewById(R.id.tvProductName);
            tvAmount = viewItem.findViewById(R.id.tvAmount);
            tvPurpose = viewItem.findViewById(R.id.tvPurpose);

            btnEditExpense = viewItem.findViewById(R.id.btnEditExpense);
            btnDeleteExpense = viewItem.findViewById(R.id.btnDeleteExpense);

            Log.d(TAG, "[MyViewHolder] constructor initialization is done");
        }

        //Setting button text present in the "recycler_view_item.xml" with employee name
        void sendData(ExpensesModel expensesModel) {
            tvExpenseId.setText(expensesModel.ID + "");
            tvExpenseDate.setText(expensesModel.Date);
            tvByWhom.setText(getPersonsById(expensesModel.ByWhom));
            if(expensesModel.ByWhom.equals(expensesModel.ForWhom)){
                tvForWhom.setText("Self");
            }
            else {
                tvForWhom.setText(getPersonsById(expensesModel.ForWhom));
            }
            tvProductName.setText(expensesModel.ProductName);
            tvAmount.setText(expensesModel.Amount);
            tvPurpose.setText(expensesModel.Purpose);

            Log.d(TAG, "[sendData] expense id: "+ expensesModel.ID);
        }

        //Setting click listener on button present in the "recycler_view_item.xml"
        void setListener() {
            btnEditExpense.setOnClickListener(this);
            btnDeleteExpense.setOnClickListener(this);

            Log.d(TAG, "[setListener] is done");
        }

        //Displaying Toast on click of button present in the "recycler_view_item.xml"
        @Override
        public void onClick(View view) {
            Toast.makeText(layoutInflater.getContext(), "clicked: ${(v as Button).text}", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "[onClick] is triggered");
        }
    }

    private String getPersonsById(String id) {
        PersonSqliteDatabaseAdapter personSqliteDatabaseAdapter = new PersonSqliteDatabaseAdapter(context);
        PersonModel personModel = personSqliteDatabaseAdapter.getPersonById(id);

        Log.d(TAG, "[getPersonsById] Person Id: "+ personModel.ID +" fetched successfully");
        return personModel.ID +". "+ personModel.Name;
    }
}