package pipenatr.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegistrarAsignacion extends Fragment {

    private View myView;
    private TextView[] diasReserva;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.formulario_reserva, container, false);

        myView.findViewById(R.id.layoutRS).setVisibility(myView.GONE);
        /*myView.findViewById(R.id.spinnerEdificios).setVisibility(myView.GONE);
        myView.findViewById(R.id.formTxtEsp).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerTiposEspacio).setVisibility(myView.GONE);
        myView.findViewById(R.id.formCantDias).setVisibility(myView.GONE);
        */

        return myView;
    }
}
