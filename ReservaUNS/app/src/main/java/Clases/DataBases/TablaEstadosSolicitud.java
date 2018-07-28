package Clases.DataBases;

import android.provider.BaseColumns;

public class TablaEstadosSolicitud implements Tabla {

    private static final String[] columns={Columns.Id, Columns.IdSolicitud , Columns.Tipo};




    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String IdSolicitud = "IdSolicitud";
        public static final String Tipo= "Tipo";
    }


}
