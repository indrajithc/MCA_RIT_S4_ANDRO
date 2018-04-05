package rit.mca.indran.smsandspeeddial;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACT = 1;
    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;
    private Uri mInfo;
    private Context mContext;
    AutoCompleteTextView number, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (AutoCompleteTextView) findViewById(R.id.numbr);
        message = (AutoCompleteTextView) findViewById(R.id.message);
    }

    public void sms(View view) {

        String numb = String.valueOf(number.getText());
        String mess = String.valueOf(message.getText());


        if (numb.length() != 10) {
            number.setError("invalid mobile number");
            return;
        }

        if (mess.length() < 1) {
            message.setError("required ");
            return;
        }


//        SmsManager.getDefault().sendTextMessage(numb, null, mess, null,null);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numb, null, mess, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void call(View view) {

        try {
//            String number = "12345678";
//            Intent intent = new Intent(Intent.ACTION_CALL);
//            intent.setData(Uri.parse("tel:" + number));
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                 Toast.makeText(this, "require the CALL_PHONE permission ", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            startActivity(intent);

            Intent contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            contactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // to display contacts who have phone number
            startActivityForResult(contactIntent, REQUEST_CONTACT);


        } catch (Exception u){}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CONTACT) {
            if (resultCode == RESULT_OK && data != null) {
                mInfo = data.getData();
                String name = getContactNameAgenda();   // the name you need
                String phone = getContactPhoneNumberAgenda();  //the number you need
                Toast.makeText(mContext, "CONTACT ADDED !", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "CANCELLED OR SOME ERROR OCCURRED !", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private String getContactNameAgenda(){
        Cursor cursor = mContext.getContentResolver().query(mInfo, null,
                null, null, null);

        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        cursor.close();
        return name;
    }

    private String getContactPhoneNumberAgenda(){
        Cursor cursor = mContext.getContentResolver().query(mInfo, new String[]{ContactsContract.Contacts._ID},
                null, null, null);
        cursor.moveToFirst();
        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        cursor.close();

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone._ID + " = ? AND " +  // Phone._ID is for the database ; Phone.CONTACT_ID is for contact when you are not querying that table
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                new String[]{id},
                null);

        cursorPhone.moveToFirst();
        String number = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        cursorPhone.close();
        return number;
    }
}
