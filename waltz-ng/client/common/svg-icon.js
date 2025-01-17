import {symbol, symbols,} from "d3-shape";
import _ from "lodash";


function xPath(size) {
    return "M" + (-size / 2) + "," + (-size / 2) +
        "l" + size + "," + size +
        "m0," + -(size) +
        "l" + (-size) + "," + size;
}

function desktop(size) {
    return `M${size * -1 / 8} ${size * 1 / 2} L${size * -3 / 8} ${size * 5 / 8} L${size * 3 / 8} ${size * 5 / 8} L${size * 1 / 8} ${size * 1 / 2} L${size * -1 / 8} ${size * 1 / 2}
        M${size * -1 / 2} ${size * -1 / 2}
         C ${size * -3 / 4} ${size * -1 / 2} ${size * -3 / 4} ${size * -1 / 2} ${size * -3 / 4} ${size * -1 / 4}
         L${size * -3 / 4} ${size * 1 / 4}
         C${size * -3 / 4} ${size * 1 / 2} ${size * -3 / 4} ${size * 1 / 2} ${size * -1 / 2} ${size * 1 / 2}
         L${size * 1 / 2} ${size * 1 / 2}
         C${size * 3 / 4} ${size * 1 / 2} ${size * 3 / 4} ${size * 1 / 2} ${size * 3 / 4} ${size * 1 / 4}
         L${size * 3 / 4} ${size * -1 / 4}
         C${size * 3 / 4} ${size * -1 / 2} ${size * 3 / 4} ${size * -1 / 2} ${size * 1 / 2} ${size * -1 / 2}
         L${size * -1 / 2} ${size * -1 / 2}`
}

function user(size) {
    return `M 0 ${size * -3 / 4}
            A ${size * 1 / 2} ${size * 1 / 2} 0 0 1 0 ${size * 1 / 4}
            A ${size * 1 / 2} ${size * 1 / 2} 0 0 1 0  ${size * -3 / 4}
            M ${size * -3 / 4} ${size * 3 / 4}
            A ${size * 3 / 4} ${size * 2 / 5} 0 0 1 ${size * 3 / 4} ${size * 3 / 4}
            M ${size * -3 / 4} ${size * 3 / 4}
            L ${size * 3 / 4} ${size * 3 / 4}`
}

function folder(size) {
    return `M${size * -1 / 2} ${size * -1 / 2}
         C ${size * -3 / 4} ${size * -1 / 2} ${size * -3 / 4} ${size * -1 / 2} ${size * -3 / 4} ${size * -1 / 4}
         L ${size * -3 / 4} ${size * 1 / 4}
         C ${size * -3 / 4} ${size * 1 / 2} ${size * -3 / 4} ${size * 1 / 2} ${size * -1 / 2} ${size * 1 / 2}
         L ${size * 1 / 2} ${size * 1 / 2}
         C ${size * 3 / 4} ${size * 1 / 2} ${size * 3 / 4} ${size * 1 / 2} ${size * 3 / 4} ${size * 1 / 4}
         L ${size * 3 / 4} ${size * -1 / 4}
         C ${size * 3 / 4} ${size * -1 / 2} ${size * 3 / 4} ${size * -1 / 2} ${size * 1 / 2} ${size * -1 / 2}
         L ${size * -1 / 2} ${size * -1 / 2}
         M 0 ${size * -1 / 2}
         L 0 ${size * -9 / 16}
         C 0 ${size * -10 / 16} 0 ${size * -10 / 16} ${size * -1 / 16} ${size * -10 / 16}
         L ${size * -9 / 16} ${size * -10 / 16}
         C ${size * -10 / 16} ${size * -10 / 16} ${size * -10 / 16} ${size * -10 / 16} ${size * -10 / 16} ${size * -9 / 16}
         L ${size * -10 / 16} ${size * -1 / 2}`
}


