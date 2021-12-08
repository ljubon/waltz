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

package com.khartec.waltz.integration_test.inmem.dao;

import com.khartec.waltz.data.actor.ActorDao;
import com.khartec.waltz.integration_test.inmem.BaseInMemoryIntegrationTest;
import com.khartec.waltz.integration_test.inmem.helpers.LogicalFlowHelper;
import com.khartec.waltz.model.EntityKind;
import com.khartec.waltz.model.actor.Actor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.khartec.waltz.integration_test.inmem.helpers.NameHelper.mkName;
import static com.khartec.waltz.model.EntityReference.mkRef;
import static org.junit.Assert.*;


public class ActorDaoTest extends BaseInMemoryIntegrationTest {

    @Autowired
    private ActorDao dao;

    @Autowired
    private LogicalFlowHelper logicalFlowHelper;

    @Test
    public void actorsCanBeCreated() {
        String name = mkName("actorsCanBeCreated");
        Long id = createActor(name);

        Actor retrieved = dao.getById(id);
        assertEquals(name, retrieved.name());
        assertEquals(name + " Desc", retrieved.description());
        assertTrue(retrieved.isExternal());
    }


    @Test
    public void actorsCanBeDeletedIfNotUsed() {
        int preCount = dao.findAll().size();
        Long id = createActor(mkName("canBeDeletedTest"));

        System.out.println("After creation: "+ dao.findAll());
        boolean deleted = dao.deleteIfNotUsed(id);

        assertTrue("Actor should be deleted as not used in flows", deleted);
        assertEquals("After deletion the count of actors should be the same as before the actor was added", preCount, dao.findAll().size());
    }


    @Test
    public void actorsCannotBeDeletedIfUsed() {
        Long idA = createActor(mkName("cannotBeDeletedActorA"));
        Long idB = createActor(mkName("cannotBeDeletedActorB"));

        logicalFlowHelper.createLogicalFlow(
                mkRef(EntityKind.ACTOR, idA),
                mkRef(EntityKind.ACTOR, idB));

        int preCount = dao.findAll().size();
        boolean wasDeleted = dao.deleteIfNotUsed(idA);

        assertFalse("Actor should not be deleted as used in a flow", wasDeleted);
        assertEquals("After attempted deletion the count of actors should be the same", preCount, dao.findAll().size());
    }

}