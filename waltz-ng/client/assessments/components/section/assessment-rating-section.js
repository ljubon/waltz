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

import {initialiseData} from "../../../common";

import template from "./assessment-rating-section.html";
import AssessmentRatingList from "../list/AssessmentRatingList.svelte";
import AssessmentRatingEditor from "../rating-editor/AssessmentRatingEditor.svelte";
import {primaryEntityReference} from "../rating-editor/rating-store";

const bindings = {
    parentEntityRef: "<",
};


const initialState = {
    AssessmentRatingList,
    AssessmentRatingEditor
};


function controller() {

    const vm = initialiseData(this, initialState);

    vm.$onChanges = () => {
        primaryEntityReference.set(vm.parentEntityRef);
    };
}


controller.$inject = [
    "$q",
    "ServiceBroker"
];


const component = {
    template,
    bindings,
    controller
};


export default {
    component,
    id: "waltzAssessmentRatingSection"
};
