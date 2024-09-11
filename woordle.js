import { Board } from './board.js';
import { showCom, showComparisonAfterCompletion } from './comp.js';
import { Keyboard } from './keyboard.js';
import { Stats } from './woordleStats.js';

import html2canvas from 'html2canvas';

//document.getElementById('mmain').style.maxHeight = window.innerHeight - 56 + "px";

let keyboard = new Keyboard();

const board = new Board(5);
let boardContainer = document.getElementById(`board-container-5`);
boardContainer.appendChild(board.board);
window.onresize = () => {
    board.setHeight();
}

const shareButton = document.getElementById("share-button");

function startCountdown() {
    const timerElement = document.querySelector('.timer');

    function updateTimer() {
        const now = new Date();
        const midnight = new Date();
        midnight.setHours(24, 0, 0, 0); // Set to next midnight

        const diff = midnight - now;

        if (diff <= 0) {
            // Stop the timer when it reaches 00:00
            clearInterval(interval);
            timerElement.textContent = '00:00:00';
            return;
        }

        // Calculate hours, minutes, and seconds
        let hours = Math.floor(diff / 1000 / 60 / 60);
        let minutes = Math.floor(diff / 1000 / 60) % 60;
        let seconds = Math.floor(diff / 1000) % 60;

        // Format time values to have leading zeros
        hours = hours < 10 ? '0' + hours : hours;
        minutes = minutes < 10 ? '0' + minutes : minutes;
        seconds = seconds < 10 ? '0' + seconds : seconds;

        // Update the timer element
        timerElement.textContent = `${hours}:${minutes}:${seconds}`;
    }

    // Update the timer every second
    updateTimer(); // Update immediately
    const interval = setInterval(updateTimer, 1000);

    return interval; // Return the interval ID if needed
}

function cutModal() {
    let modal = document.getElementById("modall");
    let copy = modal.cloneNode(true);

    // remove all elements with class='remove-on-share'
    let elements = copy.getElementsByClassName("remove-on-share");
    while (elements.length > 0) {
        elements[0].parentNode.removeChild(elements[0]);
    }

    return copy;
}

async function shareCanvas() {
    /*
    let modal = cutModal();
    document.body.appendChild(modal);
    const canvasElement = await html2canvas(modal);
    const dataUrl = canvasElement.toDataURL();
    const blob = await (await fetch(dataUrl)).blob();
    const filesArray = [
      new File(
        [blob],
        'animation.png',
        {
          type: blob.type,
          lastModified: new Date().getTime()
        }
      )
    ];
    const shareData = {
      files: filesArray,
    };
    navigator.share(shareData);

    document.body.removeChild(modal);
    */
  }

  /*
document.getElementById('share-button').addEventListener('click', function() {
    shareCanvas();
});
*/

/*
let board = new Board(5);
let boardContainer = document.getElementById(`board-container-5`);
boardContainer.appendChild(board.board);
*/

/*
    router.get('/user', async (req, res) => {
    const user = await getUserInfo(req.query.username);
    res.send(user);
});
*/

document.getElementById("toggleLettersVisibility").addEventListener('change', function() {
    if(this.checked) {
        document.getElementById("toggleLabel").textContent = "Skjuler bogstaver i deling";
        document.getElementById("user-board-after-completion").classList.add("cell__content--hidden");
        document.getElementById("computer-board-after-completion").classList.add("cell__content--hidden");
    } else {
        document.getElementById("toggleLabel").textContent = "Viser bogstaver i deling";
        document.getElementById("user-board-after-completion").classList.remove("cell__content--hidden");
        document.getElementById("computer-board-after-completion").classList.remove("cell__content--hidden");
    }
});

async function shareCanvas2() {
    html2canvas(document.getElementById('comp-cont-after-completion')).then(canvas => {
        canvas.toBlob(function(blob) {
            const file = new File([blob], "image.png", { type: "image/png" });
            const data = { files: [file] };
            if (navigator.canShare && navigator.canShare(data)) {
                navigator.share(data)
                    .then(() => console.log('Share was successful.'))
                    .catch((error) => console.log('Sharing failed', error));
            } else {
                console.log(`Your system doesn't support sharing files.`);
            }
        }, 'image/png');
    });
  }

