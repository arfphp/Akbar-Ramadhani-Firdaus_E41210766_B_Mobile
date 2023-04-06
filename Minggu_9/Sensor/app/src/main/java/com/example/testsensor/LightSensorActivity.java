package com.example.testsensor;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class LightSensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView txtBrightnessInfo;
    private MediaPlayer mPlayer;
    private GraphView mGraphLight;
    private LineGraphSeries<DataPoint> mSeriesLight;
    private double graphLastAccelXValue = 5d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_light_sensor);
        txtBrightnessInfo = findViewById(R.id.txt_brightness_info);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor == null){
            txtBrightnessInfo.setText("Sensor Tidak Tersedia");
        } else {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }

        mGraphLight = initGraph(R.id.graph_light, "Sensor of Light");
        mSeriesLight = initSeries(Color.RED, "Lux");
        mGraphLight.addSeries(mSeriesLight);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                sensorManager.unregisterListener(this, lightsensor);
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public GraphView initGraph(int id, String title){
        GraphView graph = findViewById(id);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);
//        graph.getGridLabelRenderer().setLabelVerticalWidth(200);
        graph.setTitle(title);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        return graph;
    }

    public LineGraphSeries<DataPoint> initSeries(int color, String title){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        series.setDrawDataPoints(true);
        series.setDrawBackground(true);
        series.setColor(color);
        series.setTitle(title);
        series.setBackgroundColor(Color.argb(100, 204, 119, 119));
        return series;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                txtBrightnessInfo.setText(getResources().getString(R.string.label_brightness, sensorEvent.values[0]));
                graphLastAccelXValue += 0.15d;
                mSeriesLight.appendData(new DataPoint(graphLastAccelXValue, sensorEvent.values[0]), true, 33);
                if(sensorEvent.values[0] == 0) {
                    mPlayer = new MediaPlayer();
                    try {
                        AssetFileDescriptor as = this.getAssets().openFd("cahaya_gelap.mp3");
                        mPlayer.setDataSource(as.getFileDescriptor(), as.getStartOffset(), as.getLength());
                        as.close();
                        mPlayer.prepare();
                        mPlayer.start();


                    } catch (Exception e) {
                        Toast.makeText(this, "Error Playing Music", Toast.LENGTH_SHORT).show();

                    }
                } else if(sensorEvent.values[0] > 150){
                    mPlayer = new MediaPlayer();
                    try {
                        AssetFileDescriptor as = this.getAssets().openFd("cahaya_terang.mp3");
                        mPlayer.setDataSource(as.getFileDescriptor(), as.getStartOffset(), as.getLength());
                        as.close();
                        mPlayer.prepare();
                        mPlayer.start();

                    } catch (Exception e) {
                        Toast.makeText(this, "Error Playing Music", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sensorManager.unregisterListener(this, lightSensor);
    }
}
