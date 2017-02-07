/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gochain.gochainandroid.nfcreader;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.util.Base64;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Callback class, invoked when an NFC tag is scanned while the device is running in reader mode.
 *
 * Reader mode can be invoked by calling NfcAdapter
 */
public class GoChainNfcReader implements NfcAdapter.ReaderCallback {
    private static final String TAG = "GoChainNfcReader";

    // Weak reference to prevent retain loop. mNfcTagCallback is responsible for exiting
    // foreground mode before it becomes invalid (e.g. during onPause() or onStop()).
    private WeakReference<AccountCallback> mNfcTagCallback;

    public interface AccountCallback {
        public void onNfcTokenIdReceived(String account);
    }

    public GoChainNfcReader(AccountCallback accountCallback) {
        mNfcTagCallback = new WeakReference<AccountCallback>(accountCallback);
    }

    /**
     * Callback when a new tag is discovered by the system.
     *
     * <p>Communication with the nfc tag should take place here.
     *
     * @param tag Discovered tag
     */
    @Override
    public void onTagDiscovered(Tag tag) {
        String id = Base64.encodeToString(tag.getId(), Base64.DEFAULT);
        Log.i(TAG, "New tag discovered. ID: " + id);
        mNfcTagCallback.get().onNfcTokenIdReceived(id);
    }

}
