package Clases.DataBases;

import android.provider.BaseColumns;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaPrestamos implements Tabla {

    private static String[] columns={Columns.ID, Columns.nameCol, Columns.locationCol, Columns.telephoneCol, Columns.desiredCol, Columns.reputationCol,Columns.votesCol, Columns.qualityCol,Columns.typeCol, Columns.sectorCol};




    rotected class Columns implements BaseColumns {

        public static final String ID= "id";
        public static final String tableName = "Prestamos";
        public static final String nameCol = "Name";
        public static final String locationCol = "Location";
        public static final String telephoneCol= "Telephone";
        public static final String desiredCol= "Desireds";
        public static final String reputationCol= "Reputation";
        public static final String votesCol="Votes";
        public static final String typeCol="Type";
        public static final String qualityCol="Quality";
        public static final String sectorCol="Sector";

    }

}
