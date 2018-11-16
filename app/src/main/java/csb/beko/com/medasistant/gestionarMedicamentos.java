package csb.beko.com.medasistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class gestionarMedicamentos extends Fragment {

    Button boton_agregar;


    public static dashboard newInstance() {
        dashboard fragment = new dashboard();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_gestionar, container, false);

        boton_agregar = v.findViewById(R.id.agregar_gestionar_btn);
        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AgregarMedicamento.class);
                startActivity(i);
            }
        });


        return v;
    }


}
