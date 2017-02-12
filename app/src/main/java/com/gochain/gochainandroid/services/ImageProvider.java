package com.gochain.gochainandroid.services;

import com.gochain.gochainandroid.R;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class ImageProvider {
    private int[] photos = new int[]{
            R.drawable.publicparksmall,
            R.drawable.swimmingpoolsmall,
            R.drawable.youthcentersmall,
            R.drawable.amuseparksmall,
            R.drawable.footballfieldsmall,
            R.drawable.musichallsmall,
            R.drawable.streetlightsmall,
            R.drawable.publiclibrarysmall,
            R.drawable.publicswingssmall,
            R.drawable.collegesmall,
            R.drawable.skateparksmall};

    public ImageProvider() {}

    public int getImage() {
        return photos[ThreadLocalRandom.current().nextInt(0, 11)];
    }

}