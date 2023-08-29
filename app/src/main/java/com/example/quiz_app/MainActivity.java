package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Modelclass> listOfQ;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfQ = new ArrayList<>();
        dr = FirebaseDatabase.getInstance().getReference("Questions");

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Modelclass modelclass = dataSnapshot.getValue(Modelclass.class);
                    listOfQ.add(modelclass);
                }
                Intent intent = new Intent(MainActivity.this,dashboardActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*listOfQ.add(new Modelclass("What is the name of first British to visit India?", "Hawkins", "Norway", "Devid", "George Bush", "Hawkins"));
        listOfQ.add(new Modelclass("Name of the first election commissioner of India?", "Sukumar Sen", "R.N. Shukla", "V.R. Gill", "D.B. Mahawar", "Sukumar Sen"));
        listOfQ.add(new Modelclass("Name of the first university of India?", "Nalanda University", "Taxshila University", "Jawahar University", "Dronacharya University", "Nalanda University"));
        listOfQ.add(new Modelclass("Where is India's First nuclear centre?", "Jaipur", "Kanpur", "Tarapur", "Raipur", "Tarapur"));
        listOfQ.add(new Modelclass("Name of First Indian Missile?", "HAL Tejas", "Prithvi", "SEPECAT", "Sukhoi", "Prithvi"));
        listOfQ.add(new Modelclass("What is the national flower of India?", "Rose", "Lotus", "Tulip", "Sunflower", "Lotus"));
        listOfQ.add(new Modelclass("Who was the first President of India?", "Jawaharlal Nehru", "Sardar Patel", "Rajendra Prasad", "Indira Gandhi", "Rajendra Prasad"));
        listOfQ.add(new Modelclass("Which Mughal emperor built the Taj Mahal?", "Akbar", "Jahangir", "Aurangzeb", "Shah Jahan", "Shah Jahan"));
        listOfQ.add(new Modelclass("What is the capital city of India?", "Mumbai", "New Delhi", "Kolkata", "Bangalore", "New Delhi"));
        listOfQ.add(new Modelclass("In which year did India gain independence from British rule?", "1942", "1947", "1950", "1965", "1947"));


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this,dashboardActivity.class);
            startActivity(intent);
        },2000);*/
    }
}