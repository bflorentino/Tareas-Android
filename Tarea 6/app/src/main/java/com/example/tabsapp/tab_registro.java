package com.example.tabsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab_registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab_registro extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText nombre;
    private EditText paswword;
    private TextView txtNombre, txtPassword;
    Button btnSend;
    Context context;

    public tab_registro() {}

    public static tab_registro newInstance(String param1, String param2) {
        tab_registro fragment = new tab_registro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = inflater.getContext();
        return inflater.inflate(R.layout.fragment_tab_registro, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombre = getView().findViewById(R.id.nombrEdit);
        paswword = getView().findViewById(R.id.passEdit);

        btnSend = view.findViewById(R.id.btnEnviar);
        AlertDialog.Builder alertRegistered = new AlertDialog.Builder(getActivity());

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(nombre.getText().toString()) &&
                        !TextUtils.isEmpty(paswword.getText().toString())
                ){
                    alertRegistered.setTitle("Registrado");
                    alertRegistered.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent ventana = new Intent(getActivity(), ActivityWelcome.class);
                                    ventana.putExtra("nombre", nombre.getText().toString());
                                    startActivity(ventana);
                                }
                            });
                    alertRegistered.create();
                    alertRegistered.show();
            }
                else{
                    alertRegistered.setTitle("Es necesario llenar todos los datos!!");
                    alertRegistered.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    });
                    alertRegistered.create();
                    alertRegistered.show();
                }
        }});
    }
}