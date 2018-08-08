package comcater.employeemanagement.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Utilz {
    static ProgressDialog dialog;
    public static boolean isInternetConnected(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    public static boolean isValidEmail1(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();
        }
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            // check if no view has focus:
            View view = activity.getCurrentFocus();
            if (view != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            // Ignore exceptions if any
            Log.e("KeyBoardUtil", e.toString(), e);
        }
    }


    public static int getDateFromString(String dateStr) {
        int date = 0;
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = DATE_FORMAT.parse(dateStr);
            date = parsedDate.getDate();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    public static void displayMessageAlert(String Message, Context context) {
        try {
            new AlertDialog.Builder(context).setMessage(Message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            }).create().show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static void logDisplay(String Message, Context context) {
        if (Message != null) {
            if (Message.trim().length() > 0 && context != null) {
                Log.d("PJ", Message);
            }
        }
    }
    public static void HideSoftkeyPad(EditText textfiled, Context c) {
        InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textfiled.getWindowToken(), 0);
    }

    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getCurrentTime() {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
        return currentDateTimeString;
    }

    public static String getCurrentDateInDigit() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
    public static void showProgress(Context context, String message) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        dialog = ProgressDialog.show(context, null, message, true, true);

    }

    public static void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

}

