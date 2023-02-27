package com.example.testfragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.Fragment;
import androidx.annotation.Nullable;

public class FragmentPertama extends Fragment{
    View view;
    Button tombolPertama;
    @Override
    public View onCreateView(LayoutInflater inlfater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        view = inlfater.inflate(R.layout.fragmentpertama, container, false);
        tombolPertama = view.findViewById(R.id.tombolPertama);
        tombolPertama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fragment Pertama", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
