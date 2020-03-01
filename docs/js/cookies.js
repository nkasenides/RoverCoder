/**
 * Sets a cookie with a specific name, value and expiration time.
 * If a cookie with the same name already exists, the cookie is replaced.
 * @param cname The name of the cookie.
 * @param cvalue The value of the cookie.
 * @param exdays The expiration time of the cookie.
 */
function setCookie(cname, cvalue) {
    var d = new Date();
    d.setTime(d.getTime() + (30*24*60*60*1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

/**
 * Returns a cookie's value or empty string if it does not exist.
 * @param cname The name of the cookie to get.
 * @returns {string}
 */
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
    }//end for
    return "";
}

/**
 * Returns true if cookie exists, false if it does not.
 * @param cname The name of the cookie to check.
 * @returns {boolean}
 */
function cookieExists(cname) {
    if (getCookie(cname) != "") return true; else return false;
}

/**
 * Deletes a given cookie.
 * @param cname The name of the cookie to delete.
 */
function deleteCookie(cname) {
    var cvalue = "";
    var d = new Date();
    d.setTime(d.getTime() - 1);
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}

const COOKIE_LANG = "COOKIE_LANG";
const PLAYER_ID = "PLAYER_ID";
const PLAYER_NAME = "PLAYER_NAME";
const PLAYER_WORKSPACE = "PLAYER_WORKSPACE";