document.getElementById("shareButton").addEventListener('click', function() {
    var showLetters = document.getElementById("toggleLettersVisibility").checked;
    // Implement the share functionality here
    // Use the `showLetters` boolean to determine if letters should be visible or hidden
    shareCanvas2();
});

function showAdditionalInfoOnStats() {
    fetch('https://ordish.dk/woordle/word').then(res => res.text()).then(res => {
        let hiddenWord = document.getElementById("word");
        document.getElementById('after-completion').classList.remove("d-none");
        startCountdown();
        hiddenWord.textContent = res;
    });
}

let compShown = false;

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

function convertTreeToWordleFormat(woordleFeedback, word) {
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

function setGuessesLeft(userLeftArr, pcLeftArr) {
    let table = document.getElementById("comp-stats-id");

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

function showComparison(guesses) {
    /*
    if (!compShown) {
        let compCont = document.getElementById("comp-cont");
        compCont.classList.remove("hide");

        let userBoardContainer = document.getElementById('user-board');
        let computerBoardContainer = document.getElementById('computer-board');

        userBoardContainer.appendChild(userBoard.board);
        computerBoardContainer.appendChild(computerBoard.board);

        userBoard.setBoardDataFromDB(guesses);
        userBoard.focusFirstTileOnFirstEmpty();
        userBoard.freeze();
        userBoard.setCellContentSize("1");

        let userGuesses = {
            "guesses": []
        }

        for (let i = 0; i < guesses.length; ++i) {
            userGuesses["guesses"].push(convertTreeToWordleFormat(guesses[i].Feedback, guesses[i].GuessWord));
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

                    computerBoard.setBoardDataFromDB(guessesPC);
                    computerBoard.focusFirstTileOnFirstEmpty();
                    computerBoard.freeze();
                    computerBoard.setCellContentSize("1");

                    fetch('https://ordish.dk/woordle/user/analysis', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(pcGuesses),
                    }).then(res => res.json()).then(res => {
                        guessesPC = res;
                    }).then(() => {
                        setGuessesLeft(guessesUser, guessesPC);
                    });
                });
            });
        });

        compShown = true;
    }
    */
}


let user = null;
let stats = null;
document.getElementById("stats-modal").addEventListener("show.bs.offcanvas", () => {
    //if (stats.loaded) {
        //stats.animateAll();
    //}
});
fetch('https://ordish.dk/woordle/user').then(res => res.json()).then(use => {
    user = use;
    stats = new Stats(user.userID);
}).then(() => {
    fetch('https://ordish.dk/woordle/user/solvedtoday').then(res => res.json()).then(solved => {
        if (solved.solvedToday) {
            let guesses = solved.game;

            board.setBoardDataFromDB(guesses);
            board.focusFirstTileOnFirstEmpty();
            board.freeze();
            let feedback = board.getLettersWithFeedback();
            keyboard.setLetterFeedback(feedback);

            iziToast.show({
                title: 'Hej igen!',
                message: 'Du har allerede løst dagens ord!',
                position: 'topCenter',
                transitionIn: 'fadeInDown',
                transitionOut: 'fadeOutUp',
                transitionInMobile: 'fadeInDown',
                transitionOutMobile: 'fadeOutUp',
                theme: 'dark',
                displayMode: 'replace',
                progressBar: false,
            });

            showAdditionalInfoOnStats();
            showComparisonAfterCompletion(guesses);
            showStats();
        } else {
            /*
            let section = document.getElementsByClassName("after-completion")[0];

            section.style.display = "none";
            */

            fetch('https://ordish.dk/woordle/user/hasOngoingGame').then(res => res.json()).then(res => {
                if (res.hasOngoingGame) {
                    let guesses = res.prevGuesses

                    board.setBoardDataFromDB(guesses);
                    board.focusFirstTileOnFirstEmpty();
                    let feedback = board.getLettersWithFeedback();
                    keyboard.setLetterFeedback(feedback);
                    iziToast.show({
                        title: 'Hej igen!',
                        message: 'Du har en igangværende spil!',
                        position: 'topCenter',
                        transitionIn: 'fadeInDown',
                        transitionOut: 'fadeOutUp',
                        transitionInMobile: 'fadeInDown',
                        transitionOutMobile: 'fadeOutUp',
                        theme: 'dark',
                        displayMode: 'replace',
                        progressBar: false,
                    });
                }
            });
        }
    });
}).catch((error) => {
    console.error('Error:');
});

let helpShown = false;
document.getElementById('help-button').addEventListener('click', function () {
    if (!helpShown) {
        showHelp();
        helpShown = true;
    } else {
        hideHelp();
        helpShown = false;
    }
});

function showHelp() {
    let tile1 = document.getElementById("example1");
    let tile2 = document.getElementById("example2");
    let tile3 = document.getElementById("example3");

    tile1.classList.add("board-cell--correct");
    tile1.children[0].classList.add("board-cell__content--back");

    tile2.classList.add("board-cell--misplaced");
    tile2.children[0].classList.add("board-cell__content--back");

    tile3.classList.add("board-cell--incorrect");
    tile3.children[0].classList.add("board-cell__content--back");

    let example4 = document.getElementById("example4");

    let pattern = "ynmyn";
    for (let i = 0; i < pattern.length; i++) {
        const cell = example4.children[i];
        if (pattern[i] === "y") {
            cell.classList.add("board-cell--correct");
            cell.children[0].classList.add("board-cell__content--back");
        } else if (pattern[i] === "n") {
            cell.classList.add("board-cell--incorrect");
            cell.children[0].classList.add("board-cell__content--back");
        } else if (pattern[i] === "m") {
            cell.classList.add("board-cell--misplaced");
            cell.children[0].classList.add("board-cell__content--back");
        }
    }
}

function hideHelp() {
    window.dialog.close();
}

function showStats() {
    document.getElementById("statistics-button").click();
}

function hideStats() {
    document.getElementById("stats").classList.remove("modal-open");
}

let enterCheck = document.getElementById("enter-check");
let loading = false;

function toggleLoading() {
    loading = !loading;
    if (document.getElementById("enter-loading")) {
        let loading = document.getElementById("enter-loading");
        loading.remove();

        let check = document.getElementById("enter-check");
        check.style.display = "inline-flex";
    } else {
        let loading = document.createElement("md-circular-progress");
        loading.setAttribute("indeterminate", "");
        loading.setAttribute("id", "enter-loading");

        let check = document.getElementById("enter-check");
        check.style.display = "none";

        let container = check.parentElement;
        container.appendChild(loading);
    }
}

async function handleKeyPress(key) {
    try {
        if (loading) {
            return;
        }

        if (key.match(/^[a-zæøå]$/i)) {
            board.addLetter(key);
        }

        // If key is enter log it
        if (key === 'Enter' || key === "check" || key === "CHECK" || key === "ENTER") {
            toggleLoading();

            let shouldBreak = false;

            let inputWord = board.getCurrentWord();

            if (inputWord.length !== 5) {
                shouldBreak = true;
                board.shakeRecentWord();
                iziToast.show({
                    title: 'Ups.',
                    message: 'Ordet skal være præcis 5 bogstaver langt!',
                    position: 'topCenter',
                    transitionIn: 'fadeInDown',
                    transitionOut: 'fadeOutUp',
                    transitionInMobile: 'fadeInDown',
                    transitionOutMobile: 'fadeOutUp',
                    theme: 'dark',
                    displayMode: 'replace',
                    timeout: 4000,
                    progressBar: false,
                });
            }

            if (shouldBreak) {
                toggleLoading();
                return;
            }

            await fetch('https://ordish.dk/woordle/word/isvalid?word=' + inputWord).then(res => res.json()).then(res => {
                if (!res) {
                    shouldBreak = true;
                    board.shakeRecentWord();
                    iziToast.show({
                        title: 'Ups.',
                        message: inputWord + ' er ikke et ord!',
                        position: 'topCenter',
                        transitionIn: 'fadeInDown',
                        transitionOut: 'fadeOutUp',
                        transitionInMobile: 'fadeInDown',
                        transitionOutMobile: 'fadeOutUp',
                        theme: 'dark',
                        displayMode: 'replace',
                        timeout: 4000,
                        progressBar: false,
                    });
                    shouldBreak = true;
                }
            });

            if (shouldBreak) {
                toggleLoading();
                return;
            }

            /* Fetch the secret word from /woordle/word */
            let SECRET_WORD = await fetch('https://ordish.dk/woordle/word').then(res => res.text());
            let pattern = board.getLastWordFeedbackPattern(SECRET_WORD);

            board.setPattern(pattern);
            //board.selectNextCell();
            board.focusFirstTileOnFirstEmpty();

            let feedback = board.getLettersWithFeedback();
            keyboard.setLetterFeedback(feedback);

            let userID = user.userID;
            let wordLength = 5;

            console.log("Guess was: " + inputWord);

            await fetch('https://ordish.dk/woordle/user/submitGuess', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ userID, wordLength, guess: inputWord, feedback: pattern }),
            })
                .then(response => response.text())
                .then(data => console.log(data))
                .catch((error) => {
                    console.error('Error:', error);
                });


            if (pattern === "yyyyy") {
                iziToast.show({
                    title: 'Wow.',
                    message: 'Godt gået!',
                    position: 'topCenter',
                    transitionIn: 'fadeInDown',
                    transitionOut: 'fadeOutUp',
                    transitionInMobile: 'fadeInDown',
                    transitionOutMobile: 'fadeOutUp',
                    theme: 'dark',
                    displayMode: 'replace',
                    timeout: 2000,
                    backgroundColor: '#538d4e',
                    progressBar: false,
                    onClosing: function () {
                        showAdditionalInfoOnStats();
                        stats.fetchAndSetStats();
                        showStats(); // This function will be called after the toast is closed
                        fetch('https://ordish.dk/woordle/user/solvedtoday').then(res => res.json()).then(solved => {
                            if (solved.solvedToday) {
                                let guesses = solved.game;
                                showComparisonAfterCompletion(guesses);
                            }
                        });
                    }
                });
                toggleLoading();
                board.freeze();
            } else if (board.guessesLeft() === 0) {
                iziToast.show({
                    title: 'Øv.',
                    message: 'Prøv igen imorgen!',
                    position: 'topCenter',
                    transitionIn: 'fadeInDown',
                    transitionOut: 'fadeOutUp',
                    transitionInMobile: 'fadeInDown',
                    transitionOutMobile: 'fadeOutUp',
                    theme: 'dark',
                    displayMode: 'replace',
                    timeout: 2000,
                    progressBar: false,
                    onClosed: function () {
                        showAdditionalInfoOnStats();
                        stats.fetchAndSetStats();
                        showStats(); // This function will be called after the toast is closed

                        fetch('https://ordish.dk/woordle/user/solvedtoday').then(res => res.json()).then(solved => {
                            if (solved.solvedToday) {
                                let guesses = solved.game;
                                showComparisonAfterCompletion(guesses);
                            }
                        });
                    }
                });
                toggleLoading();
                board.freeze();
            } else {
                toggleLoading();
            }
        }

        // If key is backspace log it
        if (key === 'Backspace' || key === "backspace" || key === "BACKSPACE") {
            board.removeLetter();
        }
    } catch (error) {
        console.log(error);
    }
}

// Listen for keyup events on the document
document.onkeyup = (e) => {
    // If the key is in the alphabet or æøå (danish letters), log it
    handleKeyPress(e.key);

    if (e.key == '0') {
        document.getElementById('mmain').style.maxHeight = window.innerHeight - 56 + "px";
    }
}