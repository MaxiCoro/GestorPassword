package com.example.navigationdrawer.ExtraFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigationdrawer.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewNoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewNoteFragment newInstance(String param1, String param2) {
        NewNoteFragment fragment = new NewNoteFragment();
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

        View root = inflater.inflate(R.layout.fragment_new_note, container, false);

        Button btn_Cancelar = root.findViewById(R.id.buttonCancelar);
        btn_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarInformacion(root);
            }
        });

        Button btn_Guardar = root.findViewById(R.id.buttonGuardar);
        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos(root);
            }
        });

        return root;
    }

    private void limpiarInformacion(View view){
        TextInputEditText infoNombre = view.findViewById(R.id.textFieldNote_Nombre);
        TextInputEditText infoDescripcion = view.findViewById(R.id.textFieldNote_Informacion);

        infoNombre.setText("");
        infoDescripcion.setText("");
    }

    private void guardarDatos(View view){
        TextInputEditText infoNombre = view.findViewById(R.id.textFieldNote_Nombre);
        TextInputEditText infoDescripcion = view.findViewById(R.id.textFieldNote_Informacion);

        String nombre = infoNombre.getText().toString();
        String descripcion = infoDescripcion.getText().toString();
    }
}