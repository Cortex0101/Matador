import { Board } from './board.js';

import { Datepicker } from 'vanillajs-datepicker';
// import danish locale
import da from 'vanillajs-datepicker/locales/da';

let userBoard = new Board(5, false);
let computerBoard = new Board(5, false);

let userBoardAfterCompletion = new Board(5, false);
let computerBoardAfterCompletion = new Board(5, false)

Object.assign(Datepicker.locales, da);

let enabledDates = [];
const elem = document.getElementById('date-picker');
let datepicker = null;
let minDate = null;
let maxDate = null;

function isEnabledDate(date) {
    for (let enabledDate of enabledDates) {
        if (enabledDate.getFullYear() === date.getFullYear() && enabledDate.getMonth() === date.getMonth() && enabledDate.getDate() === date.getDate()) {
            return true;
        }
    }

    return false;
}

function isDisabledDate(date) {
    return !isEnabledDate(date);
}

let user = null;

fetch('https://ordish.dk/woordle/user').then(res => res.json()).then(use => {
    user = use;

    fetch('https://ordish.dk/woordle/user/gameDates?userID=' + user.userID).then(response => response.json()).then(data => {
        // if data is empty array
        if (data === null || data.length === 0) {
            return;
        }

        for (const date of data) {
            let dateN = new Date(date.Date);
            dateN.setDate(dateN.getDate());
            enabledDates.push(dateN);
        }

        minDate = new Date(Math.min.apply(null, enabledDates));
        maxDate = new Date();

        let disabledDates = [];
        // Get all dates from minDate to maxDate and check if they are in enabledDates
        let date = new Date(minDate);
        while (date <= maxDate) {
            if (isEnabledDate(date) === false) {
                disabledDates.push(date);
            }
            date.setDate(date.getDate() + 1);
        }

        datepicker = new Datepicker(elem, {
            buttonClass: 'btn',
            datesDisabled: isDisabledDate,
            maxDate: maxDate,
            minDate: minDate,
            language: 'da',
        });

        datepicker.setDate(enabledDates[0]);

        /*
        let today = new Date();
        // check if today is a disabled date if so find the next available date
        while (isEnabledDate(today) === false) {
            today = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 1);
        }
        datepicker.setDate(today);
                */
    });
})

let prevDateBtn = document.getElementById("prev-date-btn");
let nextDateBtn = document.getElementById("next-date-btn");

prevDateBtn.addEventListener("click", () => {
    let date = datepicker.getDate();
    let yesterday = new Date(date.getFullYear(), date.getMonth(), date.getDate() - 1);
    // check if yesterday is a disabled date if so find the next available date
    while (isEnabledDate(yesterday) === false) {
        if (yesterday < minDate) {
            return;
        }

        yesterday = new Date(yesterday.getFullYear(), yesterday.getMonth(), yesterday.getDate() - 1);
    }

    datepicker.setDate(yesterday);
});

nextDateBtn.addEventListener("click", () => {
    let date = datepicker.getDate();
    let tomorrow = new Date(date.getFullYear(), date.getMonth(), date.getDate() + 1);
    // check if tomorrow is a disabled date if so find the next available date
    while (isEnabledDate(tomorrow) === false) {
        if (tomorrow > maxDate) {
            return;
        }

        tomorrow = new Date(tomorrow.getFullYear(), tomorrow.getMonth(), tomorrow.getDate() + 1);
    }

    datepicker.setDate(tomorrow);
});

function convertFeedbackToWordleFormat(feedback) {
    let wordleFeedback = "";
    for (let i = 0; i < 5; ++i) {
        if (feedback.correctlyPlacedLetters.find(letter => letter.position === i + 1)) {
            wordleFeedback += "y";
        } else if (feedback.misplacedLetters.find(letter => letter.position === i + 1)) {
            wordleFeedback += "m";
        } else {
            wordleFeedback += "n";
        }
    }
    return wordleFeedback;
}