function page(size) {
    return `M 0 ${size * -3 / 4}
         L ${size * -3 / 5} ${size * -3 / 4}
         L ${size * -3 / 5} ${size * 3 / 4}
         L ${size * 3 / 5} ${size * 3 / 4}
         L ${size * 3 / 5} ${size * -3 / 8}
         L ${size * 1 / 5} ${size * -3 / 4}
         L 0 ${size * -3 / 4}
         M ${size * 3 / 5} ${size * -3 / 8}
         L ${size * 1 / 5} ${size * -3 / 8}
         L ${size * 1 / 5} ${size * -3 / 4}`
}


function circle(size) {
    return `M 0 ${size * -3 / 4}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0 ${size * 3 / 4}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0  ${size * -3 / 4}`
}


function questionMark(size) {
    return `
            M ${size * -3 / 8} ${size * -3 / 8}
            C 0 ${size * -6 / 8} ${size * 3 / 8} ${size * -1 / 2} ${size * 3 / 8} ${size * -1 / 4}
            S 0 0 0 ${size * 1 / 4}
            M 0 ${size * 1 / 2}
            A ${size * 1 / 16} ${size * 1 / 16} 0 0 1 0 ${size * 5 / 8}
            A ${size * 1 / 16} ${size * 1 / 16} 0 0 1 0 ${size * 1 / 2}
            `
}


function questionCircle(size) {
    return `M 0 ${size * -7 / 8}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0 ${size * 7 / 8}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0  ${size * -7 / 8}
            M ${size * -3 / 8} ${size * -3 / 8}
            C 0 ${size * -6 / 8} ${size * 3 / 8} ${size * -1 / 2} ${size * 3 / 8} ${size * -1 / 4}
            S 0 0 0 ${size * 1 / 4}
            M 0 ${size * 1 / 2}
            A ${size * 1 / 16} ${size * 1 / 16} 0 0 1 0 ${size * 5 / 8}
            A ${size * 1 / 16} ${size * 1 / 16} 0 0 1 0 ${size * 1 / 2}
            `
}


function pages(size) {
    return `M 0 ${size * -3 / 4}
         L ${size * -3 / 5} ${size * -3 / 4}
         L ${size * -3 / 5} ${size * 3 / 4}
         L ${size * 3 / 5} ${size * 3 / 4}
         L ${size * 3 / 5} ${size * -3 / 8}
         L ${size * 1 / 5} ${size * -3 / 4}
         L 0 ${size * -3 / 4}
         M ${size * 3 / 5} ${size * -3 / 8}
         L ${size * 1 / 5} ${size * -3 / 8}
         L ${size * 1 / 5} ${size * -3 / 4}
         M ${size * -3 / 5} ${size * -4 / 8}
         L ${size * -3 / 4} ${size * -4 / 8}
         L ${size * -3 / 4} ${size * 7 / 8}
         L ${size * 2 / 5} ${size * 7 / 8}
         L ${size * 2 / 5} ${size * 3 / 4}`
}


function hourglass(size) {
    return `M ${size * -3 / 4} ${size * -3 / 4}
            L ${size * -3 / 4} ${size * -10 / 16}
            L ${size * 3 / 4} ${size * -10 / 16}
            L ${size * 3 / 4} ${size * -3 / 4}
            L ${size * -3 / 4} ${size * -3 / 4}
            M ${size * -10 / 16} ${size * -10 / 16}
            C ${size * -10 / 16} ${size * -1 / 4} ${size * -1 / 16} ${size * -1 / 4} ${size * -1 / 16} 0
            C ${size * -1 / 16} ${size * 1 / 4} ${size * -10 / 16} ${size * 1 / 4} ${size * -10 / 16} ${size * 10 / 16}
            L ${size * 10 / 16} ${size * 10 / 16}
            C ${size * 10 / 16} ${size * 1 / 4} ${size * 1 / 16} ${size * 1 / 4} ${size * 1 / 16} 0
            C ${size * 1 / 16} ${size * -1 / 4} ${size * 10 / 16} ${size * -1 / 4} ${size * 10 / 16} ${size * -10 / 16}
            L ${size * -10 / 16} ${size * -10 / 16}
            M ${size * -3 / 4} ${size * 3 / 4}
            L ${size * -3 / 4} ${size * 10 / 16}
            L ${size * 3 / 4} ${size * 10 / 16}
            L ${size * 3 / 4} ${size * 3 / 4}
            L ${size * -3 / 4} ${size * 3 / 4}
            M ${size * -10 / 16} ${size * 10 / 16}
            C 0 ${size * 1 / 4} 0 ${size * 1 / 4} ${size * 10 / 16} ${size * 10 / 16}`
}


