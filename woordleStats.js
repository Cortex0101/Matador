class DefaultData {
    constructor() {
        this._gamesPlayed = 63;
        this._winPercentage = 0.9523809523809523;
        this._guessDistribution = [1, 1, 12, 26, 12, 8];
        this._currentStreak = 25;
        this._bestStreak = 29;
        this._averageGuess = 3.984126984126984;
        this._rating = 75.05484048534024;
        this._daysInARow = 63;
    }

    get gamesPlayed() {
        return this._gamesPlayed;
    }

    get gamesWon() {
        return this._gamesWon;
    }

    get gamesLost() {
        return this._gamesLost;
    }

    get winPercentage() {
        return this._winPercentage;
    }

    get rating() {
        return this._rating;
    }

    get currentStreak() {
        return this._currentStreak;
    }

    get bestStreak() {
        return this._bestStreak;
    }

    get averageGuess() {
        return this._averageGuess;
    }

    get guessDistribution() {
        return this._guessDistribution;
    }
}

export class Stats {
    constructor(userID) {
        this._userID = userID;
        this._gamesPlayed = document.getElementById("gamesPlayed");
        this._winPercentage = document.getElementById("winPercentage");
        this._daysRow = document.getElementById("daysRow");
        this._currentStreak = document.getElementById("currentStreak");
        this._bestStreak = document.getElementById("bestStreak");
        this._averageGuess = document.getElementById("averageGuess");
        this._chart = document.getElementById("bar-chart");
        this._loaded = false;

        this.fetchAndSetStats();

        this.ANIMATED_TIME = 1000;

        /*
        this._datePicker = document.getElementById("date-picker");
        this._datePicker.datepicker({
            format: 'mm/dd/yyyy',
            startDate: '-3d',
            daysOfWeekDisabled: '0,6',
        });
        */
    }

    fetchAndSetStats() {
        fetch("https://ordish.dk/woordle/user/stats?userID=" + this._userID)
            .then(response => response.json())
            .then(data => {
                // {"AMOUNT_OF_GAMES_PLAYED":4,"WIN_PERCENTAGE":0.75,"GUESS_DISTRIBUTION":[1,0,0,2,0,0],"WIN_STREAK":1,"BEST_STREAK":2,"AVERAGE_GUESSES":2.25,"RATING":91.75}
                //throw new Error("No data");
                this.gamesPlayed = data.AMOUNT_OF_GAMES_PLAYED;
                this.winPercentage = data.WIN_PERCENTAGE;
                this.daysRow = data.DAYS_IN_A_ROW;
                this.currentStreak = data.WIN_STREAK;
                this.bestStreak = data.BEST_STREAK;
                this.averageGuess = data.AVERAGE_GUESSES;
                this.guessDistribution = data.GUESS_DISTRIBUTION;
                this.rating = data.RATING;
                this._loaded = true;
            })
            .catch(error => {
                console.error(error);

                // Set default values
                const defaultData = new DefaultData();
                this.gamesPlayed = defaultData.gamesPlayed;
                this.winPercentage = defaultData.winPercentage;
                this._daysRow.textContent = defaultData.daysInARow;
                this.currentStreak = defaultData.currentStreak;
                this.bestStreak = defaultData.bestStreak;
                this.averageGuess = defaultData.averageGuess;
                this.guessDistribution = defaultData.guessDistribution;
                this.rating = defaultData.rating;
            });
    }

    get loaded() {
        return this._loaded;
    }

    set gamesPlayed(gamesPlayed) {
        this._gamesPlayed.textContent = gamesPlayed;
    }

    set gamesWon(gamesWon) {
        this._gamesWon.textContent = gamesWon;
    }

    set daysRow(daysRow) {
        this._daysRow.textContent = daysRow;
    }

    set gamesLost(gamesLost) {
        this._gamesLost.textContent = gamesLost;
    }

    set winPercentage(winPercentage) {
        let percentage = winPercentage * 100;
        // Remove any decimal places
        percentage = Math.floor(percentage);
        this._winPercentage.textContent = percentage;
    }

    set currentStreak(currentStreak) {
        this._currentStreak.textContent = currentStreak;
    }

    set bestStreak(bestStreak) {
        this._bestStreak.textContent = bestStreak;
    }

    set averageGuess(averageGuess) {
        // Only 2 decimal places
        if (averageGuess != null) {
            averageGuess = averageGuess.toFixed(2);
            this._averageGuess.textContent = averageGuess;
        } else {
            this._averageGuess.textContent = "0";
        }
    }

    /*
    set guessDistribution(guessDistribution) {
        let max = 0;

        for (let i = 0; i < guessDistribution.length; i++) {
            let guess = guessDistribution[i];
            if (guess > max) {
                max = guess;
            }
        }

        for (let i = 0; i < guessDistribution.length; i++) {
            let guesses = guessDistribution[i];
            let percentage = Math.max(guesses / max * 100, 6);
            const row = this._chart.querySelectorAll('tr')[i];
            const percentageDiv = row.querySelector('.percentage');
            const amountDiv = row.querySelector('.amount');

            if (percentage > 0) {
                let span = document.createElement('span');
                span.style.width = `${percentage}%`;
                span.textContent = `${guesses}%`;
                percentageDiv.appendChild(span);
            } else {
                percentageDiv.textContent = '0%';
            }
            amountDiv.textContent = guesses;
        }

        this._guessDistribution = guessDistribution;
    }
    */

