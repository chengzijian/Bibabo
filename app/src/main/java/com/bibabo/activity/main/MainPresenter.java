/*
 * Copyright (c) 2017 SamuelGjk <samuel.alva@outlook.com>
 *
 * This file is part of DiyCode
 *
 * DiyCode is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DiyCode is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DiyCode. If not, see <http://www.gnu.org/licenses/>.
 */

package com.bibabo.activity.main;

import com.bibabo.base.mvp.BasePresenterImpl;

/**
 * Created by SamuelGjk on 2017/3/25.
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    @Override
    public void fetchMe() {
//        if (!UserUtils.isAlreadyLogin()) {
//            return;
//        }
//
//        LaiHouDefaultRetrofit.api().fetchMe(UserUtils.getAccessToken())
//                .compose(view.<UserInfoResult>bindToLife())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<UserInfoResult>() {
//                    @Override
//                    public void accept(@NonNull UserInfoResult user) throws Exception {
//                        Cache.addUserInfo(user);
//                        EventBus.getDefault().post(user.getData());
//                        view.updateMe(user.getData());
//                    }
//                });
    }
}
