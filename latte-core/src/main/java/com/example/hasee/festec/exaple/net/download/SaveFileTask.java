package com.example.hasee.festec.exaple.net.download;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.hasee.festec.exaple.net.calback.IRequest;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.hasee.festec.exaple.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by hasee on 2017-08-30.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    SaveFileTask(IRequest request, ISuccess success){
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String extension = (String)params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String)params[3];
        final InputStream inputStream = body.byteStream();

        if (downloadDir == null || downloadDir.equals("")){
            downloadDir = "down_loads";
        }

        if(extension == null || extension.equals("")){
            extension = "";
        }

        if (name == null){
            return FileUtil.writeToDisk(inputStream,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(inputStream,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null){
            SUCCESS.OnSuccess(file.getPath());
        }

        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }

        autoInstallApk(file);
    }

    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);

        }
    }
}
