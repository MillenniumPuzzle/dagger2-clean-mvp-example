/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.prjdagger2.global.datasource.datafile;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.datasource.GetUsers;
import es.voghdev.prjdagger2.global.datasource.api.GetUsersResponse;
import es.voghdev.prjdagger2.global.datasource.api.rest.model.UserApiEntry;
import es.voghdev.prjdagger2.global.model.User;

public class GetUsersFileImpl extends GetUsers {
    private Context mContext = null;

    public GetUsersFileImpl(Context applicationContext){
        mContext = applicationContext;
    }

    @Override
    public void getUsers() {

        try {
            final List<User> users = parseUsersFromRawFile(R.raw.users);

            // Wait 3s to simulate a real load process
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onUsersListReceived(users);
                }
            }, 3000);

        }catch(IOException e){
            listener.onUsersListError(e);
        }
    }

    private List<User> parseUsersFromRawFile(int resId) throws IOException {
        InputStream inputStream = mContext.getResources().openRawResource(resId);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int x = -1;
        byte[] buffer = new byte[1024];
        while((x = inputStream.read(buffer, 0, buffer.length)) != -1){
            bos.write(buffer, 0, x);
        }
        inputStream.close();

        String json = bos.toString();
        return getUsersFromJson(json);

    }

    private List<User> getUsersFromJson(String json){
        GetUsersResponse response = new Gson().fromJson(json, GetUsersResponse.class);
        List<User> users = new ArrayList<User>();
        for(UserApiEntry entry :  response.getResults()){
            users.add(entry.parseUser());
        }
        return users;
    }
}
