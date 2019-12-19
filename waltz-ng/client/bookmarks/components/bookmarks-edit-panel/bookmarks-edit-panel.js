/*
 * Waltz - Enterprise Architecture
 * Copyright (C) 2016, 2017, 2018, 2019 Waltz open source project
 * See README.md for more information
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
import template from "./bookmarks-edit-panel.html";
import { initialiseData } from "../../../common/index";
import { CORE_API } from "../../../common/services/core-api-utils";


const bindings = {
    bookmarks: "<",
    onDismiss: "<",
    onReload: "<",
    parentEntityRef: "<"
};


const initialState = {
    showFilter: false,
    visibility: {
        form: false
    },
    selectedBookmark: null
};


function controller(notification, serviceBroker) {
    const vm = initialiseData(this, initialState);

    vm.edit = (b) => {
        vm.selectedBookmark = Object.assign({}, b);
        vm.visibility.form = true;
    };

    vm.create = () => {
        vm.selectedBookmark = {
            bookmarkKind: "DOCUMENTATION",
            lastUpdatedBy: "ignored, server will set"
        };
        vm.visibility.form = true;
    };

    vm.onSave = (b) => {
        b.parent = vm.parentEntityRef;
        serviceBroker
            .execute(CORE_API.BookmarkStore.save, [b])
            .then(() => {
                vm.onReload();
                vm.resetForm();
                notification.success("Updated bookmarks")
            });
    };

    vm.onCancel = () => {
        vm.visibility.form = false;
    };

    vm.resetForm = () => {
        vm.visibility.form = false;
        vm.bookmark = {
            bookmarkKind: "DOCUMENTATION",
            parent: vm.parentEntityRef
        };
    };

    vm.remove = (b) => {
        vm.visibility.form = false;
        if (confirm("Are you sure you want to remove this bookmark ?")) {
            serviceBroker
                .execute(CORE_API.BookmarkStore.remove, [b.id])
                .then(() => {
                    vm.onReload();
                    notification.warning("Removed bookmark");
                });
        }
    };

}


controller.$inject = ["Notification", "ServiceBroker"];


const component = {
    controller,
    template,
    bindings
};


export default {
    id: "waltzBookmarksEditPanel",
    component
};