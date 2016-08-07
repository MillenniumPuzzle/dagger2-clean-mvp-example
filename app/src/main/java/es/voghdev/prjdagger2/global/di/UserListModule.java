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
package es.voghdev.prjdagger2.global.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.ui.presenter.AbsUserListPresenter;
import es.voghdev.prjdagger2.ui.presenter.impl.UserListPresenter;

@Module
public class UserListModule {

    private final Context mContext;

    public UserListModule(final Context context) {
        mContext = context;
    }

    @Provides
    AbsUserListPresenter provideUserListPresenter(){
        return new UserListPresenter(mContext);
    }
}
