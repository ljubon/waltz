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

package com.khartec.waltz.data.logical_flow;

import com.khartec.waltz.data.IdSelectorFactory;
import com.khartec.waltz.data.application.ApplicationIdSelectorFactory;
import com.khartec.waltz.data.data_type.DataTypeIdSelectorFactory;
import com.khartec.waltz.model.EntityKind;
import com.khartec.waltz.model.IdSelectionOptions;
import org.jooq.Condition;
import org.jooq.Record1;
import org.jooq.Select;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.khartec.waltz.common.Checks.checkNotNull;
import static com.khartec.waltz.schema.tables.DataFlowDecorator.DATA_FLOW_DECORATOR;
import static com.khartec.waltz.schema.tables.LogicalFlow.LOGICAL_FLOW;


@Service
public class LogicalFlowIdSelectorFactory implements IdSelectorFactory {


    private final ApplicationIdSelectorFactory applicationIdSelectorFactory;
    private final DataTypeIdSelectorFactory dataTypeIdSelectorFactory;


    @Autowired
    public LogicalFlowIdSelectorFactory(ApplicationIdSelectorFactory applicationIdSelectorFactory,
                                        DataTypeIdSelectorFactory dataTypeIdSelectorFactory) {
        checkNotNull(applicationIdSelectorFactory, "applicationIdSelectorFactory cannot be null");
        checkNotNull(dataTypeIdSelectorFactory, "dataTypeIdSelectorFactory cannot be null");

        this.applicationIdSelectorFactory = applicationIdSelectorFactory;
        this.dataTypeIdSelectorFactory = dataTypeIdSelectorFactory;
    }


    @Override
    public Select<Record1<Long>> apply(IdSelectionOptions options) {
        checkNotNull(options, "options cannot be null");
        switch (options.entityReference().kind()) {
            case APPLICATION:
                return mkForApplication(options);
            case APP_GROUP:
                return wrapAppIdSelector(options);
            case CAPABILITY:
                return wrapAppIdSelector(options);
            case DATA_TYPE:
                return mkForDataType(options);
            case MEASURABLE:
                return wrapAppIdSelector(options);
            case ORG_UNIT:
                return wrapAppIdSelector(options);
            case PERSON:
                return wrapAppIdSelector(options);
            case PROCESS:
                return wrapAppIdSelector(options);

            default:
                throw new UnsupportedOperationException("Cannot create physical specification selector from options: " + options);
        }
    }

    private Select<Record1<Long>> wrapAppIdSelector(IdSelectionOptions options) {
        Select<Record1<Long>> appIdSelector = applicationIdSelectorFactory.apply(options);

        Condition sourceCondition = LOGICAL_FLOW.SOURCE_ENTITY_ID.in(appIdSelector)
                .and(LOGICAL_FLOW.SOURCE_ENTITY_KIND.eq(EntityKind.APPLICATION.name()));

        Condition targetCondition = LOGICAL_FLOW.TARGET_ENTITY_ID.in(appIdSelector)
                .and(LOGICAL_FLOW.TARGET_ENTITY_KIND.eq(EntityKind.APPLICATION.name()));

        return DSL.select(LOGICAL_FLOW.ID)
                .from(LOGICAL_FLOW)
                .where(sourceCondition.or(targetCondition));
    }


    private Select<Record1<Long>> mkForApplication(IdSelectionOptions options) {
        ensureScopeIsExact(options);
        long appId = options.entityReference().id();
        return DSL.select(LOGICAL_FLOW.ID)
                .from(LOGICAL_FLOW)
                .where(LOGICAL_FLOW.SOURCE_ENTITY_ID.eq(appId)
                        .and(LOGICAL_FLOW.SOURCE_ENTITY_KIND.eq(EntityKind.APPLICATION.name())))
                .or(LOGICAL_FLOW.TARGET_ENTITY_ID.eq(appId)
                        .and(LOGICAL_FLOW.TARGET_ENTITY_KIND.eq(EntityKind.APPLICATION.name())));
    }


    private Select<Record1<Long>> mkForDataType(IdSelectionOptions options) {
        Select<Record1<Long>> dataTypeSelector = dataTypeIdSelectorFactory.apply(options);

        return DSL.select(DATA_FLOW_DECORATOR.DATA_FLOW_ID)
                .from(DATA_FLOW_DECORATOR)
                .where(DATA_FLOW_DECORATOR.DECORATOR_ENTITY_ID.in(dataTypeSelector)
                        .and(DATA_FLOW_DECORATOR.DECORATOR_ENTITY_KIND.eq(EntityKind.DATA_TYPE.name())));

    }
}
