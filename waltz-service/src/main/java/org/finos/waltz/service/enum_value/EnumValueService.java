/*
 * Waltz - Enterprise Architecture
 * Copyright (C) 2016, 2017, 2018, 2019 Waltz open source project
 * See README.md for more information
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific
 *
 */

package org.finos.waltz.service.enum_value;


import org.finos.waltz.data.enum_value.EnumValueDao;
import org.finos.waltz.model.EnumValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.finos.waltz.common.Checks.checkNotNull;

@Service
public class EnumValueService {

    private final EnumValueDao enumValueDao;


    @Autowired
    public EnumValueService(EnumValueDao enumValueDao) {
        checkNotNull(enumValueDao, "enumValueDao cannot be null");
        this.enumValueDao = enumValueDao;
    }


    public List<EnumValue> findAll() {
        return enumValueDao.findAll();
    }

}