    set guessDistribution(guessDistribution) {
        let total = 0;

        for (let i = 0; i < guessDistribution.length; i++) {
            total += guessDistribution[i];
        }

        for (let i = 0; i < guessDistribution.length; i++) {
            let guesses = guessDistribution[i];
            let percentage = Math.max(guesses / total * 100);
            const row = this._chart.querySelectorAll('tr')[i];
            const percentageDiv = row.querySelector('.percentage');
            const amountDiv = row.querySelector('.amount');

            if (percentage > 0) {
                let span = document.createElement('span');
                span.style.width = `${percentage}%`;
                span.textContent = `${percentage.toFixed(1)}%`;
                percentageDiv.appendChild(span);
            } else {
                percentageDiv.textContent = '0%';
            }
            amountDiv.textContent = guesses;
        }

        this._guessDistribution = guessDistribution;
    }

    set rating(ratin) {
        const arrow = document.getElementById('arrow');
        const ratingBarWidth = document.getElementById('rating-b').offsetWidth;
        const arrowPosition = (ratin / 100) * ratingBarWidth;

        arrow.style.left = `${arrowPosition}px`;
    }

    startCountAnimation(element, targetValue, duration, precise = false) {
        const startTime = performance.now();
        const startValue = 0;

        // Easing function for smooth animation
        function easeInOut(t) {
            return t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
        }

        function updateCounter(currentTime) {
            const elapsedTime = currentTime - startTime;
            const progress = Math.min(elapsedTime / duration, 1);
            const easedProgress = easeInOut(progress);
            const animatedValue = startValue + (targetValue - startValue) * easedProgress;

            element.textContent = Math.round(animatedValue);

            if (progress < 1) {
                requestAnimationFrame(updateCounter);
            } else {
                if (precise) {
                    element.textContent = targetValue;
                }
            }
        }

        requestAnimationFrame(updateCounter);
    }

    _animateGamesPlayed() {
        const gamesPlayed = this._gamesPlayed;
        this.startCountAnimation(gamesPlayed, this._gamesPlayed.textContent, this.ANIMATED_TIME);
    }

    _animateWinPercentage() {
        const winPercentage = this._winPercentage;
        this.startCountAnimation(winPercentage, this._winPercentage.textContent, this.ANIMATED_TIME);
    }

    _animateDaysRow() {
        const daysRow = this._daysRow;
        this.startCountAnimation(daysRow, this._daysRow.textContent, this.ANIMATED_TIME);
    }

    _animateCurrentStreak() {
        const currentStreak = this._currentStreak;
        this.startCountAnimation(currentStreak, this._currentStreak.textContent, this.ANIMATED_TIME);
    }

    _animateBestStreak() {
        const bestStreak = this._bestStreak;
        this.startCountAnimation(bestStreak, this._bestStreak.textContent, this.ANIMATED_TIME);
    }

    _animateAverageGuess() {
        const averageGuess = this._averageGuess;
        this.startCountAnimation(averageGuess, this._averageGuess.textContent, this.ANIMATED_TIME, true);
    }

    _animateGuessDistribution() {
        for (let i = 0; i < this._guessDistribution.length; i++) {
            let guesses = this._guessDistribution[i];
            const row = this._chart.querySelectorAll('tr')[i];
            const percentageDiv = row.querySelector('.percentage');
            const amountDiv = row.querySelector('.amount');
            let element = percentageDiv.querySelector('span');

            if (element == null) {
                continue;
            }

            // element.style.left;
            const targetValueWidth = parseInt(element.style.width);
            const targetValue = parseInt(amountDiv.textContent);
            const duration = this.ANIMATED_TIME;
    
            const startTime = performance.now();
            const startValue = 0;
    
            // Easing function for smooth animation
            function easeInOut(t) {
                return t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
            }
    
            function updateCounter(currentTime) {
                const elapsedTime = currentTime - startTime;
                const progress = Math.min(elapsedTime / duration, 1);
                const easedProgress = easeInOut(progress);
                const animatedValue = startValue + (targetValue - startValue) * easedProgress;
                const animatedValueWidth = startValue + (targetValueWidth - startValue) * easedProgress;
    
                element.style.width = Math.round(animatedValueWidth) + "%";
                amountDiv.textContent = Math.round(animatedValue);
    
                if (progress < 1) {
                    requestAnimationFrame(updateCounter);
                }
            }
    
            requestAnimationFrame(updateCounter);
        }
    }

    _animateRating() {
        const element = document.getElementById('arrow');
        // element.style.left;
        const targetValue = parseInt(element.style.left);
        const duration = this.ANIMATED_TIME;

        const startTime = performance.now();
        const startValue = 0;

        // Easing function for smooth animation
        function easeInOut(t) {
            return t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
        }

        function updateCounter(currentTime) {
            const elapsedTime = currentTime - startTime;
            const progress = Math.min(elapsedTime / duration, 1);
            const easedProgress = easeInOut(progress);
            const animatedValue = startValue + (targetValue - startValue) * easedProgress;

            element.style.left = Math.round(animatedValue) + "px";

            if (progress < 1) {
                requestAnimationFrame(updateCounter);
            }
        }

        requestAnimationFrame(updateCounter);
    }

    animateAll() {
        this._animateGamesPlayed();
        this._animateWinPercentage();
        this._animateDaysRow();
        this._animateCurrentStreak();
        this._animateBestStreak();
        this._animateAverageGuess();
        this._animateGuessDistribution();
        this._animateRating();
    }
}