package com.HirciTech.YemaoChat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ChatListAdapter mAdapter;
    private ImageButton mSendButton;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // TODO: Set up the display name and get the Firebase reference
        setupDisplayName();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Send the message when the "enter" button is pressed
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                sendMessage();
                return true;
            }
        });
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        // TODO: Add an OnClickListener to the sendButton to send a message

    }

    // TODO: Retrieve the display name from the Shared Preferences
    private  void setupDisplayName(){
//        SharedPreferences pref = getSharedPreferences(RegisterActivity.DISPLAY_NAME_KEY,MODE_PRIVATE);
//        this.mDisplayName = pref.getString(RegisterActivity.DISPLAY_NAME_KEY,null);
//        if(mDisplayName==null)
//        {
//            mDisplayName="No name";
//        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDisplayName = user.getDisplayName();
    }


    private void sendMessage() {
        System.out.println("Send called");
        String message = mInputText.getText().toString();
        if(message.length()>0)
        {
            InstandMessage chat = new InstandMessage(message,mDisplayName);
            mDatabaseReference.child("messages").push().setValue(chat); //Push to firebase
            mInputText.setText("");
        }
        // TODO: Grab the text the user typed in and push the message to Firebase

    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
    @Override
    public void onStart()
    {
        super.onStart();
        mAdapter=new ChatListAdapter(this,mDatabaseReference,mDisplayName);
        mChatListView.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();

        // TODO: Remove the Firebase event listener on the adapter.

        mAdapter.cleanup();
    }

}
