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
package org.finos.waltz.web.json;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.finos.waltz.model.EntityKind;
import org.finos.waltz.model.application.LifecyclePhase;
import org.finos.waltz.model.report_grid.ReportSubject;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableKeyCell.class)
@JsonDeserialize(as = ImmutableKeyCell.class)
public interface KeyCell{


    @Value.Default
    default String type() {
        return ApiTypes.KEYCELL;
    }

    Optional<String> name();

    EntityKind kind();

    Optional<Long> waltzId();

    Optional<String> externalId();

    LifecyclePhase lifecyclePhase();

    static KeyCell fromSubject(ReportSubject subject) {
        return ImmutableKeyCell
                .builder()
                .name(subject.entityReference().name())
                .kind(subject.entityReference().kind())
                .waltzId(subject.entityReference().id())
                .externalId(subject.entityReference().externalId())
                .lifecyclePhase(subject.lifecyclePhase())
                .build();
    }


}
