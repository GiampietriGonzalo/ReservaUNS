package pipenatr.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import Clases.DataBases.DBController;
import Clases.Principales.Usuario;

public class SaveSharedPreference
{
    //Clase pa mantener el usuario loguea3

    static final String PREF_USER_ID= "id";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserId(Context ctx, String userId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, userId);
        editor.commit();
    }

    public static String getUserId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }
}
