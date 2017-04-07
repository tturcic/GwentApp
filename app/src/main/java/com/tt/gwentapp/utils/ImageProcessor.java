package com.tt.gwentapp.utils;

import android.graphics.Bitmap;
import android.support.annotation.WorkerThread;

import java.util.Arrays;

import rx.Observable;

/**
 * @author tturcic
 *         \date 7.4.2017.
 */
public class ImageProcessor {

    public ImageProcessor(){}

    /**
     * Trims transparent pixels from a bitmap.
     * Should be run on background thread.
     * @param originalBitmap - bitmap to trim.
     * @return - trimmed bitmap.
     */
    @WorkerThread
    public Observable<Bitmap> trimBitmap(Bitmap originalBitmap){
        // crop bitmap to non-transparent area and return:
        return Observable.defer(() -> {
            int height = originalBitmap.getHeight();
            int width = originalBitmap.getWidth();
            int[] empty = new int[width];
            int[] buffer = new int[width];
            Arrays.fill(empty,0);
            int top = 0;
            int left = 0;
            int bottom = height;
            int right = width;

            for (int y = 0; y < height; y++) {
                originalBitmap.getPixels(buffer, 0, width, 0, y, width, 1);
                if (!Arrays.equals(empty, buffer)) {
                    top = y;
                    break;
                }
            }

            for (int y = height - 1; y > top; y--) {
                originalBitmap.getPixels(buffer, 0, width, 0, y, width, 1);
                if (!Arrays.equals(empty, buffer)) {
                    bottom = y;
                    break;
                }
            }

            int bufferSize = bottom -top +1;
            empty = new int[bufferSize];
            buffer = new int[bufferSize];
            Arrays.fill(empty,0);

            for (int x = 0; x < width; x++) {
                originalBitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize);
                if (!Arrays.equals(empty, buffer)) {
                    left = x;
                    break;
                }
            }

            for (int x = width - 1; x > left; x--) {
                originalBitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize);
                if (!Arrays.equals(empty, buffer)) {
                    right = x;
                    break;
                }
            }

            return Observable.just(Bitmap.createBitmap(originalBitmap, left, top, right - left, bottom - top));
        });
    }
}
