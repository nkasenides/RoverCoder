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

const LANG = {

    HOMEPAGE: {
        gr: "Πίσω στην αρχική",
        en: "Back to home page"
    },
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
    },
    SCOREBOARD: {
        gr: "Αποτελέσματα",
        en: "Scoreboard"
    },
    PLAY_AGAIN: {
        gr: "Προγραμμάτισε το Rover",
        en: "Program the rover"
    },
    ERROR_GETTING_SCOREBOARD: {
        gr: "Σφάλμα κατά τη λήψη του πίνακα αποτελεσμάτων.",
        en: "Error getting scoreboard."
    },
    ERROR_GETTING_QUEUE: {
        gr: "Σφάλμα κατά τη λήψη του πίνακα σειράς.",
        en: "Error getting code queue."
    },
    PLAYER_NAME: {
        gr: "Όνομα παίκτη",
        en: "Player name"
    },
    POINTS: {
        gr: "Βαθμοί",
        en: "Points"
    },
    QUEUE_NUM: {
        gr: "Σειρά",
        en: "Turn"
    },
    QUEUE: {
        gr: "Σειρά εκτέλεσης",
        en: "Queue"
    },
    NO_SCORES: {
        gr: "Δεν υπάρχουν αποτελέσματα.",
        en: "There are no scores to show."
    },
    NO_CODES: {
        gr: "Δεν υπάρχουν κώδικες για εκτέλεση.",
        en: "There are no codes to execute."
    },
    NO_CODE: {
        gr: "Παρακαλώ γράψτε κώδικα.",
        en: "Please write your code."
    },
    CONFIRM_REJECT_CODE: {
        gr: "Είστε βέβαιοι ότι θέλετε να ακυρώσετε αυτή την προσπάθεια; O υπάρχον κώδικας θα διαγραφεί.",
        en: "Are you sure you would like to go back? This will erase your existing code."
    },
    CODE_SUBMITTED: {
        gr: "Ο κώδικάς σας έχει μεταφορτωθεί στο Rover και θα εκτελεστεί σύντομα!",
        en: "Your code has been uploaded to the Rover and will be executed soon!"
    },
    CODE_EXISTS: {
        gr: "Έχετε ήδη μεταφορτωμένο κώδικα που δεν έχει εκτελεστεί ακόμα από το Rover. Παρακαλώ περιμένετε μέχρι να εκτελεστεί ο προηγούμενος κώδικας από το Rover πριν υποβάλετε νέο κώδικα.",
        en: "You already have an uploaded code that has not yet been executed by the rover. Please wait until your previous code is executed by the rover before submitting new code."
    },
    HELLO: {
        gr: "Γειά σου",
        en: "Hello"
    },
    CHANGE_NAME: {
        gr: "Αλλαγή ονόματος",
        en: "Change name"
    },
    PLAYING_NOW: {
        gr: "Παίζει τώρα",
        en: "Playing now"
    },
    WITH: {
        gr: "με",
        en: "with"
    },
    POINTS_SO_FAR: {
        gr: "βαθμούς",
        en: "points"
    },
    PREVIOUS_ATTEMPT: {
        gr: "Προηγούμενη προσπάθεια",
        en: "Previous attempt"
    },
    LOWER_SCORE: {
        gr: "Χαμηλότερη βαθμολογία",
        en: "Lower score"
    },
    POSITION: {
        gr: "Θέση",
        en: "Position"
    }

};