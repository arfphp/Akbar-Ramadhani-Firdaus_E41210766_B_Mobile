package com.example.testmaps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.testmaps.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//    final private int REQUEST_COURSE_ACCESS = 123;
//    LocationManager lm;
//    boolean permissionGranted;
//    LocationListener locationListener;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


//        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new MyLocationListener();
    }


    public boolean isZoomControlsEnabled() {
        return true;
    }

    public void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18)); // Menggerakkan kamera ke titik yang diklik
                            MarkerOptions mo = new MarkerOptions();
                            mo.position(latLng);
                            mo.title("You're Here");
                            mMap.addMarker(mo);
                            String latitude = String.valueOf(latLng.latitude);
                            String longitude = String.valueOf(latLng.longitude);
                            Toast.makeText(MapsActivity.this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Cek apakah izin lokasi sudah diberikan atau belum
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true); // Mengaktifkan layer lokasi
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); // Meminta izin lokasi
        }

        getMyLocation();

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {

            @Override
            public boolean onMyLocationButtonClick() {
                mMap.clear();
                getMyLocation();
                return false;
            }
        });

        // Menambahkan listener pada map ketika diklik
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear(); // Menghapus marker sebelumnya
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17)); // Menggerakkan kamera ke titik yang diklik
                MarkerOptions mo = new MarkerOptions();
                mo.position(latLng);
                mo.title(latLng.latitude + " : " + latLng.longitude);
                mMap.addMarker(mo);
                String latitude = String.valueOf(latLng.latitude);
                String longitude = String.valueOf(latLng.longitude);
                Toast.makeText(MapsActivity.this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_SHORT).show(); // Menampilkan toast dengan koordinat yang diklik
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            getMyLocation();
        }
    }
}