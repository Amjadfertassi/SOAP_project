package com.example.soap

import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView

public class MainActivity extends AppCompatActivity {
    EditText txtnbr;
    String restultat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnbr=(EditText)findViewById(R.id.txtnbr);
    }
    public void getNombreEnLetters(View view) {
        new MaTacheAsyn().execute(txtnbr.getText().toString());
    }
    //Pour se connecter à intenet nous devons utiliser un autre thread que celui du UI
    class MaTacheAsyn extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            int donnee;
            try {
                donnee=Integer.parseInt(strings[0]);
            }
            catch (NumberFormatException e){
                donnee=0;
            }
            return MyWebServiceUtil.getData(donnee);
        }
        @Override
        protected void onPostExecute(String o) {
            TextView lblRes;
            lblRes=(TextView)findViewById(R.id.lblResultat);
            lblRes.setText(o.toString());
        }
    }
}