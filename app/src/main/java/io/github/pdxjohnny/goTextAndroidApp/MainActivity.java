/*
 * Copyright 2015 The Go Authors. All rights reserved.
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file.
 */

package io.github.pdxjohnny.goTextAndroidApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import go.hello.Hello;


public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        final String myPackageName = getPackageName();
//        if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
//            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, myPackageName);
//            startActivity(intent);
//        }
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.mytextview);

        // Call Go function.
        Hello.StartWeb();
        String greetings = Hello.Greetings("Android and Gopher");
        mTextView.setText(greetings);
    }
}
