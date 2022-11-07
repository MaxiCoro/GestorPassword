package com.example.navigationdrawer.ExtraFragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.navigationdrawer.Database.PasswordContract;
import com.example.navigationdrawer.Database.PasswordDbHelper;
import com.example.navigationdrawer.PasswordsFragment;
import com.example.navigationdrawer.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPasswordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PasswordDbHelper dbHelper;
    private FragmentTransaction transaction;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewPasswordFragment newInstance(String param1, String param2) {
        NewPasswordFragment fragment = new NewPasswordFragment();
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

        View root = inflater.inflate(R.layout.fragment_new_password, container, false);

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        dbHelper = new PasswordDbHelper(this.getContext());
        Button btnCancelar = root.findViewById(R.id.buttonPasswordCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordsFragment fragment = new PasswordsFragment();
                transaction.replace(R.id.fragmentHolder, fragment);
                transaction.commit();
            }
        });

        Button btnGuardar = root.findViewById(R.id.buttonPasswordGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos(root);
                PasswordsFragment fragment = new PasswordsFragment();
                transaction.replace(R.id.fragmentHolder, fragment);
                transaction.commit();
            }
        });

        return root;
    }


    private void guardarDatos(View view){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        TextInputEditText infoURL = view.findViewById(R.id.textFieldPass_URL);
        TextInputEditText infoNombre = view.findViewById(R.id.textFieldPass_Nombre);
        TextInputEditText infoUsuario = view.findViewById(R.id.textFieldPass_Usuario);
        TextInputEditText infoContra = view.findViewById(R.id.textFieldPass_Password);

        valores.put(PasswordContract.PasswordEntry.COLUMN_NAME_NOMBRE, infoNombre.getText().toString());
        valores.put(PasswordContract.PasswordEntry.COLUMN_NAME_URL, infoURL.getText().toString());
        valores.put(PasswordContract.PasswordEntry.COLUMN_NAME_USUARIO, infoUsuario.getText().toString());
        valores.put(PasswordContract.PasswordEntry.COLUMN_NAME_PASSWORD, infoContra.getText().toString());

        long newRowId = db.insert(PasswordContract.PasswordEntry.TABLE_NAME, null, valores);

        if (newRowId != -1){
            Toast.makeText(this.getContext(), "Contraseña guardada", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.getContext(), "Error al guardar", Toast.LENGTH_LONG).show();
        }

    }
}