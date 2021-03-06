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

import dagger.Component;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.ui.activity.BaseActivity;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;

@Component(modules = MainModule.class)
public interface RootComponent {

    void inject(BaseActivity activity); // BaseActivity can inject dependencies from this Component

    void inject(App application);

    void inject(UserListPresenter presenter); // UserListPresenter can inject dependencies from this Component
}