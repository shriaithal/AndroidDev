package pin.cert.app.certificatepinning;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String TRUST_STORE_PASSWORD = "cmpe277!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void certificatePin(View view) {

        try {
            AsyncCertPin task = new AsyncCertPin(((TextView)findViewById(R.id.textView)));
            task.execute();
        } catch (Exception e) {

        }
    }

    private class AsyncCertPin extends AsyncTask<Void, Void, PublicKey> {

        TextView publicKeyView;

        public AsyncCertPin(TextView publicKeyView) {
            this.publicKeyView = publicKeyView;
        }
        @Override
        protected PublicKey doInBackground(Void... params) {
            try {
                URL url = new URL("https://www.google.com");
                PublicKey publicKey = makeRequest(getBaseContext(), url);
                return publicKey;

            } catch (Exception e) {
                Log.e("CertPin", e.getMessage(), e);
            }
            return null;
        }

        protected void onPostExecute(PublicKey publicKey) {
            String text = "";
            if(null == publicKey) {
                text = "Invalid Certificate";
            }else {
                text = publicKey.toString();
            }
            publicKeyView.setText(text);
        }
    }

    private PublicKey makeRequest(Context context, URL url) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException {
        AssetManager assetManager = context.getAssets();
        InputStream keyStoreInputStream = context.getResources().openRawResource(R.raw.keystore);

        KeyStore trustStore = KeyStore.getInstance("BKS");
        trustStore.load(keyStoreInputStream, "cmpe277!".toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
        trustManagerFactory.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());

        InputStream inputStream = urlConnection.getInputStream();

        Certificate[] certificates = urlConnection.getServerCertificates();
        PublicKey publicKey = certificates[1].getPublicKey();
        return publicKey;
    }
}
