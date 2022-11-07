package com.example.navigationdrawer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawer.Database.PasswordContract;
import com.example.navigationdrawer.Database.PasswordDbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PasswordsFragment extends Fragment {

    private ListView listaPassword;
    private PasswordDbHelper dbHelper;
    private List<Map<String, Object>> passwords;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_password, container, false);
        listaPassword = view.findViewById(R.id.listPassword);
        String[] datos = {"nombre", "url", "usuario"};
        int[] vistas = {R.id.itemListPassword_Nombre, R.id.itemListPassword_URL, R.id.itemListPassword_Usuario};

        dbHelper = new PasswordDbHelper(getActivity());

        SimpleAdapter adaptador = new SimpleAdapter(getActivity(), obtenerListadoPasswords(), R.layout.itemlist_password, datos, vistas);

        listaPassword.setAdapter(adaptador);
        listaPassword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String valor = adaptador.getItem(i).toString();
                Toast.makeText(getActivity(), valor, Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    private List<Map<String, Object>> obtenerListadoPasswords(){
        ArrayList listPassword = new ArrayList<>();
        Map<String, Object> item;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + PasswordContract.PasswordEntry.TABLE_NAME, null);

        cursor.moveToFirst();

        for(int indice = 0; indice < cursor.getCount(); indice++){
            item = new HashMap<>();
            item.put("nombre", cursor.getString(1));
            item.put("url", cursor.getString(2));
            item.put("usuario", cursor.getString(3));

            listPassword.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return listPassword;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}