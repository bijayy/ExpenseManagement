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
import com.example.bijay.expensemanagement.Data.Sqlite.Adapter.PersonSqliteDatabaseAdapter;
import com.example.bijay.expensemanagement.Models.PersonModel;
import com.example.bijay.expensemanagement.R;

import java.util.List;

public class PersonsRecyclerAdapter extends RecyclerView.Adapter<PersonsRecyclerAdapter.MyViewHolder> {

    private static final String TAG = PersonsRecyclerAdapter.class.getSimpleName();

    //Variable to inflate the view out of the "expenses_group_items.xml"
    private LayoutInflater layoutInflater = null;
    private List<PersonModel> personModelList = null;

    public PersonsRecyclerAdapter(Context context, List<PersonModel> personModelList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.personModelList = personModelList;

        Log.d(TAG, "[PersonsRecyclerAdapter] constructor initialization is done, personList: " + this.personModelList);
    }

    //Getting view and passing that view to MyViewHolder class and returning the MyViewHolder object for recycler view
    @Override
    public PersonsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.persons_details_list, parent, false);
        PersonsRecyclerAdapter.MyViewHolder myViewHolder = new PersonsRecyclerAdapter.MyViewHolder(view);

        Log.d(TAG, "[onCreateViewHolder] View initialization is done");
        return myViewHolder;
    }

    //Return size of emploee data
    @Override
    public int getItemCount() {
        int size = this.personModelList.size();

        Log.d(TAG, "[getItemCount] people size: " + size);
        return size;
    }

    //Binding the view with the data present in the recycler view
    //Setting the listener on each item of the recycler view
    @Override
    public void onBindViewHolder(PersonsRecyclerAdapter.MyViewHolder holder, int position) {
        PersonModel personModel = this.personModelList.get(position);
        holder.sendData(personModel);
        holder.setListener();

        Log.d(TAG, "[onBindViewHolder] ViewHolder: " + holder + " and person position: " + position);
    }

    //MyViewHolder class which initializes the Button present in the "recycler_view_item.xml" and assign the data into this.
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvGroupIdName;
        TextView tvPersonIdName;
        TextView tvPersonMobileNumber;
        TextView tvPersonEmail;

        Button btnEditPerson;
        Button btnDeletePerson;

        //Getting button present in the "recycler_view_item.xml"
        MyViewHolder(View viewItem) {
            super(viewItem);
            tvGroupIdName = viewItem.findViewById(R.id.tvGroupIdName);
            tvPersonIdName = viewItem.findViewById(R.id.tvPersonIdName);
            tvPersonMobileNumber = viewItem.findViewById(R.id.tvPersonMobileNumber);
            tvPersonEmail = viewItem.findViewById(R.id.tvPersonEmail);
            btnEditPerson = viewItem.findViewById(R.id.btnEditPerson);
            btnDeletePerson = viewItem.findViewById(R.id.btnDeletePerson);

            Log.d(TAG, "[MyViewHolder] constructor initialization is done");
        }

        //Setting button text present in the "recycler_view_item.xml" with employee name
        void sendData(PersonModel personModel) {
            tvGroupIdName.setText(personModel.ExpenseGroupModel.ID + ". " + personModel.ExpenseGroupModel.GroupName);
            tvPersonIdName.setText(personModel.ID + ". " + personModel.Name);
            tvPersonMobileNumber.setText(personModel.MobileNumber);
            tvPersonEmail.setText(personModel.Email);

            Log.d(TAG, "[sendData] person name: " + personModel.Name + " fetched successfully");
        }

        //Setting click listener on button present in the "recycler_view_item.xml"
        void setListener() {
            btnEditPerson.setOnClickListener(this);
            btnDeletePerson.setOnClickListener(this);

            Log.d(TAG, "[setListener] is done");
        }

        //Displaying Toast on click of button present in the "recycler_view_item.xml"
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnEditPerson:
                    PersonSqliteDatabaseAdapter personSqliteDatabaseAdapter = new PersonSqliteDatabaseAdapter(layoutInflater.getContext());
                    PersonModel personModel = new PersonModel();
                    personModel.ID = getLayoutPosition();

                    //xpensesGroupSqliteDatabaseAdapter.updateExpensesGroupById();

                    //Toast.makeText(layoutInflater.getContext(), "", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "[onClick][btnEditPerson] item position: " + getLayoutPosition());
                    break;

                case R.id.btnDeletePerson:


                    Toast.makeText(layoutInflater.getContext(), "clicked: ${(v as Button).text}", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Log.d(TAG, "[onClick][default] Not Implemented");
                    break;
            }

            Log.d(TAG, "[onClick] is triggered");
        }
    }
}
