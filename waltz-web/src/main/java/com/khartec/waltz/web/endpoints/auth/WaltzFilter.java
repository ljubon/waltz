/*
 * Waltz - Enterprise Architecture
 * Copyright (C) 2016  Khartec Ltd.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.khartec.waltz.web.endpoints.auth;

import com.khartec.waltz.service.settings.SettingsService;
import spark.Filter;

import java.util.Optional;

import static com.khartec.waltz.common.Checks.checkNotNull;


public abstract class WaltzFilter implements Filter {

    private final SettingsService settingsService;


    public WaltzFilter(SettingsService settingsService) {
        checkNotNull(settingsService, "Settings Service cannot be null");
        this.settingsService = settingsService;
    }


    public Optional<String> getSettingValue(String name) {
        return settingsService.getValue(name);
    }


}
