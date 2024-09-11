/**
 * Calculates the statistics and rating for a user's Wordle game.
 * @param {number} winPercentage - The win percentage of the user.
 * @param {number[]} guessDistribution - The distribution of guesses made by the user.
 * @param {number} winStreak - The current win streak of the user.
 * @param {number} bestStreak - The longest win streak of the user.
 * @param {number} averageGuesses - The average number of guesses made by the user.
 * @param {number} gamesPlayed - The total number of games played by the user.
 * @returns {number} The final rating for the user's Wordle game.
 */
'use strict';
var express = require('express');
var path = require('path');
const fs = require('fs');
var router = express.Router();
const db = require('../woordle_db').db;

function getAmountOfGamesPlayed(userID) {
    return new Promise((resolve, reject) => {
        db.execute(
            'SELECT COUNT(*) AS AmountOfGamesPlayed FROM Games WHERE UserID = ?',
            [userID],
            (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(rows[0].AmountOfGamesPlayed);
                }
            }
        );
    });
}

function getWinPercentage(userID, AMOUNT_OF_GAMES_PLAYED) {
    return new Promise((resolve, reject) => {
        db.execute(
            'SELECT COUNT(*) AS AmountOfWins FROM Guesses JOIN Games ON Guesses.GameID = Games.GameID WHERE Games.UserID = ? AND Feedback = ?',
            [userID, 'yyyyy'],
            (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    let WIN_PERCENTAGE = 0;
                    if (AMOUNT_OF_GAMES_PLAYED === 0) {
                        WIN_PERCENTAGE = 0;
                    } else {
                        WIN_PERCENTAGE = wonGameRows[0].AmountOfWins / AMOUNT_OF_GAMES_PLAYED;
                    }
                    resolve(WIN_PERCENTAGE);
                }
            }
        );
    });
}

function getGuessDistribution(userID) {
    return new Promise((resolve, reject) => {
        db.execute(
            'SELECT GuessNumber FROM Guesses JOIN Games ON Guesses.GameID = Games.GameID WHERE Games.UserID = ? AND Feedback = ?',
            [userID, 'yyyyy'],
            (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    let GUESS_DISTRIBUTION = [0, 0, 0, 0, 0, 0];
                    for (const row of rows) {
                        if (row.GuessNumber <= 6) {
                            GUESS_DISTRIBUTION[row.GuessNumber - 1]++;
                        }
                    }
                    resolve(GUESS_DISTRIBUTION);
                }
            }
        );
    });
}

function getAverageGuesses(GUESS_DISTRIBUTION, AMOUNT_OF_GAMES_PLAYED) {
    let AVERAGE_GUESSES = 0;
    for (let i = 0; i < GUESS_DISTRIBUTION.length; i++) {
        AVERAGE_GUESSES += (i + 1) * GUESS_DISTRIBUTION[i];
    }
    AVERAGE_GUESSES /= AMOUNT_OF_GAMES_PLAYED;
    return AVERAGE_GUESSES;
}

function getCurrentWinStreak(userID) {
    return new Promise((resolve, reject) => {
        db.execute(
            'SELECT * FROM Guesses JOIN Games ON Guesses.GameID = Games.GameID WHERE Games.UserID = ? AND WordLength = ? ORDER BY Games.CreatedAt DESC',
            [userID, 5],
            (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    let WIN_STREAK = 0;
                    let currentGameID = null;
                    let highestGuess = null;

                    for (const row of rows) {
                        if (currentGameID === null || row.GameID !== currentGameID) {
                            if (highestGuess !== null && highestGuess.Feedback !== 'yyyyy') {
                                break;
                            }
                            if (highestGuess !== null && highestGuess.Feedback === 'yyyyy') {
                                WIN_STREAK++;
                            }
                            currentGameID = row.GameID;
                            highestGuess = row;
                        } else if (row.GuessNumber > highestGuess.GuessNumber) {
                            highestGuess = row;
                        }
                    }

                    if (highestGuess !== null && highestGuess.Feedback === 'yyyyy') {
                        WIN_STREAK++;
                    }

                    resolve(WIN_STREAK);
                }
            }
        );
    });
}

