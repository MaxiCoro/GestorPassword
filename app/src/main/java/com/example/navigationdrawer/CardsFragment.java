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

import com.example.navigationdrawer.Database.CardContract;
import com.example.navigationdrawer.Database.CardDbHelper;
import com.example.navigationdrawer.Database.PasswordContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsFragment extends Fragment {

    private ListView listaCards;
    private CardDbHelper dbHelper;
    private List<Map<String, Object>> tarjetas;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cards, container, false);
        listaCards = view.findViewById(R.id.listCards);
        String[] datos = {"titular", "tipo", "nroTarjeta", "fFin"};
        int[] vistas = {R.id.itemListCard_Titular, R.id.itemListCard_Tipo, R.id.itemListCard_NroTarjeta, R.id.itemListCard_FFin};

        dbHelper = new CardDbHelper(getContext());

        SimpleAdapter adaptador = new SimpleAdapter(getContext(), obtenerListadoCards(), R.layout.itemlist_card, datos, vistas);
        listaCards.setAdapter(adaptador);
        listaCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String valor = adaptador.getItem(i).toString();
                Toast.makeText(getActivity(), valor, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private List<Map<String, Object>> obtenerListadoCards(){
        ArrayList listCard = new ArrayList<>();
        Map<String, Object> item;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + CardContract.CardEntry.TABLE_NAME, null);

        cursor.moveToFirst();

        for(int indice = 0; indice < cursor.getCount(); indice++){
            item = new HashMap<>();
            item.put("titular", cursor.getString(1));
            item.put("tipo", cursor.getString(2));
            item.put("nroTarjeta", cursor.getString(3));
            item.put("fFin", cursor.getString(4));

            listCard.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return listCard;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}