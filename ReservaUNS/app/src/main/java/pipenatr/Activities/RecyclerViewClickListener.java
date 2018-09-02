package pipenatr.Activities;

public interface RecyclerViewClickListener {

    public boolean cancelarSolicitud(int position);
    public boolean eliminarSolicitud(int position);
    public boolean aceptarSolicitud(int position);
    public boolean rechazarSolicitud(int position);
}
