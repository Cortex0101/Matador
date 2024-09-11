export class Keyboard {
    constructor() {
      this._keyboard = document.getElementById("keyboardID");
  
      // Loop through all rows of keyboard, and for each row, loop through all keys
      for (let row of this._keyboard.children) {
        for (let key of row.children) {
          // Add event listener to each key
          key.addEventListener("click", () => {
            // emit keyup event
            document.dispatchEvent(
              new KeyboardEvent("keyup", { key: key.innerText })
            );
          });
        }
      }
    }
  
    setLetterFeedback(feedback) {
      // Loop through all rows of keyboard, and for each row, loop through all keys
      for (let row of this._keyboard.children) {
        for (let keyNode of row.children) {
          let key = keyNode.innerText;
  
          let misFeed = feedback["misplacedLetters"];
  
          if (feedback["misplacedLetters"].includes(key)) {
              keyNode.classList.add("keyboard__key--misplaced");
          }
  
          if (feedback["incorrectLetters"].includes(key)) {
              keyNode.classList.add("keyboard__key--incorrect");
          }  
  
          if (feedback["correctLetters"].includes(key)) {
              keyNode.classList.add("keyboard__key--correct");
          }
        }
      }
    }
  
    clearFeedback() {
      // Loop through all rows of keyboard, and for each row, loop through all keys
      for (let row of this._keyboard.children) {
        for (let keyNode of row.children) {
          keyNode.classList.remove("keyboard__key--misplaced");
          keyNode.classList.remove("keyboard__key--incorrect");
          keyNode.classList.remove("keyboard__key--correct");
        }
      }
    }
  }