export function setGuessesLeft(userLeftArr, pcLeftArr, userGuessesLength, pcGuessesLength, id) {
    let table = document.getElementById(id);

    // clear table
    for (let i = 0; i < table.rows.length; ++i) {
        table.rows[i].cells[0].innerHTML = "-";
        table.rows[i].cells[1].innerHTML = "-";
    }

    /* Go through each row and retrieve the two td's */
    for (let i = 0; i < table.rows.length; ++i) {
        let userLeft = table.rows[i].cells[0];
        let pcLeft = table.rows[i].cells[1];

        /* Set the text of the td's to the guesses left */
        userLeft.innerHTML = userLeftArr[i];
        pcLeft.innerHTML = pcLeftArr[i];
    }

    // Get index of last occourence of 1 in userLeftArr
    let lastUserLeft = userGuessesLength;
    let lastPCLeft = pcGuessesLength;

    if (lastUserLeft < 6) {
        for (let i = lastUserLeft; i < 6; ++i) {
            table.rows[i].cells[0].innerHTML = "-";
        }
    }

    if (lastPCLeft < 6) {
        for (let i = lastPCLeft; i < 6; ++i) {
            table.rows[i].cells[1].innerHTML = "-";
        }
    }
}

function setGuessesLeftAfterCompletion(userLeftArr, pcLeftArr) {
    let table = document.getElementById("comp-stats-id-after-completion");

    /* Go through each row and retrieve the two td's */
    for (let i = 0; i < table.rows.length; ++i) {
        let userLeft = table.rows[i].cells[0];
        let pcLeft = table.rows[i].cells[1];

        /* Set the text of the td's to the guesses left */
        userLeft.innerHTML = userLeftArr[i];
        pcLeft.innerHTML = pcLeftArr[i];
    }

    // Get index of last occourence of 1 in userLeftArr
    let lastUserLeft = userLeftArr.lastIndexOf(1);
    let lastPCLeft = pcLeftArr.lastIndexOf(1);

    if (lastUserLeft < 6) {
        for (let i = lastUserLeft; i < 6; ++i) {
            table.rows[i].cells[0].innerHTML = "-";
        }
    }

    if (lastPCLeft < 6) {
        for (let i = lastPCLeft; i < 6; ++i) {
            table.rows[i].cells[1].innerHTML = "-";
        }
    }
}

let userBoardContainer = document.getElementById('user-board');
let computerBoardContainer = document.getElementById('computer-board');

// clear userBoardContainer children
while (userBoardContainer.firstChild) {
    userBoardContainer.removeChild(userBoardContainer.firstChild);
}
userBoardContainer.appendChild(userBoard.board);

// clear computerBoardContainer children
while (computerBoardContainer.firstChild) {
    computerBoardContainer.removeChild(computerBoardContainer.firstChild);
}
computerBoardContainer.appendChild(computerBoard.board);

userBoard.setCellContentSize("1");
computerBoard.setCellContentSize("1");

export async function showCom(date) {
    let userGuesses = null;
    let guessesPC = null;
    let userWordsLeft = null;
    let pcWordsLeft = null;

    // convert date from Sat Feb 03 2024 01:00:00 GMT+0100 (Centraleuropæisk normaltid) to 2024-02-03
    // make sure to account for timezones. If you convert to isoString directly date may be off by one day
    let adjusted = new Date(date.getTime() - (date.getTimezoneOffset() * 60000));
    let isoStr = adjusted.toISOString()

    let fetchStr = "https://ordish.dk/woordle/user/" + user.userID + "/analysis/" + isoStr;

    try {
        await fetch(fetchStr).then(res => res.json()).then(res => {
            userGuesses = res.playerGuesses;
            guessesPC = res.computerGuesses;
            pcWordsLeft = res.wordsLeftPc;
            userWordsLeft = res.wordsLeft;
        }).catch(err => {
            console.log(err);
        });
    } catch (err) {
        console.log(err);
    }
    userBoard.clear();
    computerBoard.clear();

    userBoard.setBoardDataFromDB(userGuesses);
    userBoard.focusFirstTileOnFirstEmpty();
    userBoard.freeze();

    let pcGuesses = [];
    for (let i = 0; i < guessesPC.guesses.length; ++i) {
        pcGuesses.push({
            GuessWord: guessesPC.guesses[i].guess,
            Feedback: convertFeedbackToWordleFormat(guessesPC.guesses[i])
        });
    }

    computerBoard.setBoardDataFromDB(pcGuesses);
    computerBoard.focusFirstTileOnFirstEmpty();
    computerBoard.freeze();

    setGuessesLeft(userWordsLeft, pcWordsLeft, userGuesses.length, guessesPC.guesses.length, "comp-stats-id");
}

