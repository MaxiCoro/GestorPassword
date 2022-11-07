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

import com.example.navigationdrawer.Database.NoteContract;
import com.example.navigationdrawer.Database.NoteDbHelper;
import com.example.navigationdrawer.Database.PasswordContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotesFragment extends Fragment {

    private ListView listaNota;
    private NoteDbHelper dbHelper;
    private List<Map<String, Object>> notes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        listaNota = view.findViewById(R.id.listNotes);
        String[] datos = {"nombre", "descripcion"};
        int[] vistas = {R.id.itemListNote_Titulo, R.id.itemListNote_Informacion};

        dbHelper = new NoteDbHelper(getActivity());

        SimpleAdapter adaptador = new SimpleAdapter(getActivity(), obtenerListadoNotes(), R.layout.itemlist_note, datos, vistas);
        listaNota.setAdapter(adaptador);
        listaNota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String valor = adaptador.getItem(i).toString();
                Toast.makeText(getActivity(), valor, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private List<Map<String, Object>> obtenerListadoNotes(){
        ArrayList listNotes = new ArrayList<>();
        Map<String, Object> item;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NoteContract.NoteEntry.TABLE_NAME, null);

        cursor.moveToFirst();

        for(int indice = 0; indice < cursor.getCount(); indice++){
            item = new HashMap<>();
            item.put("nombre", cursor.getString(1));
            item.put("descripcion", cursor.getString(2));
            listNotes.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return listNotes;

    }

}
