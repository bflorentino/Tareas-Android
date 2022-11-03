package com.example.tabsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;

public class tab_canciones_favoritas extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String[] links = {"https://youtu.be/u3A-tKLIH1I",
            "https://www.youtube.com/watch?v=APVDlnuldsQ&t=2s",
            "https://www.youtube.com/watch?v=Fms6bXpqF2k",
            "https://www.youtube.com/watch?v=ay5QoTMsN_s",
            "https://www.youtube.com/watch?v=l7bSRVO8fuQ"
    };

    private String[] videosNombres = {"7 Extensiones de Visual Studio Code",
            "Mitos en Programación",
            "¿Qué es un programador Backend Junior?",
            "5 Tips con Group By en SQL",
            "Entrevista Técnica de Programador Junior"};

    private ListView listaVideos;

    public tab_canciones_favoritas() {}

    public static tab_canciones_favoritas newInstance(String param1, String param2) {
        tab_canciones_favoritas fragment = new tab_canciones_favoritas();
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
        return inflater.inflate(R.layout.fragment_tab_canciones_favoritas, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, videosNombres);

        listaVideos = getView().findViewById(R.id.list_element);
        listaVideos.setAdapter(adapter);

        listaVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ventana = new Intent(Intent.ACTION_VIEW, Uri.parse(links[i]));
                startActivity(ventana);
            }
        });
    }
}