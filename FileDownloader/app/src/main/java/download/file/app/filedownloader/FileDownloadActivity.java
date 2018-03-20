package download.file.app.filedownloader;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;

public class FileDownloadActivity extends AppCompatActivity {

    DownloadBindService mService;
    boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_download);

        EditText pdfURL1 = (EditText) findViewById(R.id.pdf1);
        EditText pdfURL2 = (EditText) findViewById(R.id.pdf2);
        EditText pdfURL3 = (EditText) findViewById(R.id.pdf3);
        EditText pdfURL4 = (EditText) findViewById(R.id.pdf4);
        EditText pdfURL5 = (EditText) findViewById(R.id.pdf5);

        pdfURL1.setText("https://www.cisco.com/c/dam/en_us/about/annual-report/2016-annual-report-full.pdf");
        pdfURL2.setText("https://www.cisco.com/c/dam/en_us/solutions/industries/docs/gov/everything-for-cities.pdf");
        pdfURL3.setText("https://www.cisco.com/c/dam/en_us/about/ac79/docs/innov/IoE_Economy.pdf");
        pdfURL4.setText("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_execsummary_en_xg.pdf");
        pdfURL5.setText("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_en_xg.pdf");

    }


    /*@Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, DownloadBindService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, DownloadBindService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConnection);
        mBound = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
        mBound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        mBound = false;
    }*/

    public void startDownload(View view) {
        Intent intent = new Intent(getBaseContext(), DownloadIntentService.class);

        //Intent intent = new Intent(getBaseContext(), DownloadBindService.class);
        try {
            String[] urls = new String[5];
            urls[0] = "https://www.cisco.com/c/dam/en_us/about/annual-report/2016-annual-report-full.pdf";
            urls[1] = "https://www.cisco.com/c/dam/en_us/solutions/industries/docs/gov/everything-for-cities.pdf";
            urls[2] = "https://www.cisco.com/c/dam/en_us/about/ac79/docs/innov/IoE_Economy.pdf";
            urls[3] = "http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_execsummary_en_xg.pdf";
            urls[4] = "http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_en_xg.pdf";
            intent.putExtra("URLs", urls);

            startService(intent);

            /*if (mBound) {
                mService.downloadFiles(intent);
            }*/
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            DownloadBindService.DownloadBinder binder = (DownloadBindService.DownloadBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
