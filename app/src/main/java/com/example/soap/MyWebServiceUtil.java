package com.example.soap_tp3;

import android.util.Log;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MyWebServiceUtil {
    // Données nécessaires pour se connecter à un service SOAP
    // Récupérables à partir du fichier wsdl
    public static final String wsdl_url = "https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL";
    public static final String name_space = "http://www.dataaccess.com/webservicesserver/";
    public static final String methode_name = "NumberToWords";
    public static final String soap_action = "https://www.dataaccess.com/webservicesserver/numberconversion.wso?op=NumberToWords";

    public static String getData(int nombre) {
        String resultat = "DONNEE :";

        // Créer un objet du protocole SOAP
        SoapObject soapObject = new SoapObject(name_space, methode_name);

        // Spécifier la donnée d'entrée
        soapObject.addProperty("ubiNum", nombre);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

        // En cas d'un service .net (asmx)
        // envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);

        // Envoyer la requête
        HttpTransportSE httpTransport = new HttpTransportSE(wsdl_url);

        try {
            httpTransport.call(soap_action, envelope);
            SoapObject soapResult = (SoapObject) envelope.bodyIn;
            resultat = soapResult.getProperty(0).toString();
            Log.d("RESULTAT :", resultat);
        } catch (Exception e) {
            Log.d("erreur connection :", e.toString());
            e.printStackTrace();
        }

        return resultat;
    }
}
