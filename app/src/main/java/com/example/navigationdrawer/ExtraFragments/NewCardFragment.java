package com.example.navigationdrawer.ExtraFragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.navigationdrawer.Database.CardContract;
import com.example.navigationdrawer.Database.CardDbHelper;
import com.example.navigationdrawer.Database.PasswordContract;
import com.example.navigationdrawer.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewCardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CardDbHelper dbHelper;

    String[] meses = {"01 - Enero", "02 - Febrero", "03 - Marzo", "04 - Abril", "05 - Mayo", "06 - Junio", "07 - Julio", "08 - Agosto", "09 - Septiembre", "10 - Octubre", "11 - Noviembre", "12 - Diciembre"};
    Spinner spinnerInicio;
    Spinner spinnerFin;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewCardFragment newInstance(String param1, String param2) {
        NewCardFragment fragment = new NewCardFragment();
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

        View root = inflater.inflate(R.layout.fragment_new_card, container, false);

        dbHelper = new CardDbHelper(getContext());
        spinnerInicio = root.findViewById(R.id.spinnerCard_FInicioMes);
        spinnerInicio.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, meses));

        spinnerFin = root.findViewById(R.id.spinnerCard_FFinMes);
        spinnerFin.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, meses));

        Button btn_Cancelar = root.findViewById(R.id.buttonCard_Cancelar);
        btn_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarInformacion(root);
            }
        });

        Button btn_Guardar = root.findViewById(R.id.buttonCard_Guardar);
        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos(root);
            }
        });

        return root;
    }

    private void guardarDatos(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        String fInicio = "";
        String fFin = "";

        TextInputEditText infoTitular = view.findViewById(R.id.textFieldCard_Titular);
        TextInputEditText infoTipo = view.findViewById(R.id.textFieldCard_Tipo);
        TextInputEditText infoNroTarjeta = view.findViewById(R.id.textFieldCard_NroTarj);
        TextInputEditText infoInicioAnio = view.findViewById(R.id.textFieldCard_FInicioAnio);
        TextInputEditText infoFinAnio = view.findViewById(R.id.textFieldCard_FFinAnio);
        EditText infoExtra = view.findViewById(R.id.textFieldCard_Extra);

        fInicio = "" + ((spinnerInicio.getSelectedItemPosition() + 1) + " / " + infoInicioAnio.getText().toString());
        fFin = "" + ((spinnerFin.getSelectedItemPosition() + 1) + " / " + infoFinAnio.getText().toString());
        valores.put(CardContract.CardEntry.COLUMN_NAME_TITULAR, infoTitular.getText().toString());
        valores.put(CardContract.CardEntry.COLUMN_NAME_TIPO, infoTipo.getText().toString());
        valores.put(CardContract.CardEntry.COLUMN_NAME_NROTARJETA, infoNroTarjeta.getText().toString());
        valores.put(CardContract.CardEntry.COLUMN_NAME_FINICIO, fInicio);
        valores.put(CardContract.CardEntry.COLUMN_NAME_FFIN, fFin);
        valores.put(CardContract.CardEntry.COLUMN_NAME_EXTRA, infoExtra.getText().toString());

        long newRowId = db.insert(CardContract.CardEntry.TABLE_NAME, null, valores);

        if (newRowId != -1){
            Toast.makeText(this.getContext(), "Tarjeta guardada", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.getContext(), "Error al guardar", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiarInformacion(View view){
        TextInputEditText infoTitular = view.findViewById(R.id.textFieldCard_Titular);
        TextInputEditText infoTipo = view.findViewById(R.id.textFieldCard_Tipo);
        TextInputEditText infoNroTarjeta = view.findViewById(R.id.textFieldCard_NroTarj);
        TextInputEditText infoInicioAnio = view.findViewById(R.id.textFieldCard_FInicioAnio);
        TextInputEditText infoFinAnio = view.findViewById(R.id.textFieldCard_FFinAnio);
        EditText infoExtra = view.findViewById(R.id.textFieldCard_Extra);

        infoTitular.setText("");
        infoTipo.setText("");
        infoNroTarjeta.setText("");
        infoInicioAnio.setText("");
        infoFinAnio.setText("");
        infoExtra.setText("");
        spinnerInicio.setSelection(0);
        spinnerFin.setSelection(0);
    }
}