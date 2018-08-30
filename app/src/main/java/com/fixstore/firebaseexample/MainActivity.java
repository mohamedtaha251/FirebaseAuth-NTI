package com.fixstore.firebaseexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etAddress;
    Button btnAdd;
    User user;
    Phone p;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference referenc;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.et_name);
        etAddress = (EditText) findViewById(R.id.et_address);
        btnAdd = (Button) findViewById(R.id.btn_add);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        referenc = database.getReference("UserData");
        user = new User();
        p = new Phone();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();

                user.setName(name);
                p.setHome("0123245665");
                p.setMobile("041245454");
                user.setAddress(address);
                // user.setPhone(new Phone("0224410487", "01117178454"));

                DatabaseReference userref = referenc.child("user1");
                userref.child("name").setValue("taha");
                userref.child("address").setValue("cairo");

                DatabaseReference phoneref = userref.child("phone");
                phoneref.child("home").setValue("0224410487");
                phoneref.child("mobile").setValue("01117178455");

                referenc.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      //  User user = dataSnapshot.getValue(User.class);
                       // Toast.makeText(MainActivity.this, user.getName() + user.getAddress(), Toast.LENGTH_SHORT).show();

                        for (int i=0;i<dataSnapshot.getChildrenCount();i++)
                        {
                            User user=dataSnapshot.getValue(User.class);
                            String s=dataSnapshot.child("user1").child("name").getValue(String.class);
                            Toast.makeText(MainActivity.this, user.getName(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }
}