function convertTreeToWordleFormat2(woordleFeedback, word) {
    let guessEntry = {
        "guess": word,
        "correctlyPlacedLetters": [],
        "absentLetters": [],
        "misplacedLetters": []
    }

    for (let i = 0; i < woordleFeedback.length; ++i) {
        if (woordleFeedback[i] === 'y') {
            guessEntry["correctlyPlacedLetters"].push({
                "letter": word[i],
                "position": i + 1
            });
        } else if (woordleFeedback[i] === 'm') {
            guessEntry["misplacedLetters"].push({
                "letter": word[i],
                "position": i + 1
            });
        } else {
            guessEntry["absentLetters"].push(word[i]);
        }
    }

    return guessEntry;
}

let compShown = false;
let secretWord = null;

export function showComparisonAfterCompletion(guesses) {
    if (!compShown) {
        let compCont = document.getElementById("comp-cont-after-completion");
        compCont.classList.remove("hide");

        let userBoardContainerAfterCompletion = document.getElementById('user-board-after-completion');
        let computerBoardContainerAfterCompletion = document.getElementById('computer-board-after-completion');

        userBoardContainerAfterCompletion.appendChild(userBoardAfterCompletion.board);
        computerBoardContainerAfterCompletion.appendChild(computerBoardAfterCompletion.board);

        userBoardAfterCompletion.setBoardDataFromDB(guesses);
        userBoardAfterCompletion.focusFirstTileOnFirstEmpty();
        userBoardAfterCompletion.freeze();
        userBoardAfterCompletion.setCellContentSize("1");

        let userGuesses = {
            "guesses": []
        }

        for (let i = 0; i < guesses.length; ++i) {
            userGuesses["guesses"].push(convertTreeToWordleFormat2(guesses[i].Feedback, guesses[i].GuessWord));
        }
        let guessesUser = null;
        let guessesPC = null;

        // Post guesses to /user/analysis
        fetch('https://ordish.dk/woordle/user/analysis', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userGuesses),
        }).then(res => res.json()).then(res => {
            guessesUser = res;
        }).then(() => {
            fetch('https://ordish.dk/woordle/word').then(res => res.text()).then(word => {
                secretWord = word;
            }).then(() => {
                fetch('https://ordish.dk/woordle/word/computerGuesses?word=' + secretWord).then(res => res.json()).then(pcGuesses => {
                    let guessesPC = pcGuesses.guesses;

                    for (let i = 0; i < guessesPC.length; ++i) {
                        guessesPC[i].Feedback = convertFeedbackToWordleFormat(guessesPC[i]);
                        guessesPC[i].GuessWord = guessesPC[i].guess;
                    }

                    computerBoardAfterCompletion.setBoardDataFromDB(guessesPC);
                    computerBoardAfterCompletion.focusFirstTileOnFirstEmpty();
                    computerBoardAfterCompletion.freeze();
                    computerBoardAfterCompletion.setCellContentSize("1");

                    fetch('https://ordish.dk/woordle/user/analysis', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(pcGuesses),
                    }).then(res => res.json()).then(res => {
                        guessesPC = res;
                    }).then(() => {
                        setGuessesLeftAfterCompletion(guessesUser, guessesPC);
                    });
                });
            });
        });

        compShown = true;
    }
}

elem.addEventListener("changeDate", (e) => {
    let date = e.detail.date;
    showCom(date);
});

function enableAndSetToday() {
    let today = new Date();
    enabledDates.push(today);
    datepicker.setDate(today);
}


document.addEventListener("keyup", (e) => {
    if (e.key === "1") {
        enableAndSetToday();
    }
});