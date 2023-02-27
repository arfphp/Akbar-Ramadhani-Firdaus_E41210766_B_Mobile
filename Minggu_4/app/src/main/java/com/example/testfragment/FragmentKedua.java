package com.example.testfragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.Fragment;
import androidx.annotation.Nullable;
public class FragmentKedua extends Fragment{
    View view;
    Button tombolKedua;
    @Override
    public View onCreateView(LayoutInflater inlfater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        view = inlfater.inflate(R.layout.fragmentkedua, container, false);
        tombolKedua = view.findViewById(R.id.tombolkedua);
        tombolKedua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fragment Kedua", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
