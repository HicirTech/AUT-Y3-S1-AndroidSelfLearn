package com.HirciTech.YemaoChat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ChatListAdapter extends BaseAdapter {
    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private String mDisplayName;
    private ArrayList<DataSnapshot> mDataSnapshots;
    private ChildEventListener mChildEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            mDataSnapshots.add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public ChatListAdapter(Activity activity, DatabaseReference databaseReference, String displayName) {
        mActivity = activity;
        mDatabaseReference = databaseReference.child("messagesA");
        mDisplayName = displayName;
        mDataSnapshots = new ArrayList<>();
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }

    static class viewHolder{
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams Params;
    }
    @Override
    public int getCount() {
        int size = mDataSnapshots.size();
        return size;
    }

    @Override
    public InstandMessage getItem(int i) {
        DataSnapshot snapshot = mDataSnapshots.get(i);
        return snapshot.getValue(InstandMessage.class);//convert back to class
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        /*
         * if no row can use, create new row
         */
        if(view==null)
        {
            //create new row from inflater
            LayoutInflater inflater =
                    (LayoutInflater)mActivity.
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chat_msg_row,viewGroup,false);

            //assgin new row holder information
            final viewHolder holder =new viewHolder();
            holder.authorName =view.findViewById(R.id.author);
            holder.body = view.findViewById(R.id.message);
            holder.Params = (LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
            view.setTag(holder);//put into the view
        }


        //re use row
        final  InstandMessage message = getItem(i);
        final viewHolder holder = (viewHolder)view.getTag();
        holder.authorName.setText(message.getAuthor());
        boolean isAuthor = message.getAuthor().equals(mDisplayName);
        holder.body.setText(message.getMessage());
        this.setChatRowApperance(isAuthor,holder);
        return view;
    }
    public void cleanup()
    {
        this.mDatabaseReference.removeEventListener(mChildEventListener);
    }

    private void setChatRowApperance(boolean isAuthor, viewHolder holder){
        if(isAuthor){
            holder.Params.gravity = Gravity.END;
            holder.authorName.setTextColor(Color.RED);
            holder.body.setBackgroundResource(R.drawable.bubble2);
        }
        else{
            holder.Params.gravity = Gravity.START;
            holder.authorName.setTextColor(Color.BLUE);
            holder.body.setBackgroundResource(R.drawable.bubble1);
        }
        holder.authorName.setLayoutParams(holder.Params);
        holder.body.setLayoutParams(holder.Params);
    }
}
