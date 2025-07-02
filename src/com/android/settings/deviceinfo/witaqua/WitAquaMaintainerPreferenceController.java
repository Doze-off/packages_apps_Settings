/*
 * Copyright (C) 2025 The LineageOS Project
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

package com.android.settings.deviceinfo.witaqua;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.preference.Preference;

import com.android.settings.core.BasePreferenceController;

public class WitAquaMaintainerPreferenceController extends BasePreferenceController {

    private static final String KEY_WITAQUA_BUILD_STATUS_PROP = "ro.witaqua.build.status";
    private static final String KEY_WITAQUA_MAINTAINER_PROP = "ro.witaqua.maintainer";

    public WitAquaMaintainerPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        String maintainer = SystemProperties.get(KEY_WITAQUA_MAINTAINER_PROP, "Unknown").trim();
        String buildStatus = SystemProperties.get(KEY_WITAQUA_BUILD_STATUS_PROP, "Unknown").trim();

        if (!TextUtils.isEmpty(buildStatus) && !TextUtils.isEmpty(maintainer)) {
            return buildStatus + " by " + maintainer;
        }

        return "Unknown";
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setSummary(getSummary());
    }
}
