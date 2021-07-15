package com.example.eldercareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.*;

/*-----code written by me(syntax referred from internet)-----*/

public class performanceGraph extends AppCompatActivity {

    AnyChartView anyChartView;
    String[] games = {"Guess Words","Guess Color","Card matching"};
    float winning_percentage[]=new float[3];
    DBHelper db;
    long mobno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_graph);
        mobno=getIntent().getExtras().getLong("mobno");

        winning_percentage[0]=getIntent().getExtras().getFloat("wordPercentage");
        winning_percentage[1]=getIntent().getExtras().getFloat("colorPercentage");
        winning_percentage[2]=getIntent().getExtras().getFloat("cardPercentage");
        db=new DBHelper(this);

        anyChartView = findViewById(R.id.any_chart_view);
        setupPieChart();
    }

    public void setupPieChart(){


        String game;
        if(winning_percentage[0] < winning_percentage[1] && winning_percentage[0] < winning_percentage[2])
        {
            game="Guess Words";
        }
        else if(winning_percentage[1] < winning_percentage[2])
        {
            game="Guess Color";
        }
        else
        {
            game="Card Matching";
        }
        TextView concentrate = (TextView)findViewById(R.id.concentrate);
        concentrate.setText("Concentrate more on "+game);

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i=0;i<games.length;i++)
        {
            dataEntries.add(new ValueDataEntry(games[i],winning_percentage[i]));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
        System.out.println(winning_percentage[0]+"word"+winning_percentage[1]+"color"+winning_percentage[2]+"card");

            System.out.println(mobno);
            doctormsg();


    }
    public void doctormsg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                sendSMSMessage();
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }
    }
    protected void sendSMSMessage() {

        try {

            System.out.println("sendsems");
            String[] doctor = { "Hema Karthick Ph.no:9387271738 ","Kannan Ph.no:6374829364 ","Suresh Kumar Ph.no:8746372777 ","John Ph.no:7584977343 ","Kesavan Ph.no:9994446667" };

            List<String> doctorlist = Arrays.asList(doctor);

            Collections.shuffle(doctorlist);

            doctorlist.toArray(doctor);
            System.out.println(doctor[1]);
            String str;
            if(winning_percentage[0]<=33.33&&winning_percentage[1]<=33.33&&winning_percentage[2]<=33.33){
                 str="Your performance in elder care app is low.Don't worry we will guide you to doctor. " +
                    "You may have disease like Alzheimer's Disease, dementia, anxiety, bipolar disorder and schizophrenia. " +
                    "So Consult Dr."+doctor[1];}
                else{
                    str="!!!!!!AWESOME!!!!!!.You have performed very well in elder care app." +
                            "Keep practicing our app's activities.Incase if you need any doctor consultation, you can contact Dr."+doctor[1];;
                }
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> msgparts = smsManager.divideMessage(str);
            smsManager.sendMultipartTextMessage(String.valueOf(mobno), null, msgparts, null, null);
           // smsManager.sendTextMessage(String.valueOf(mobno), null,"CONSULT POPULAR PSYCHOTHERAPIST Dr.MATHIVANAN,Phoneno:9893212398 ", null, null);
            Toast.makeText(this, "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "SMS failed, please try again.", Toast.LENGTH_LONG).show();
        }

    }
}