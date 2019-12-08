package com.example.asdlo.qrreadertest2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.google.android.gms.vision.barcode.Barcode.QR_CODE;

public class SearchFragment extends Fragment {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    public static String code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search,
                container, false);


        textView = (TextView) view.findViewById(R.id.startCommand);
        surfaceView = (SurfaceView) view.findViewById(R.id.camerapreview);

        barcodeDetector = new BarcodeDetector.Builder(view.getContext()).setBarcodeFormats(QR_CODE).build();

        cameraSource = new CameraSource.Builder(view.getContext(), barcodeDetector).setRequestedPreviewSize(640, 480).setAutoFocusEnabled(true).build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission( view.getContext() , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCode = detections.getDetectedItems();
                if (qrCode.size() != 0) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator) view.getContext().getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(100);
                            code = qrCode.valueAt(0).displayValue;

                            openActiviry2();
                        }
                    });
                }
            }
        });
        return view;
    }

    public void openActiviry2() {
        Intent intent = new Intent(getContext(), Activity2.class);
        startActivity(intent);
    }
}