function getLongestWinStreak(userID) {
    return new Promise((resolve, reject) => {
        db.execute(
            'SELECT * FROM Guesses JOIN Games ON Guesses.GameID = Games.GameID WHERE Games.UserID = ? AND WordLength = ? ORDER BY Games.CreatedAt DESC',
            [userID, 5],
            (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    let currentStreak = 0;
                    let BEST_STREAK = 0;
                    let currentGameID = null;
                    let highestGuess = null;

                    for (const row of rows) {
                        if (currentGameID === null || row.GameID !== currentGameID) {
                            if (highestGuess !== null && highestGuess.Feedback === 'yyyyy') {
                                currentStreak++;
                            } else {
                                if (currentStreak > BEST_STREAK) {
                                    BEST_STREAK = currentStreak;
                                }
                                currentStreak = 0;
                            }
                            currentGameID = row.GameID;
                            highestGuess = row;
                        } else if (row.GuessNumber > highestGuess.GuessNumber) {
                            highestGuess = row;
                        }
                    }

                    if (highestGuess !== null && highestGuess.Feedback === 'yyyyy') {
                        currentStreak++;
                    }

                    if (currentStreak > BEST_STREAK) {
                        BEST_STREAK = currentStreak;
                    }

                    resolve(BEST_STREAK);
                }
            }
        );
    });
}

function getDaysInARow(userID) {
    return new Promise((resolve, reject) => {
        db.execute(
            'SELECT DISTINCT DATE(Games.CreatedAt) AS Date FROM Games WHERE UserID = ? ORDER BY Date DESC LIMIT 1000',
            [userID],
            (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    let DAYS_IN_A_ROW = 0;
                    let currentDate = null;

                    for (const row of rows) {
                        if (currentDate === null || row.Date === currentDate) {
                            DAYS_IN_A_ROW++;
                            currentDate = row.Date;
                        } else {
                            break;
                        }
                    }

                    resolve(DAYS_IN_A_ROW);
                }
            }
        );
    });
}

function calculateWordleRating(winPercentage, guessDistribution, winStreak, bestStreak, averageGuesses, gamesPlayed) {
    const MaxAverage = 6;
    const BotAverage = 4.0146;
    const performance = 100 * (1 - ((averageGuesses - BotAverage) / (MaxAverage - BotAverage)));

    const uncertaintyScore = 50 * (1 - (gamesPlayed / (gamesPlayed + 50)));

    // FinalRating=max(0,min(100,PerformanceScore−UncertaintyScore))
    const finalRating = Math.max(0, Math.min(100, performance - uncertaintyScore)) * winPercentage;

    return finalRating;
}

router.get('/:userID/stats', async (req, res) => {
    try {
        const { userID } = req.params;

        // Get amount of games played
        const AMOUNT_OF_GAMES_PLAYED = await getAmountOfGamesPlayed(userID);

        // Get win percentage
        const WIN_PERCENTAGE = await getWinPercentage(userID, AMOUNT_OF_GAMES_PLAYED);

        // Get guess distribution
        const GUESS_DISTRIBUTION = await getGuessDistribution(userID);

        // Get average guesses
        const AVERAGE_GUESSES = getAverageGuesses(GUESS_DISTRIBUTION, AMOUNT_OF_GAMES_PLAYED);

        // Get current winstreak
        const WIN_STREAK = await getCurrentWinStreak(userID);

        // Get longest winstreak
        const BEST_STREAK = await getLongestWinStreak(userID);

        // Get rating
        const RATING = calculateWordleRating(
            WIN_PERCENTAGE,
            GUESS_DISTRIBUTION,
            WIN_STREAK,
            BEST_STREAK,
            AVERAGE_GUESSES,
            wonGameRows[0].AmountOfWins
        );

        // Get how many days in a row the user has played any game
        const DAYS_IN_A_ROW = await getDaysInARow(userID);

        res.send(
            JSON.stringify({
                AMOUNT_OF_GAMES_PLAYED,
                WIN_PERCENTAGE,
                GUESS_DISTRIBUTION,
                WIN_STREAK,
                BEST_STREAK,
                AVERAGE_GUESSES,
                DAYS_IN_A_ROW,
                RATING
            })
        );
    } catch (error) {
        logger.debug('Error:', { message: error.message, stack: error.stack });
        res.status(500).json({ error: 'Internal Server Error' });
    }
});

// Get the amount of games played by a user
router.get('/user/:userID/stats/gamesPlayed', async (req, res) => {
    const { userID } = req.params;

    const [gamesRows, gamesFields] = await db.execute(
        'SELECT COUNT(*) AS AmountOfGamesPlayed FROM Games WHERE UserID = ?',
        [userID]
    );

    res.json(gamesRows[0]);
});

router.get('/', function (req, res) {
    res.sendFile('/usr/local/lsws/Example/html/public/wordle2-dist/wordle2.html');
});

module.exports = router;