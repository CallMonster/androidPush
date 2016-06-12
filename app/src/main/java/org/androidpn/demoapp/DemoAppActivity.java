/*
 * Copyright (C) 2010 Moduad Co., Ltd.
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
package org.androidpn.demoapp;

import org.androidpn.client.NotificationReceiver;
import org.androidpn.client.ServiceManager;
import org.androidpn.impl.PushInfoInterface;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This is an androidpn client demo application.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class DemoAppActivity extends Activity implements PushInfoInterface{

    private TextView pushMsg,pushTitle,pushUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        NotificationReceiver.pushMsgArr.add(this);

        pushMsg= (TextView) findViewById(R.id.pushMsg);
        pushTitle= (TextView) findViewById(R.id.pushTitle);
        pushUrl= (TextView) findViewById(R.id.pushUrl);

        // Settings
        Button okButton = (Button) findViewById(R.id.btn_settings);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ServiceManager.viewNotificationSettings(DemoAppActivity.this);
            }
        });

        // Start the service
        ServiceManager serviceManager = new ServiceManager(this);
        serviceManager.setNotificationIcon(R.drawable.notification);
        serviceManager.startService();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationReceiver.pushMsgArr.remove(this);
    }

    @Override
    public void onPushMsgListener(String notifi_ID, String title, String msg, String uri) {

        pushTitle.setText("title:"+title);
        pushMsg.setText("msg:"+msg);
        pushUrl.setText("uri:"+uri);

    }
}