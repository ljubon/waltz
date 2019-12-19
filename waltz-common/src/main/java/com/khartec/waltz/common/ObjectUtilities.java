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

package com.khartec.waltz.common;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class ObjectUtilities {

    public static <T> T dump(T x) {
        System.out.println("dump: " + x);
        return x;
    }


    @SafeVarargs
    public static <T> T  firstNotNull(T... ts) {
        for (T t : ts) {
            if (t != null) { return t; }
        }
        return null;
    }

    
    @SafeVarargs
    public static <T> boolean any(Predicate<T> pred, T... ts) {
        return Stream
                .of(ts)
                .anyMatch(pred);
    }

}
