package pipenatr.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import Clases.DataBases.DBController;
import Clases.Estados.StateController;
import Clases.Principales.Usuario;

import static Clases.Otras.ButtonListenerController.getButtonListenerController;

public class PantallaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DBController controller;
    private StateController stateController;

    
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        controller= DBController.getDBController(this);
        stateController=stateController.getStateController();

        //Modifica nombre y Email de acuerdo al usuario logueado
        Usuario user = controller.findUsuario(Integer.parseInt(SaveSharedPreference.getUserId(this)));
        View headerView = (View) navigationView.getHeaderView(0);
        ((TextView)  headerView.findViewById(R.id.txtEmailHeader)).setText(user.getMail());
        ((TextView) headerView.findViewById(R.id.txtNombreHeader)).setText(user.getNombre());

        //Hace visibles en el drawer las operaciones especificas del usuario
        user.actualizarNavView(navigationView);
    }

    
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        return true;
    }

    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_solicitar_reserva) {
            fragmentManager.beginTransaction().replace(R.id.pantalla_principal, new FormularioReserva()).commit();
        } else if (id == R.id.nav_consultar_solicitud) {
            fragmentManager.beginTransaction().replace(R.id.pantalla_principal, new ConsultarSolicitud()).commit();
        } else if (id == R.id.nav_consultar_asignaciones) {
            fragmentManager.beginTransaction().replace(R.id.pantalla_principal, new ConsultarAsignaciones()).commit();
        } else if (id == R.id.nav_registrar_asignacion) {
            fragmentManager.beginTransaction().replace(R.id.pantalla_principal, new RegistrarAsignacion()).commit();
        } else if (id == R.id.nav_consultar_prestamo) {
            fragmentManager.beginTransaction().replace(R.id.pantalla_principal, new ConsultarPrestamos()).commit();
        } else if (id == R.id.nav_administrar_solicitudes_departamento) {
            fragmentManager.beginTransaction().replace(R.id.pantalla_principal, new ConsultarSolicitud()).commit();
        } else if (id == R.id.nav_cerrar_sesion) {
            SaveSharedPreference.setUserId(PantallaPrincipal.this, "");
            Intent intent = new Intent(this, IniciarSesion.class);
            finish();
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

