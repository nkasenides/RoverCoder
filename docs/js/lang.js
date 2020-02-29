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

    PAGE_HOME: {
        gr: "Αρχική",
        en: "Home"
    },
    PAGE_SIGNIN: {
        gr: "Σύνδεση",
        en: "Sign in"
    },
    PAGE_SIGNUP: {
        gr: "Εγγραφή",
        en: "Sign up"
    },
    PAGE_ADD_LISTING: {
        gr: "Καταχώρηση αγγελίας",
        en: "Create listing"
    },
    PAGE_LIST_LISTINGS: {
        gr: "Οι αγγελίες μου",
        en: "My listings"
    },
    PAGE_EDIT_LISTING: {
        gr: "Επεξεργασία αγγελίας",
        en: "Modify listing"
    },


    CLASSIFIEDS: {
        gr: "Αγγελίες",
        en: "Classifieds"
    },
    NICOSIA: {
        gr: "Λευκωσία",
        en: "Nicosia"
    },
    LIMASSOL: {
        gr: "Λεμεσός",
        en: "Limassol"
    },
    LARNACA: {
        gr: "Λάρνακα",
        en: "Larnaca"
    },
    AMMOCHOSTOS: {
        gr: "Αμμόχωστος",
        en: "Ammochostos"
    },
    PAPHOS: {
        gr: "Πάφος",
        en: "Paphos"
    },
    NEW_ENTRY: {
        gr: "Καταχώρηση",
        en: "Create listing"
    },
    CONTACT_US: {
        gr: "Επικοινωνία",
        en: "Contact us"
    },
    SIGN_IN: {
        gr: "Σύνδεση",
        en: "Sign in"
    },
    SIGN_OUT: {
        gr: "Αποσύνδεση",
        en: "Sign out"
    },
    HELP: {
        gr: "Βοήθεια",
        en: "Help"
    },
    TERMS_AND_CONDITIONS: {
        gr: "Όροι και Προϋποθέσεις",
        en: "Terms and Conditions"
    },
    ADD_NEW_LISTING: {
        gr: "Καταχώρηση αγγελίας",
        en: "Create a new listing"
    },
    ADD_NEW_LISTING_OTHER: {
        gr: "Καταχώρηση άλλης αγγελίας",
        en: "Create another listing"
    },
    SEE_LISTINGS: {
        gr: "Δες αγγελίες",
        en: "See listings"
    },
    SIGN_IN_INSTRUCTION_PHONE: {
        gr: "Εισάγετε τον αριθμό του κινητού τηλεφώνου σας για να συνδεθείτε. Ακολούθως, θα λάβετε μήνυμα SMS στο κινητό σας για επαλήθευση.",
        en: "Please enter your mobile phone number to sign in. You will receive an SMS message on your phone for confirmation."
    },
    SIGN_IN_ATTEMPTED_ADD_LISTING: {
        gr: "Για να καταχωρήσετε καινούργια αγγελία, πρέπει πρώτα να συνδεθείτε.",
        en: "You need to sign in before adding a listing."
    },
    SIGN_IN_INSTRUCTION_VERIFY_CODE: {
        gr: "Παρακαλώ εισάγετε τον κωδικό επαλήθευσης που έχετε λάβει στον αριθμό τηλεφώνου σας",
        en: "Please enter the verification code, sent to your phone number"
    },
    I_HAVE_NOT_RECEIVED_CODE: {
        gr: "Δεν εχω λάβει κωδικό",
        en: "I have not received a code"
    },
    PHONE_NUMBER: {
        gr: "Αριθμός τηλεφώνου",
        en: "Phone number"
    },
    PHONE_NUMBER_CONTACT: {
        gr: "Tηλέφωνο επικοινωνίας",
        en: "Phone number"
    },
    PHONE_NUMBER_OR_EMAIL: {
        gr: "Αριθμός τηλεφώνου ή email",
        en: "Phone number or email"
    },
    VERIFICATION_CODE: {
        gr: "Κωδικός επαλήθευσης",
        en: "Verification code"
    },
    VERIFY: {
        gr: "Επαλήθευση",
        en: "Verify"
    },
    INVALID_PHONE_MODAL_MESSAGE: {
        gr: "Ο αριθμός τηλεφώνου που έχετε εισάγει δεν είναι σωστός.",
        en: "The phone number you entered is not valid."
    },
    PLEASE_ENTER_PHONE_NUMBER: {
      gr: "Παρακαλώ δώστε αριθμό τηλεφώνου.",
      en: "Please enter you phone number."
    },
    PLEASE_ENTER_PHONE_OR_EMAIL: {
        gr: "Παρακαλώ εισάγετε αριθμό τηλεφώνου ή διεύθυνση ηλεκτρονικού ταχυδρομείου (email)",
        en: "Please enter a phone number or an email address."
    },
    CODE_NOT_VERIFIED_MODAL_MESSAGE: {
        gr: "Η επαλήθευση του κώδικα έχει αποτύχει.",
        en: "Code verification failed."
    },
    SIGN_UP_AT_ANTAMIVI: {
        gr: "Εγγραφή στο jjobo.com",
        en: "Sign up for jjobo.com"
    },
    COMPLETE_SIGNUP_INSTRUCTION: {
        gr: "",
        en: "To complete the sign up process, please enter your name and surname below:"
    },
    FIRSTNAME: {
        gr: "Όνομα",
        en: "First name"
    },
    LASTNAME: {
        gr: "Επίθετο",
        en: "Last name"
    },
    NAME: {
        gr: "Όνοματεπώνυμο",
        en: "Full name"
    },
    COMPLETE_SIGNUP: {
        gr: "Ολοκλήρωση εγγραφής",
        en: "Complete sign up"
    },
    ERROR: {
        gr: "Πρόβλημα",
        en: "Error"
    },
    PLEASE_ENTER_YOUR_FIRST_NAME: {
        gr: "Παρακαλώ εισάγετε το όνομά σας.",
        en: "Please enter your first name."
    },
    PLEASE_ENTER_YOUR_LAST_NAME: {
        gr: "Παρακαλώ εισάγετε το επίθετό σας.",
        en: "Please enter your last name."
    },
    PLEASE_ENTER_YOUR_FULL_NAME: {
        gr: "Παρακαλώ εισάγετε το ονοματεπώνυμο σας.",
        en: "Please enter your full name."
    },
    PLEASE_ENTER_YOUR_PHONE_NUMBER: {
        gr: "Παρακαλώ εισάγετε τον αριθμό τηλεφώνου σας.",
        en: "Please enter your phone number"
    },
    INVALID_PHONE_NUMBER_TRY_AGAIN: {
        gr: "Ο αριθμός τηλεφώνου δεν έιναι σωστός. Πατήστε OK για επαλήθευση καινούργιου αριθμού.",
        en: "The phone number you entered is not valid. Please press 'OK' to verify a new phone number.",
    },
    INVALID_ID_TRY_AGAIN: {
        gr: "Ο αριθμός εγγραφής χρήστη δεν έιναι σωστός. Πατήστε OK για επαλήθευση καινούργιου αριθμού.",
        en: "The user ID provided is not valid. Please press 'OK' to verify a new phone number.",
    },
    ERROR_DURING_SIGN_UP_NEW_SIGNUP: {
        gr: "Παρουσιάστηκε σφάλμα κατα την εγγραφή. Πατήστε 'OK' για επαλήθευση καινούργιου αριθμού τηλεφώνου.",
        en: "An error occurred during sign up. Please click 'OK' to verify a new phone number."
    },
    USER_WITH_NUMBER_EXISTS: {
        gr: "Ο χρήστης με τον εξής αριθμό τηλεφώνου υπάρχει ήδη.",
        en: "A user with the following phone number already exists."
    },
    USER_WITH_ID_EXISTS: {
        gr: "Ο χρήστης με αυτό τον μοναδικό αριθμό υπάρχει ήδη.",
        en: "A user with the given ID already exists."
    },
    ERROR_DURING_SIGN_UP_TRY_AGAIN: {
        gr: "Παρουσιάστηκε σφάλμα κατα την εγγραφή. Πατήστε OK για να ξαναπροσπαθήσετε.",
        en: "An error occurred during sign up. Please click 'OK' to try again."
    },
    PLEASE_WAIT: {
        gr: "Παρακαλώ περιμένετε",
        en: "Please wait"
    },
    TITLE_ENGLISH: {
        gr: "Τίτλος αγγελίας",
        en: "Classified title"
    },
    TITLE_GREEK: {
        gr: "Τίτλος αγγελίας",
        en: "Classified title"
    },
    DESCRIPTION_ENGLISH: {
        gr: "Περιγραφή αγγελίας",
        en: "Classified description"
    },
    DESCRIPTION_GREEK: {
        gr: "Περιγραφή αγγελίας",
        en: "Classified description"
    },
    DESCRIPTION_PLACEHOLDER: {
        gr: "Προθέστε μια περιγραφή της αγγελίας σας, περιγράφοντας σημαντικές πληροφορίες που θα βοηθήσουν στο τι ζητάτε.",
        en: "Add a description, detailing important information which describes what you are looking for."
    },
    CITIES: {
        gr: "Πόλεις αγγελίας",
        en: "Cities"
    },
    ALL_CITIES: {
        gr: "Όλες οι πόλεις",
        en: "All cities"
    },
    CATEGORY: {
        gr: "Κατηγορία αγγελίας",
        en: "Category"
    },
    CHOOSE_CITIES: {
        gr: "Επιλέξτε πόλεις...",
        en: "Choose cities..."
    },
    PLEASE_ENTER_A_VALID_TITLE: {
        gr: "Παρακαλώ δώστε τίτλο.",
        en: "Please enter a valid title."
    },
    PLEASE_ENTER_A_DESCRIPTION: {
        gr: "Παρακαλώ δώστε περιγραφή",
        en: "Please enter a description."
    },
    PLEASE_SELECT_CITIES: {
        gr: "Παρακαλώ επιλέξτε πόλεις.",
        en: "Please select cities."
    },
    PLEASE_SELECT_CATEGORY: {
        gr: "Παρακαλώ επιλέξτε κατηγορία.",
        en: "Please select a category."
    },
    AMOUNT: {
        gr: "Ποσό ανταμοιβής",
        en: "Amount"
    },
    HOURS: {
        gr: "Ώρες",
        en: "Hours"
    },
    HOUR: {
        gr: "Ώρα",
        en: "Hour"
    },
    MINUTES: {
        gr: "Λεπτά",
        en: "Minutes"
    },
    MINUTE: {
        gr: "Λεπτό",
        en: "Minute"
    },
    DURATION: {
        gr: "Διάρκεια",
        en: "Duration"
    },
    PLEASE_ENTER_A_VALID_AMOUNT: {
        gr: "Παρακαλώ εισάγετε σωστό ποσό (0 ή περισσότερο).",
        en: "Please enter a valid amount (0 or more)."
    },
    PLEASE_ENTER_A_VALID_DURATION: {
        gr: "Παρακαλώ εισάγετε σώστη διάρκεια.",
        en: "Please enter a valid duration."
    },
    THE_DURATION_CANNOT_BE_0: {
        gr: "Η διάρκεια δεν μπορεί να είναι 0 ώρες και 0 λεπτά.",
        en: "The duration cannot be 0 hours and 0 minutes."
    },
    ERROR_OCCURRED_DURING_CREATION: {
        gr: "Παρουσιάστικε σφάλμα κατα την δημιουργία αγγελίας.",
        en: "An error occurred during the creation."
    },
    ADD_LISTING_SUCCESS: {
        gr: "Η καταχώρισή σας δημιουργήθηκε με επιτυχία και εκκρεμεί αποδοχή της.",
        en: "Your listing was created successfully and is pending for review."
    },
    MODIFY_LISTING_SUCCESS: {
        gr: "Η επεξεργασία της αγγελίας ολοκληρώθηκε με επιτυχία.",
        en: "Your listing was modified successfully."
    },
    RETURN_TO_HOMEPAGE: {
        gr: "Επιστροφή στη βασική σελίδα",
        en: "Return to home page"
    },
    MY_LISTINGS: {
        gr: "Οι αγγελίες μου",
        en: "My listings"
    },
    PENDING: {
        gr: "Εκκρεμεί",
        en: "Pending"
    },
    PUBLISHED: {
        gr: "Δημοσιεύτηκε",
        en: "Published"
    },
    TITLE: {
        gr: "Τίτλος",
        en: "Title"
    },
    CREATED_ON: {
        gr: "Δημιουργήθηκε την",
        en: "Created on"
    },
    UPDATED_ON: {
        gr: "Ενημερώθηκε την",
        en: "Updated on"
    },
    APPROVED: {
        gr: "Εγκριθηκε",
        en: "Approved"
    },
    VIEW: {
        gr: "Επισκόπηση",
        en: "View"
    },
    DELETE: {
        gr: "Διαγραφή",
        en: "Delete"
    },
    EDIT: {
        gr: "Επεξεργασία",
        en: "Edit"
    },
    ERROR_OCCURRED: {
        gr: "Παρουσιάστηκε σφάλμα - Προσπαθήστε να ανανεώσετε τη σελίδα.",
        en: "An error occurred - Try reloading the page."
    },
    COULD_NOT_DELETE_LISTING: {
        gr: "Η αγγελία δεν έχει διαγραφεί",
        en: "Unable to delete listing."
    },
    LISTING_DELETED: {
        gr: "Η αγγελία έχει διαγραφεί.",
        en: "Listing deleted."
    },
    DATE: {
        gr: "Ημέρα",
        en: "Date"
    },
    TIME: {
        gr: "Ώρα",
        en: "Time"
    },
    DATE_AND_TIME: {
        gr: "Ημέρα και ώρα",
        en: "Date and time"
    },
    PLEASE_SELECT_A_DATE: {
        gr: "Παρακαλώ επιλέξτε ημέρα",
        en: "Please select a date."
    },
    PLEASE_SELECT_A_TIME: {
        gr: "Παρακαλώ επιλέξτε ώρα.",
        en: "Please select a time."
    },
    PLEASE_SELECT_A_DATE_IN_THE_FUTURE: {
        gr: "Παρακαλώ επιλέξτε μια ημερομηνία και ώρα στο μέλλον.",
        en: "Please select a date/time in the future."
    },
    NO_LISTINGS_TO_SHOW: {
        gr: "Δεν υπάρχουν αγγελίες.",
        en: "There are no listings to show."
    },
    NO_MORE_LISTINGS_TO_SHOW: {
        gr: "Δεν υπάρχουν περισσότερες αγγελίες.",
        en: "There are no more listings to show."
    },
    CITY: {
        gr: "Πόλεις αγγελίας",
        en: "City"
    },
    INTERESTED: {
        gr: "Ενδιαφέρομαι",
        en: "Interested"
    },
    AND: {
        gr: "και",
        en: "and"
    },
    COULD_NOT_FETCH_LISTING: {
        gr: "Η αγγελία δεν βρέθηκε.",
        en: "The listing could not be found."
    },
    CANCEL: {
        gr: "Ακύρωση",
        en: "Cancel"
    },
    CLEAR: {
        gr: "Καθαρισμός",
        en: "Clear"
    },
    OK: {
        gr: "OK",
        en: "OK"
    },
    JANUARY: {
        gr: "Ιανουάριος",
        en: "January"
    },
    FEBRUARY: {
        gr: "Φεβρουάριος",
        en: "February"
    },
    MARCH: {
        gr: "Μάρτιος",
        en: "March"
    },
    APRIL: {
        gr: "Απρίλιος",
        en: "April"
    },
    MAY: {
        gr: "Μάϊος",
        en: "May"
    },
    JUNE: {
        gr: "Ιούνιος",
        en: "June"
    },
    JULY: {
        gr: "Ιούλιος",
        en: "July"
    },
    AUGUST: {
        gr: "Αύγουστος",
        en: "August"
    },
    SEPTEMBER: {
        gr: "Σεπτέμβριος",
        en: "September"
    },
    OCTOBER: {
        gr: "Οκτώβριος",
        en: "October"
    },
    NOVEMBER: {
        gr: "Νοέμβριος",
        en: "November"
    },
    DECEMBER: {
        gr: "Δεκέμβριος",
        en: "December"
    },
    JANUARY_SHORT: {
        gr: "Ιαν",
        en: "Jan"
    },
    FEBRUARY_SHORT: {
        gr: "Φεβ",
        en: "Feb"
    },
    MARCH_SHORT: {
        gr: "Μαρ",
        en: "Mar"
    },
    APRIL_SHORT: {
        gr: "Απρ",
        en: "Apr"
    },
    MAY_SHORT: {
        gr: "Μαι",
        en: "May"
    },
    JUNE_SHORT: {
        gr: "Ιουν",
        en: "Jun"
    },
    JULY_SHORT: {
        gr: "Ιουλ",
        en: "Jul"
    },
    AUGUST_SHORT: {
        gr: "Αυγ",
        en: "Aug"
    },
    SEPTEMBER_SHORT: {
        gr: "Σεπ",
        en: "Sep"
    },
    OCTOBER_SHORT: {
        gr: "Οκτ",
        en: "Oct"
    },
    NOVEMBER_SHORT: {
        gr: "Νοε",
        en: "Nov"
    },
    DECEMBER_SHORT: {
        gr: "Δεκ",
        en: "Dec"
    },
    MONDAY: {
        gr: "Δευτέρα",
        en: "Monday"
    },
    TUESDAY: {
        gr: "Τρίτη",
        en: "Tuesday"
    },
    WEDNESDAY: {
        gr: "Τετάρτη",
        en: "Wednesday"
    },
    THURSDAY: {
        gr: "Πέμπτη",
        en: "Thursday"
    },
    FRIDAY: {
        gr: "Παρασκευή",
        en: "Friday"
    },
    SATURDAY: {
        gr: "Σάββατο",
        en: "Saturday"
    },
    SUNDAY: {
        gr: "Κυριακή",
        en: "Sunday"
    },
    MONDAY_SHORT: {
        gr: "Δευ",
        en: "Mon"
    },
    TUESDAY_SHORT: {
        gr: "Τρι",
        en: "Tue"
    },
    WEDNESDAY_SHORT: {
        gr: "Τετ",
        en: "Wed"
    },
    THURSDAY_SHORT: {
        gr: "Πεμ",
        en: "Thu"
    },
    FRIDAY_SHORT: {
        gr: "Παρ",
        en: "Fri"
    },
    SATURDAY_SHORT: {
        gr: "Σάβ",
        en: "Sat"
    },
    SUNDAY_SHORT: {
        gr: "Κυρ",
        en: "Sun"
    },
    MONDAY_ABBREV: {
        gr: "Δ",
        en: "M"
    },
    TUESDAY_ABBREV: {
        gr: "Τ",
        en: "T"
    },
    WEDNESDAY_ABBREV: {
        gr: "Τ",
        en: "W"
    },
    THURSDAY_ABBREV: {
        gr: "Π",
        en: "T"
    },
    FRIDAY_ABBREV: {
        gr: "Π",
        en: "F"
    },
    SATURDAY_ABBREV: {
        gr: "Σ",
        en: "S"
    },
    SUNDAY_ABBREV: {
        gr: "Κ",
        en: "S"
    },
    INVALID_LISTING_ID: {
        gr: "Ο αριθμός αγγελίας δεν έιναι σωστός.",
        en: "The listing ID is not valid.",
    },
    COULD_NOT_RETRIEVE_LISTING: {
        gr: "Η αγγελία δεν βρέθηκε.",
        en: "The listing could not be loaded.",
    },
    SAVE: {
        gr: "Αποθήκευση",
        en: "Save"
    },
    SERVER_ERROR: {
        gr: "Έχει παρουσιαστεί πρόβλημα στο διακομιστή. Απολογούμαστε για την ταλαιπωρία.",
        en: "A server error has occurred. We are sorry for the inconvenience."
    },
    CONTENT_MAY_NOT_CONTAIN_SYMBOLS: {
        gr: "Οι πληροφορίες δεν πρέπει να περιέχουν τα εξής σύμβολα: < και >.",
        en: "Your content may not contain the symbols: < and >."
    },
    DISCLAIMER: {
        gr: "Συμφωνώ με τη συλλογή, χρήση και επεξεργασία των προσωπικών μου δεδομένων και επιβεβαιώνω ότι είμαι τουλάχιστον 18 ετών. Το  JJobo.com είναι πλήρως συμμορφωμένο με το GDPR.  Κάνοντας κλικ στη \"Καταχώρηση\" συμφωνείτε με τους <a TARGET='_blank' href=\"termsAndConditions.jsp\">Όρους Χρήσης</a>, τους κανόνες δημοσίευσης και την πολιτική απορρήτου.",
        en: "I agree to the collection, use and processing of my personal data and confirm that I am at least 18 years old. JJobo.com is fully GDPR compliant. By clicking \"Submit\" you agree to our <a TARGET='_blank' href=\"termsAndConditions.jsp\">Terms of Use</a>, our publishing rules, and our privacy policy."
    },
    CLICK_AGREE_TO_TERMS_OF_USE: {
        gr: "Παρακαλώ συμφωνήστε με τους Όρους Χρήσης για καταχώρηση.",
        en: "Please agree to the Terms of Use to submit."
    },
    LISTING_IMAGE_INSTRUCTION: {
        gr: "Προαιρετικό - Μπορείτε να επιλέξετε εικόνα για την αγγελία σας πατώντας το 'Επιλογή εικόνας'.",
        en: "You can choose an image for your listing by clicking 'Choose image'."
    },
    LISTING_IMAGE_INSTRUCTION_2: {
        gr: "Προαιρετικό - Μπορείτε να επιλέξετε εικόνα για την αγγελία σας πατώντας το 'Επιλογή εικόνας' ή 'Αλλαγή Εικόνας'.",
        en: "You can choose an image for your listing by clicking 'Choose image' or 'Change image'."
    },
    LISTING_IMAGE: {
        gr: "Επιλογή εικόνας",
        en: "Choose image"
    },
    CHANGE_IMAGE: {
        gr: "Αλλαγή εικόνας",
        en: "Change image"
    },
    REMOVE_IMAGE: {
        gr: "Διαγραφή εικόνας",
        en: "Remove image"
    },
    ARE_YOU_SURE_SIGNOUT: {
        gr: "Είστε βέβαιοι ότι θέλετε να αποσυνδεθείτε;",
        en: "Are you sure you would like to sign out?"
    },
    ARE_YOU_SURE_DELETE: {
        gr: "Είστε βέβαιοι ότι θέλετε να διαγράψετε αυτή την αγγελία;",
        en: "Are you sure you would like to delete this listing?"
    },
    CALL: {
        gr: "Κάλεσε τώρα",
        en: "Call now"
    },
    CLOSE: {
        gr: "Κλείσε",
        en: "Close"
    },
    WRITE_SOMETHING: {
        gr: "Γράψτε το μήνυμά σας...",
        en: "Write something..."
    },
    SUBMIT: {
        gr: "Υποβολή",
        en: "Submit"
    },
    PLEASE_ENTER_SOME_CONTENT: {
        gr: "Παρακαλώ γράψτε το κείμενο σας.",
        en: "Please write your text."
    },
    SUBJECT: {
        gr: "Θέμα",
        en: "Subject"
    },
    PLEASE_ENTER_A_SUBJECT: {
        gr: "Παρακαλώ δώστε θέμα.",
        en: "Please enter a subject."
    },
    ERROR_OCCURRED_EMAIL: {
        gr: "Έχει παρουσιαστεί σφάλμα κατα την επικοινωνία με το JJobo.com.",
        en: "An error occurred while contacting JJobo.com."
    },
    SUCCESS: {
        gr: "Επιτυχία",
        en: "Success"
    },
    SUCCESS_EMAIL: {
        gr: "Σας ευχαριστούμε, το μήνυμα σας έχει σταλεί!",
        en: "Thank you, your message has been sent!"
    },
    GREEK: {
        gr: "Ελληνικά",
        en: "Greek"
    },
    ENGLISH: {
        gr: "Αγγλικά",
        en: "English"
    },
    FILTER_AND_ORDER: {
        gr: "Φίλτρα και ταξινόμηση",
        en: "Filters and order"
    },
    FILTERS: {
        gr: "Φίλτρα",
        en: "Filters"
    },
    ORDERING: {
        gr: "Ταξινόμηση",
        en: "Order"
    },
    FILTER_BY_CATEGORY: {
        gr: "Φιλτράρισμα ανα κατηγορία",
        en: "Filter by category"
    },
    FILTER_BY_CITY: {
        gr: "Φιλτράρισμα ανα πόλη",
        en: "Filter by city"
    },
    FILTER_BY_PRICE :{
        gr: "Φιλτράρισμα ανα τιμή",
        en: "Filter by price"
    },
    MINIMUM: {
        gr: "Ελάχιστο",
        en: "Minimum"
    },
    MAXIMUM: {
        gr: "Μέγιστο",
        en: "Maximum"
    },
    MINIMUM_AMOUNT: {
        gr: "Ελάχιστο ποσό",
        en: "Minimum amount"
    },
    MAXIMUM_AMOUNT: {
        gr: "Μέγιστο ποσό",
        en: "Maximum amount"
    },
    ALL: {
        gr: "Όλα",
        en: "All"
    },
    ALL_LISTINGS: {
        gr: "Όλες",
        en: "All"
    },
    ALL_CITIES: {
        gr: "Επιλογή όλων",
        en: "Select all"
    },
    NO_CITIES: {
        gr: "Επιλογή καμίας",
        en: "Select none"
    },
    BY_AMOUNT: {
        gr: "Ποσό",
        en: "Amount"
    },
    BY_DATE: {
        gr: "Ημερ. έναρξης",
        en: "Date occurring"
    },
    BY_CREATED_ON: {
        gr: "Ημερο. δημοσίευσης",
        en: "Date created"
    },
    ASCENDING: {
        gr: "(Αύξουσα)",
        en: "(Ascending)"
    },
    DESCENDING: {
      gr: "(Φθίνουσα)",
      en: "(Descending)"
    },
    HIT_VIEW: {
        gr: "προβολή",
        en: "view"
    },
    HIT_VIEWS: {
        gr: "προβολές",
        en: "views"
    },
    I_ACCEPT_OFFERS: {
        gr: "Δέχομαι προσφορές",
        en: "I accept offers"
    },
    WITH_OFFERS: {
        gr: "Αγγελίες που δέχονται προσφορές",
        en: "Include listings that accept offers"
    },
    RECENT_CLASSIFIEDS: {
        gr: "Πρόσφατες αγγελίες",
        en: "Recent classifieds"
    },
    LISTED_ON: {
        gr: "Αναρτήθηκε στις",
        en: "Listed on"
    },
    EXPIRES_ON: {
        gr: "Λήγει στις",
        en: "Expires on"
    },
    PLEASE_ENTER_DATE_AND_TIME: {
        gr: "Παρακαλώ εισάγετε την ημερομηνία και ώρα που θα ξεκινήσει η εργασία της αγγελίας σας.",
        en: "Please enter the date and time at which your classified's requested work/service needs to begin."
    },
    YOUR_LISTING_WILL_BE_DELETED: {
        gr: "Η καταχώρισή σας θα διαγραφεί αυτόματα όταν έχει λήξει η ημερομηνία και η ώρα που έχετε ορίσει.",
        en: "Your listing will automatically be deleted when the date and time you have set has expired."
    },
    PLEASE_ENTER_THE_DURATION: {
        gr: "Παρακαλώ εισάγετε τη διάρκεια της αγγελίας σας. Εαν δεν είστε σίγουρος/η, αφήστε τη διάρκεια κενή.",
        en: "Please enter the duration of your classified. If you are not sure, leave the duration blank."
    },
    PLEASE_ENTER_AMOUNT: {
        gr: "Παρακαλώ εισάγετε το ποσό ανταμοιβής για την αγγελία σας. Εαν δεν είστε σίγουροι ή δέχεστε προσφορές, πατήστε το κουμπί 'Δέχομαι προσφορές'.",
        en: "Please enter the amount for your classified. If you are not sure or you are open to offers, check the box 'I accept offers'."
    },
    SEARCH_NOW: {
        gr: "Ψάξε αγγελίες...",
        en: "Search..."
    },
    SEARCH: {
        gr: "Αναζήτηση",
        en: "Search"
    },
    SEARCH_TERM_MISSING: {
        gr: "Παρακαλώ δώστε όρο αναζήτησης.",
        en: "Please enter a search term."
    },
    CLEAR_SEARCH: {
        gr: "Καθαρισμός",
        en: "Clear"
    },
    INVALID_SIZE_TITLE: {
        gr: "Παρακαλώ εισάγετε τίτλο μέχρι 50 λέξεις.",
        en: "Please enter a title no more than 50 words."
    },
    INVALID_SIZE_DESCRIPTION: {
        gr: "Παρακαλώ εισάγετε περιγραφή μέχρι 500 λέξεις.",
        en: "Please enter a description no more than 500 words."
    },
    NO_SEARCH_RESULTS: {
        gr: "Δεν βρέθηκαν αποτελέσματα για τον όρο ",
        en: "No results found for the term "
    },
    FILTER_BY_PRICE_NOTE: {
        gr: "Εάν θέλετε να συμπεριλάβετε αγγελίες που δέχονται προσφορές πατήστε 'Αγγελίες που δέχονται προσφορές'. Για να φιλτράρετε με ελάχιστο ποσό, απενεργοποιήστε αυτή την επιλογή.",
        en: "If you would like to include listings that accept offers, check 'Include listings that accept offers'. To filter listings by a minimum amount, uncheck this option."
    },
    LIMIT_EXCEEDED: {
        gr: "Υπέρβαση ορίου: Δεν μπορείτε να καταχωρήσετε περισσότερες από 10 αγγελίες σε λιγότερο 30 λεπτά. Δοκιμάστε ξανά σε λίγο.",
        en: "Limit exceeded: You may not create more than 10 classified listings in less than 30 minutes. Please try again in awhile."
    }



};