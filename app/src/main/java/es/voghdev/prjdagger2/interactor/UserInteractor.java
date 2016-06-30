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
package es.voghdev.prjdagger2.interactor;

import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserInteractor implements Interactor, GetUsers.Listener {
    GetUsers.Listener listener = new NullListener();
    GetUsers dataSource;

    public UserInteractor(GetUsers getUsers) {
        dataSource = getUsers;
    }

    public void getUsers(GetUsers.Listener listener) {
        if(listener != null)
            this.listener = listener;
        this.execute();
    }

    @Override
    public void onUsersReceived(List<User> list, boolean isCached) {
        listener.onUsersReceived(list, isCached);
    }

    @Override
    public void onError(Exception e) {
        listener.onError(e);
    }

    @Override
    public void onNoInternetAvailable() {
        listener.onNoInternetAvailable();
    }

    @Override
    public void execute() {
        dataSource.getAsync(this);
    }

    private class NullListener implements GetUsers.Listener {
        public void onUsersReceived(List<User> list, boolean isCached) {}
        public void onError(Exception e) {}
        public void onNoInternetAvailable() {}
    }
}
