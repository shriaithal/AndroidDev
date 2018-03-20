package download.file.app.filedownloader;

import android.os.AsyncTask;

/**
 * Created by Shriaithal on 3/18/2018.
 */

public class DownloadTask extends AsyncTask<String, Integer, Long> {
    @Override
    protected Long doInBackground(String... urls) {
        int count = urls.length;

        return 0L;
    }
}
