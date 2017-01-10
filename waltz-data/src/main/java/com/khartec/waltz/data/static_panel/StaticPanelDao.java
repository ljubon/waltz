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

package com.khartec.waltz.data.static_panel;

import com.khartec.waltz.model.staticpanel.ContentKind;
import com.khartec.waltz.model.staticpanel.ImmutableStaticPanel;
import com.khartec.waltz.model.staticpanel.StaticPanel;
import com.khartec.waltz.schema.tables.records.StaticPanelRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.khartec.waltz.schema.tables.StaticPanel.STATIC_PANEL;

@Repository
public class StaticPanelDao {

    private final DSLContext dsl;


    private RecordMapper<Record, StaticPanel> panelMapper = r -> {
        StaticPanelRecord record = r.into(STATIC_PANEL);
        return ImmutableStaticPanel.builder()
                .id(record.getId())
                .content(record.getContent())
                .group(record.getGroup())
                .icon(record.getIcon())
                .encoding(ContentKind.valueOf(record.getEncoding()))
                .title(record.getTitle())
                .priority(record.getPriority())
                .width(record.getWidth())
                .build();
    };


    @Autowired
    public StaticPanelDao(DSLContext dsl) {
        this.dsl = dsl;
    }


    public List<StaticPanel> findByGroups(String... groups) {
        return dsl.select(STATIC_PANEL.fields())
                .from(STATIC_PANEL)
                .where(STATIC_PANEL.GROUP.in(groups))
                .orderBy(STATIC_PANEL.PRIORITY.asc())
                .fetch(panelMapper);
    }
}