function leftArrow(size) {
    return `M 0 ${size * -3 / 4}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0 ${size * 3 / 4}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0  ${size * -3 / 4}
            M ${size * 1 / 2} 0
            L ${size * -1 / 2} 0
            M ${size * -1 / 4} ${size * -1 / 4}
            L ${size * -1 / 2} 0
            L ${size * -1 / 4} ${size * 1 / 4}`
}


function rightArrow(size) {
    return `M 0 ${size * -3 / 4}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0 ${size * 3 / 4}
            A ${size * 3 / 4} ${size * 3 / 4} 0 0 1 0  ${size * -3 / 4}
            M ${size * 1 / 2} 0
            L ${size * -1 / 2} 0
            M ${size * 1 / 4} ${size * -1 / 4}
            L ${size * 1 / 2} 0
            L ${size * 1 / 4} ${size * 1 / 4}`
}


function pencil(size) {
    return `
            M ${size * 1 / 2} ${size * -1 / 2}
            L ${size * -10 / 16} ${size * 10 / 16}
            L ${size * -10 / 16} ${size * 14 / 16}
            L ${size * -6 / 16} ${size * 14 / 16}
            L ${size * 3 / 4} ${size * -1 / 4}
            L ${size * 1 / 2} ${size * -1 / 2}
            M ${size * 9 / 16} ${size * -9 / 16}
            L ${size * 11 / 16} ${size * -11 / 16}
            L ${size * 15 / 16} ${size * -7 / 16}
            L ${size * 13 / 16} ${size * -5 / 16}
            L ${size * 9 / 16} ${size * -9 / 16}
            M ${size * -10 / 16} ${size * 12 / 16}
            L ${size * -8 / 16} ${size * 12 / 16}
            L ${size * -8 / 16} ${size * 14 / 16}`
}


function fw(size) {
    return ``;
}

const customSymbolTypes = {
    "cross": xPath,
    "desktop": desktop,
    "user": user,
    "folder": folder,
    "page": page,
    "pages": pages,
    "circle": circle,
    "questionMark": questionMark,
    "questionCircle": questionCircle,
    "hourglass": hourglass,
    "leftArrow": leftArrow,
    "rightArrow": rightArrow,
    "pencil": pencil,
    "fw": fw
};


function constant(x) {
    return function () {
        return x;
    };
}


const customSymbol = function () {

    let type, size = 8;

    function symbol(d, i) {
        const customSymbol = _.get(customSymbolTypes, [type.call(this, d, i)]);
        return customSymbol(size.call(this, d, i));
    }

    symbol.type = function (tp) {
        if (!arguments.length) return type;
        type = typeof tp === "function" ? tp : constant(tp);
        return symbol;
    };

    symbol.size = function (sz) {
        if (!arguments.length) return size;
        size = typeof sz === "function" ? sz : constant(sz);
        return symbol;
    };

    return symbol;
};

export function getSymbol(type, size) {
    size = size || 8;
    if (symbols.indexOf(type) !== -1) {
        return symbol().type(type).size(size)();
    } else {
        return customSymbol().type(type).size(size)();
    }
}
