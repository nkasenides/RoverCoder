//IMPORTANT: Requires cookies.js to be imported first.

const GREEK = "GR";
const ENGLISH = "EN";

let selectedLanguage = GREEK;

function initLanguage() {
    selectedLanguage = getCookie(COOKIE_LANG);
    if (selectedLanguage == null || selectedLanguage === "") {
        setLanguage(GREEK, false);
    }
}

function setLanguage(language, refresh) {
    if (language !== selectedLanguage) {
        setCookie(COOKIE_LANG, language);
        selectedLanguage = language;
        if (refresh) {
            location.reload();
        }
    }
}

function getString(name, removeAccents) {
    switch (selectedLanguage) {
        case GREEK:
            const strGr = Reflect.get(LANG, name);
            let strGrValue = Reflect.get(strGr, "gr");
            if (removeAccents) {
                strGrValue = strGrValue.replace("ά", "α");
                strGrValue = strGrValue.replace("ί", "ι");
                strGrValue = strGrValue.replace("έ", "ε");
                strGrValue = strGrValue.replace("ύ", "υ");
                strGrValue = strGrValue.replace("ό", "ο");
                strGrValue = strGrValue.replace("ή", "η");
                strGrValue = strGrValue.replace("ώ", "ω");
            }
            return strGrValue;
        case ENGLISH:
            const strEn = Reflect.get(LANG, name);
            return Reflect.get(strEn, "en");
        default:
            return "#MISSING#";
    }
}

function writeString(name, removeAccents) {
    let string = getString(name);
    if (removeAccents) {
        string = string.replace("ά", "α");
        string = string.replace("ί", "ι");
        string = string.replace("έ", "ε");
        string = string.replace("ύ", "υ");
        string = string.replace("ό", "ο");
        string = string.replace("ή", "η");
        string = string.replace("ώ", "ω");
    }
    document.write(string);
}

function getCity(enumName) {
    switch (enumName) {
        case "NICOSIA":
            return getString("NICOSIA");
        case "LIMASSOL":
            return getString("LIMASSOL");
        case "LARNACA":
            return getString("LARNACA");
        case "PAPHOS":
            return getString("PAPHOS");
        case "AMMOCHOSTOS":
            return getString("AMMOCHOSTOS");
    }
}

const LANG = {

    TEXT_FRONT_PAGE: {
        gr: "Πως τηλεχειριζόμαστε ένα ρομποτικό όχημα στον Άρη που βρίσκεται περισσότερο από 50 εκατομμύρια χιλιόμετρα μακριά; Η απάντηση είναι με προγραμματισμό. Μάθετε πως οι ρομποτικοί αυτοί εξερευνητές μπορούν να προγραμματιστούν με εντολές οι οποίες μεταδίδονται και εκτελούνται από το όχημα για την επίτευξη ενός στόχου. Χρησιμοποιώντας μιαγραφική γλώσσα προγραμματισμού και τη δημιουργικότητα σας, στείλτε τις εντολές σας σε ένα ρομπότ και παρακολουθήστε το να εξερευνά το άγνωστο τοπίο του Άρη και φέρτε εις πέρας την αποστολή σας.",
        en: "How do you telecontrol a robotic vehicle on Mars some 50 million kilometers from our planet earth? The answer is coding. Learn how these robotic explorers can be programmed with a sequence of commands which are communicated and executed on the vehicle to achieve a specific goal. Using a graphical programming language and your own creativity, send your code to a real robot and watch it as it explores the unknown landscape on Mars and complete your mission."
    },
    PLAY_NOW: {
        gr: "Ας προγραμματίσουμε!",
        en: "Let's program!"
    },
    PLAYER_NAME_PLACEHOLDER: {
        gr: "Εισάγετε το όνομά σας...",
        en: "Enter your name..."
    },
    ENTER_YOUR_NAME_INSTRUCTION: {
        gr: "Πληκτρολογήστε το όνομά σας παρακάτω για να ξεκινήσετε.",
        en: "Enter your name below to start playing."
    }

};