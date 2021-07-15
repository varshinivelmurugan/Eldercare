package com.example.eldercareapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eldercareapp.yAdapter.RecyclerViewAdapter;
import com.example.eldercareapp.Model.Exercise;
import java.util.ArrayList;
import java.util.List;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList =new ArrayList<>();  //list to store the exercises

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    protected void onCreate(Bundle savedInstancestate) {

        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_list_exercises);
        initData();
//internet code few changes made
        recyclerView = (RecyclerView)findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
//code written by myself
    private void initData() {

        exerciseList.add(new Exercise(R.drawable.ashtanga_pose,R.raw.ashtanga_pose,"Ashtanga pose","Step 1 :\n"+"While inhaling, slowly raise the right arm side ways up.\n" +
               "Step 2 :\n" +"Stretch the right arm upwards.\n" +
                "Step 3 :\n" +"While exhaling bend the trunk slowly to the left.\n" +
                "Step 4 :\n" +"The left palm slides down along the left thigh as far as possible."));

        exerciseList.add(new Exercise(R.drawable.spinal_twist_pose,R.raw.spiral_twist_pose,"Spinal Twist pose", "Step 1 :\n" +"Inhale bring both arms straight out infront of you, about chest height.\n" +
                "Step 2 :\n" +"Exhale move your arms to your right. Allow your hips, knees and feet to follow along with the movement. Inhale return to your starting position."));

        exerciseList.add(new Exercise(R.drawable.palm_tree_pose,R.raw.palmtree_pose,"PalmTree pose", "Step 1 \n" +"Stand erect with legs slightly apart with the hands on the sides.\n" +
                "Step 2 :\n" +"Raise the hand above the head and look straight.\n" +
                "Step 3 :\n" +"Interlock the fingers and turn it upwards. The palms should be facing the sky."));

        exerciseList.add(new Exercise(R.drawable.childpose,R.raw.child_pose,"Child pose", "Step 1 :\n" +"Kneel on the floor with your toes together and your knees hip-width apart. Rest your palms on top of your thighs.\n" +
                "Step 3 :\n" +"On an exhale, lower your torso between your knees. Extend your arms alongside your torso with your palms facing down. Relax your shoulders toward the ground. Rest in the pose for as long as needed"));

        exerciseList.add(new Exercise(R.drawable.humming_bee_breathe,R.raw.humming_bee_pose,"Humming Bee Breathe pose", "Step 1 :\n" +"Sit in a quiet and well ventilated corner and close your eyes.\n" +
                "Step 2 :\n" +"Place your index fingers on your ears right at the cartilage.\n" +
                "Step 3 :\n" +"Breathe in and while breathing out press the cartilage with your fingers. Keep the cartilage pressed while making a loud humming sound like a bee"));

        exerciseList.add(new Exercise(R.drawable.corpse_pose,R.raw.corpse_pose,"Corpse pose", "Step 1 :\n" +"Lie in the centre of the mat.\n" +
                "Step 2 :\n" +"Make sure your spine is straight and your shoulders are touching the ground.\n" +
                "Step 3 :\n" +"Keep your arms wide, your palms facing upward and fingers absolutely relaxed.\n" +
                "Step 4 :\n" +"Keep your eyes closed and concentrate on each and every breath."));
    }
}
