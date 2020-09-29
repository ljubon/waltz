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

function store($http, baseUrl) {

    const BASE = `${baseUrl}/report-grid`;


    const getViewById = (id, selectionOptions) => {
        return $http
            .post(`${BASE}/view/id/${id}`, selectionOptions)
            .then(result => result.data);
    };


    return {

        getViewById
    };
}


store.$inject = [
    "$http",
    "BaseApiUrl"
];


const serviceName = "ReportGridStore";


export const ReportGridStore_API = {
    getViewById: {
        serviceName,
        serviceFnName: "getViewById",
        description: "executes getViewById [gridId, selectionOptions]"
    }
};


export default {
    serviceName,
    store
};