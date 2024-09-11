export class Board {
    constructor(n, setHeight = true) {
      this._board = this.createBoard(n);
  
      this._currentCell = this._board.firstElementChild.firstElementChild;
      this.focusFirstTileOnFirstEmpty();
  
      if (n === 4) {
        let maxWidth = 62 * 4 + 3 * 5;
        this._board.style.maxWidth = maxWidth + "px";
      }
  
      this._frozen = false;
  
      if (setHeight) {
        //this.setHeight();      
      } else {
        this.setCellContentSize("1");
      }
    }
  
    setHeight() {
      let topElement = document.createElement("div");
      topElement.style.position = "fixed";
      topElement.style.top = "0";
      document.body.appendChild(topElement);
  
      let bottomElement = document.createElement("div");
      bottomElement.style.position = "fixed";
      bottomElement.style.bottom = "0";
      document.body.appendChild(bottomElement);
  
      let viewportHeight = bottomElement.offsetTop - topElement.offsetTop;
  
      let keyboardHeight = document.getElementById("keyboardID").offsetHeight;
      let headerHeight = document.getElementById("head").offsetHeight;
  
      let boardHeight = viewportHeight - keyboardHeight - headerHeight - 20;
  
      this._board.style.maxHeight = boardHeight + "px";
      document.getElementById("board-container-5").style.maxHeight = boardHeight + "px";
  
      document.body.removeChild(topElement);
      document.body.removeChild(bottomElement);
    }
  
    get currentCell() {
      return this._currentCell;
    }
  
    get board() {
      return this._board;
    }
  
    selectCell(cell) {
      this._currentCell.classList.remove("board-cell--selected");
      this._currentCell = cell;
      this._currentCell.classList.add("board-cell--selected");
    }
  
    createBoard(n) {
      const board = document.createElement("div");
      board.classList.add("board");
  
      for (let i = 0; i < 6; i++) {
        const row = this.createRow(n);
        board.appendChild(row);
      }
  
      return board;
    }
  
    createRow(n) {
      const row = document.createElement("div");
      row.classList.add("board-row");
      row.style.gridTemplateColumns = "repeat(" + n + ", 1fr)";
  
      for (let i = 0; i < n; i++) {
        const cell = this.createCell();
        row.appendChild(cell);
      }
  
      return row;
    }
  
    createCell() {
      const cell = document.createElement("div");
      cell.classList.add("board-cell");
  
      let letterElement = this.createLetter("");
      cell.appendChild(letterElement);
  
      cell.onclick = () => {
        const activeRow = this._currentCell.parentElement;
  
        if (activeRow === cell.parentElement) {
          this.selectCell(cell);
        }
      }
  
      cell.onanimationend = () => {
        cell.classList.remove("letter-added");
      };
  
      return cell;
    }
  
    setCellContentSize(size) {
      for (let row of this._board.children) {
        for (let cell of row.children) {
          //cell.children[0].style.fontSize = size + "rem";
          if (size == "1") {
            cell.children[0].classList.remove("board-cell__content");
            cell.children[0].classList.add("board-cell__content--sm");
          }
        }
      }
    }
  
    selectNextCell() {
      if (this._currentCell) {
        this._currentCell.classList.remove("board-cell--selected");
      }
  
      const nextCell = this._currentCell.nextElementSibling;
      if (nextCell) {
        nextCell.classList.add("board-cell--selected");
        this._currentCell = nextCell;
      } else {
        const nextRow = this._currentCell.parentElement.nextElementSibling;
        if (nextRow) {
          const firstCell = nextRow.firstElementChild;
          firstCell.classList.add("board-cell--selected");
          this._currentCell = firstCell;
        }
      }
    }
  
    selectPreviousCell() {
      const previousCell = this._currentCell.previousElementSibling;
      if (previousCell) {
        this._currentCell.classList.remove("board-cell--selected");
        previousCell.classList.add("board-cell--selected");
        this._currentCell = previousCell;
      } else {
        const previousRow =
          this._currentCell.parentElement.previousElementSibling;
        if (previousRow) {
          this._currentCell.classList.remove("board-cell--selected");
          const lastCell = previousRow.lastElementChild;
          lastCell.classList.add("board-cell--selected");
          this._currentCell = lastCell;
        }
      }
    }
  
    createLetter(letter) {
      const letterElement = document.createElement("div");
      letterElement.classList.add("board-cell__content");
      letterElement.textContent = letter;
  
      return letterElement;
    }
  
    addLetter(letter) {
      if (this._frozen) {
        return;
      }
  
      if (this._currentCell) {
        // Check if current cell is last cell in row
        const isLastCellInRow = this._currentCell.nextElementSibling === null;
  
        if (isLastCellInRow) {
          this._currentCell.children[0].textContent = letter;
          this._currentCell.classList.add("letter-added");
        } else {
          this._currentCell.children[0].textContent = letter;
          this._currentCell.classList.add("letter-added");
          this.selectNextCell();
        }
      }
    }
  
    removeLetter() {
      if (this._frozen) {
        return;
      }
  
      if (this._currentCell) {
        let row = this._currentCell.parentElement;
        let index = Array.from(row.children).indexOf(this._currentCell);
  
        let content = this._currentCell.children[0].textContent;
  
        if (this._currentCell.children[0].textContent !== "") {
          this._currentCell.children[0].textContent = "";
        } else {
          let prevCell = this._currentCell.previousElementSibling;
  
          if (prevCell) {
            if (prevCell.children[0].textContent !== "") {
              prevCell.children[0].textContent = "";
            }
  
            if (index > 0) {
              this.selectPreviousCell();
            }
          }
        }
      }
    }
  
    /*
      example pattern ynmyn
  
      y is correct
      n is wrong
      m is misplaced
    */
    setPattern(pattern) {
      const currentRow = this._currentCell.parentElement;
  
      const patternArray = pattern.split("");
  
      for (let i = 0; i < patternArray.length; i++) {
        const cell = currentRow.children[i];
        if (patternArray[i] === "y") {
          cell.classList.add("board-cell--correct");
          cell.children[0].classList.add("board-cell__content--back");
        } else if (patternArray[i] === "n") {
          cell.classList.add("board-cell--incorrect");
          cell.children[0].classList.add("board-cell__content--back");
        } else if (patternArray[i] === "m") {
          cell.classList.add("board-cell--misplaced");
          cell.children[0].classList.add("board-cell__content--back");
        }
      }
    }
  
    getLettersWithFeedback() {
      let correctLetters = [];
      let misplacedLetters = [];
      let incorrectLetters = [];
  
      for (let row of this._board.children) {
        for (let cell of row.children) {
          if (cell.classList.contains("board-cell--correct")) {
            correctLetters.push(cell.children[0].textContent.toUpperCase());
          } else if (cell.classList.contains("board-cell--misplaced")) {
            misplacedLetters.push(cell.children[0].textContent.toUpperCase());
          } else if (cell.classList.contains("board-cell--incorrect")) {
            incorrectLetters.push(cell.children[0].textContent.toUpperCase());
          }
        }
      }
  
      return {
        correctLetters,
        misplacedLetters,
        incorrectLetters,
      };
    }
  
    getLastUncheckedRowWord() {
      let lastUncheckedRow = this._currentCell.parentElement;
  
      let word = "";
  
      for (let cell of lastUncheckedRow.children) {
        if (cell.children[0].textContent === "") {
          break;
        } else {
          word += cell.children[0].textContent;
        }
      }
  
      return word.toUpperCase();
    }
  
    getRecentWord() {
      let currentRow = this._currentCell.parentElement;
  
      let lastRow = currentRow.previousElementSibling;
  
      if (lastRow) {
        // If index of last row is 4 then check if input has been filled on the 5th row
        if (Array.from(lastRow.parentElement.children).indexOf(lastRow) === 4) {
          if (lastRow.parentElement.lastElementChild.children[0].textContent !== "") {
            let word = "";
  
            for (let cell of lastRow.parentElement.lastElementChild.children) {
              word += cell.children[0].textContent;
            }
  
            return word.toUpperCase();
          }
        } else {
          let word = "";
  
          for (let cell of lastRow.children) {
            word += cell.children[0].textContent;
          }
  
          return word.toUpperCase();
        }
      } else {
        let word = "";
  
        for (let cell of currentRow.children) {
          word += cell.children[0].textContent;
        }
  
        return word.toUpperCase();
      }
    }
  
    getCurrentWord() {
      const row = this._currentCell.parentElement;
  
      let word = "";
  
      for (let cell of row.children) {
        if (cell.children[0].textContent === "") {
          break;
        } else {
          word += cell.children[0].textContent;
        }
      }
  
      return word.toUpperCase();
    }
  
    getLastWordFeedbackPattern2(secret_word) {
      let rowWord = this.getLastUncheckedRowWord();
  
      let secret_word_cpy = (' ' + secret_word).slice(1);
  
      let pattern = "";
  
      for (let i = 0; i < rowWord.length; i++) {
        if (rowWord[i] === secret_word[i]) {
          pattern += "y";
        } else if (secret_word.includes(rowWord[i])) {
          pattern += "m";
        } else {
          pattern += "n";
        }
      }
  
      return pattern;
    }
  
    getLastWordFeedbackPattern(secret_word) {
      let guess = this.getLastUncheckedRowWord();
      let solution = (' ' + secret_word).slice(1);
  
      let matches = {};
    let colors = Array(guess.length).fill('n');
  
    for (let index = 0; index < guess.length; index++) {
      if (guess[index] === solution[index]) {
        colors[index] = 'y';
        matches[guess[index]] = (matches[guess[index]] || 0) + 1;
      } else if (solution.includes(guess[index])) {
        colors[index] = 'm';
      }
    }
  
    if (Object.keys(matches).length) {
      let countsInSolution = Array.from(solution).reduce((acc, curr) => {
        acc[curr] = (acc[curr] || 0) + 1;
        return acc;
      }, {});
  
      for (let index = 0; index < guess.length; index++) {
        if (guess[index] !== solution[index] && matches[guess[index]] === countsInSolution[guess[index]]) {
          colors[index] = 'n';
        }
      }
    }
  
    // convert to string
    const pattern = colors.join('');
  
    return pattern;
  }

  clear() {
    for (let row of this._board.children) {
      for (let cell of row.children) {
        cell.children[0].textContent = "";
        cell.children[0].classList.remove("board-cell__content--back");
        cell.classList.remove("board-cell--correct");
        cell.classList.remove("board-cell--incorrect");
        cell.classList.remove("board-cell--misplaced");
      }
    }
  }
      
  
    guessesLeft() {
      let guessesLeft = 0;
  
      for (let row of this._board.children) {
        for (let cell of row.children) {
          if (cell.children[0].textContent === "") {
            guessesLeft++;
          }
        }
      }
  
      return guessesLeft;
    }
  
    focusFirstTileOnFirstEmpty() {
      for (let row of this._board.children) {
        for (let cell of row.children) {
          if (cell.children[0].textContent === "") {
            this.selectCell(cell);
            return;
          }
        }
      }
    }
  
    freeze() {
      this._frozen = true;
      this._currentCell.classList.remove("board-cell--selected");
    }
  
    unfreeze() {
      this._frozen = false;
      this._currentCell.classList.add("board-cell--selected");
    }
  
    getLastFilledRowIndex() {
      for (let i = this._board.children.length - 1; i >= 0; --i) {
        const row = this._board.children[i];
        for (let cell of row.children) {
          if (cell.children[0].textContent !== "") {
            return i + 1;
          }
        }
      }
    }
  
    shakeRecentWord() {
      let row = this._currentCell.parentElement;
  
      // Create a shaking back and fourth effect on the row
      row.classList.add("shake-horizontal");
  
      row.onanimationend = () => {
        row.classList.remove("shake-horizontal");
      };
    }
  
    getGuess(rowIndex) {
      const row = this._board.children[rowIndex];
      let guess = "";
  
      for (let cell of row.children) {
        guess += cell.children[0].textContent;
      }
  
      return guess;
    }
  
    getCorrectLetters(rowIndex) {
      const row = this._board.children[rowIndex];
      let correctLetters = [];
  
      let i = 1;
      for (let cell of row.children) {
        if (cell.classList.contains("board-cell--correct")) {
          correctLetters.push({ letter: cell.children[0].textContent, position: i });
        }
        ++i;
      }
  
      return correctLetters;
    }
  
    getIncorrectLetters(rowIndex) {
      const row = this._board.children[rowIndex];
      let incorrectLetters = [];
  
      let i = 1;
      for (let cell of row.children) {
        if (cell.classList.contains("board-cell--incorrect")) {
          incorrectLetters.push(cell.children[0].textContent);
        }
        ++i;
      }
  
      return incorrectLetters;
    }
  
    getMisplacedLetters(rowIndex) {
      const row = this._board.children[rowIndex];
      let misplacedLetters = [];
  
      let i = 1;
      for (let cell of row.children) {
        if (cell.classList.contains("board-cell--misplaced")) {
          misplacedLetters.push({ letter: cell.children[0].textContent, position: i });
        }
        ++i;
      }
  
      return misplacedLetters;
    }
  
    getBoardData() {
      const boardData = [];
      const lastRow = this.getLastFilledRowIndex();
  
      for (let i = 0; i < lastRow; ++i) {
        const guess = this.getGuess(i);
        const correctLetters = this.getCorrectLetters(i);
        const incorrectLetters = this.getIncorrectLetters(i);
        const misplacedLetters = this.getMisplacedLetters(i);
  
        boardData.push({ guess: guess, correctlyPlacedLetters: correctLetters, absentLetters: incorrectLetters, misplacedLetters: misplacedLetters });
      }
  
      console.log(boardData);
  
      return boardData;
    }
  
    /* 
      example
      word = "BARES"
      feedback = "yynnn"
      rowIndex = 0
    */
    setWord(word, feedback, rowIndex) {
      const row = this._board.children[rowIndex];
  
      for (let i = 0; i < word.length; i++) {
        const cell = row.children[i];
  
        if (feedback[i] === "y") {
          cell.classList.add("board-cell--correct");
          cell.children[0].classList.add("board-cell__content--back");
          cell.children[0].textContent = word[i];
        } else if (feedback[i] === "n") {
          cell.classList.add("board-cell--incorrect");
          cell.children[0].classList.add("board-cell__content--back");
          cell.children[0].textContent = word[i];
        } else if (feedback[i] === "m") {
          cell.classList.add("board-cell--misplaced");
          cell.children[0].classList.add("board-cell__content--back");
          cell.children[0].textContent = word[i];
        }
      }
    }
  
  
    setBoardDataFromDB(guesses) {
      for (let i = 0; i < guesses.length; ++i) {
        const guess = guesses[i].GuessWord;
        let feedback = guesses[i].Feedback;
        const rowIndex = i;
  
        this.setWord(guess, feedback, rowIndex);
      }
    }
  }

  /*
  function createAllBoards() {
    const NUM_BOARDS = 4;
    const MIN_BOARD_SIZE = 4;
  
    let BOARDS = [];
  
    for (let i = MIN_BOARD_SIZE; i < MIN_BOARD_SIZE + NUM_BOARDS; i++) {
      const board = new Board(i);
      let boardContainer = document.getElementById(`board-container-${i}`);
      boardContainer.appendChild(board.board);
      BOARDS.push(board);
    }
  
    return BOARDS;
  }
  */