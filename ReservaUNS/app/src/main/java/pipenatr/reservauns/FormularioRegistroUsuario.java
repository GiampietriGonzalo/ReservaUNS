package pipenatr.reservauns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Docente;
import pipenatr.Activities.IniciarSesion;
import pipenatr.Activities.R;

public class FormularioRegistroUsuario extends AppCompatActivity {

    TextView inputNombre, inputApellido, inputLegajo, inputTelefono, inputEmail, inputContraseña, inputVerificarContraseña;
    DBController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro_usuario);

        inputNombre = (TextView) findViewById(R.id.input_Nombre);
        inputApellido = (TextView) findViewById(R.id.input_Apellido);
        inputLegajo = (TextView) findViewById(R.id.input_Legajo);
        inputTelefono = (TextView) findViewById(R.id.input_Numero);
        inputEmail = (TextView) findViewById(R.id.input_Email);
        inputContraseña = (TextView) findViewById(R.id.input_password);
        inputVerificarContraseña = (TextView) findViewById(R.id.input_VerifyPassword);

        Button btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

        controller = DBController.getDBController(this);
    }

    private void registrarUsuario()
    {
        View focusView = null;
        boolean error = false;
        String nombre = inputNombre.getText().toString();
        String apellido = inputApellido.getText().toString();
        String legajo = inputLegajo.getText().toString();
        String telefono = inputTelefono.getText().toString();
        String email = inputEmail.getText().toString();
        String contraseña = inputContraseña.getText().toString();
        String verificacionContraseña = inputVerificarContraseña.getText().toString();

        //Verifica que los campos no esten vacios
        LinkedList<TextView> inputs = new LinkedList<TextView>();
        inputs.addLast(inputNombre);
        inputs.addLast(inputApellido);
        inputs.addLast(inputLegajo);
        inputs.addLast(inputTelefono);
        inputs.addLast(inputEmail);
        inputs.addLast(inputContraseña);
        inputs.addLast(inputVerificarContraseña);
        for( int i=0; i<inputs.size(); i++)
        {
            if(inputs.get(i).getText().toString().equals(""))
            {
                inputs.get(i).setError("Este campo no puede estar vacío");
                focusView = inputs.get(i);
                error = true;
            }
        }

        //Verifica que los campos no contengan espacios
        for( int i=0; i<inputs.size(); i++)
        {
            if(inputs.get(i).getText().toString().contains(" "))
            {
                inputs.get(i).setError("Los campos no pueden contener espacios");
                focusView = inputs.get(i);
                error = true;
            }
        }

        if(!error)
        {
            if(!telefono.matches("[0-9]+"))
            {
                inputTelefono.setError("Este campo sólo puede contener números");
                focusView = inputTelefono;
                error = true;
            }

            if(!legajo.matches("[0-9]+"))
            {
                inputLegajo.setError("Este campo sólo puede contener números");
                focusView = inputLegajo;
                error = true;
            }

            if(!contraseña.equals(verificacionContraseña))
            {
                inputVerificarContraseña.setError("Las contraseñas ingresadas no coinciden");
                focusView = inputVerificarContraseña;
                error = true;
            }
        }

        if(error)
            focusView.requestFocus();
        else
        {
            //Docente nuevo = new Docente(13, contraseña, nombre, apellido, Integer.parseInt(legajo), email, telefono);
            //controller.insertDocente(nuevo);
            finish();
        }
    }
}
