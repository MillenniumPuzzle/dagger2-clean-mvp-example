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

import es.voghdev.prjdagger2.global.datasource.GetUsers;

public class UserInteractor extends GetUsers implements Interactor, GetUsers.Listener {

    GetUsers dataSource;

    public UserInteractor(GetUsers getUsers) {
        dataSource = getUsers;
    }

    @Override
    public void execute() {
        dataSource.get(this);
    }

    @Override
    public void onResultsReceived(List list) {
        listener.onResultsReceived(list);
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
    protected void get() {
        this.execute();
    }
}
