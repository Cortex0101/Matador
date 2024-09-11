// import js-cookie 
import Cookies from 'js-cookie';

// Get the current theme from the html element
// if darkTheme cookie has never been set, set it to dark
// if darkTheme cookie has been set, set the theme to the value of the cookie
let enableLight = Cookies.get('wordle2_themePreference') === 'light';
let enableColorBlindMode = Cookies.get('wordle2_colorBlindMode') === 'true';    
let switchEnterBackspaces = Cookies.get('wordle2_switchEnterBackspace') === 'true';

if (enableLight) {
    document.documentElement.removeAttribute("data-bs-theme");
    document.getElementById("toggle-dark-theme-btn").checked = true;
} else {
    document.documentElement.setAttribute("data-bs-theme", "dark");
}

if (enableColorBlindMode) {
    document.documentElement.setAttribute("data-color-blind", "true");
    document.getElementById("high-contrast-btn").checked = true;
} else {
    document.documentElement.removeAttribute("data-color-blind");
}

if (switchEnterBackspaces) {
    switchEnterBackspace(!switchEnterBackspaces);
    document.getElementById("switch-btns-btn").checked = true;
} 

let darkModeBtn = document.getElementById("toggle-dark-theme-btn");

darkModeBtn.addEventListener("click", () => {
    // if the button was enbaled, enable data-bs-theme="dark" on the html element
    let checked = darkModeBtn.checked;
    if (checked) {
        document.documentElement.removeAttribute("data-bs-theme");
        Cookies.set('wordle2_themePreference', 'light', { expires: 365, secure: true, sameSite: 'strict' });
    } else {
        document.documentElement.setAttribute("data-bs-theme", "dark");
        Cookies.set('wordle2_themePreference', 'dark', { expires: 365, secure: true, sameSite: 'strict' });
    }
});

let colorBlindBtn = document.getElementById("high-contrast-btn");

colorBlindBtn.addEventListener("click", () => {
    let checked = colorBlindBtn.checked;
    if (checked) {
        document.documentElement.setAttribute("data-color-blind", "true");
        Cookies.set('wordle2_colorBlindMode', 'true', { expires: 365, secure: true, sameSite: 'strict' });
    } else {
        document.documentElement.removeAttribute("data-color-blind");
        Cookies.set('wordle2_colorBlindMode', 'false', { expires: 365, secure: true, sameSite: 'strict' });
    }
});

let switchBtnsBtn = document.getElementById("switch-btns-btn");

function switchEnterBackspace(enterOnLeft) {
    // select last row of keyboard (.keyboard-row)
    const keyboardRow = document.querySelector(".keyboard-row:last-child");
    const enterButton = document.getElementById('enter-check');
    const backspaceButton = document.getElementById('backspace-btn');

    // Remove buttons from the DOM
    enterButton.remove();
    backspaceButton.remove();

    // Append them in the order based on the enterOnLeft boolean
    if (enterOnLeft) {
        keyboardRow.insertBefore(enterButton, keyboardRow.firstChild);
        keyboardRow.appendChild(backspaceButton);
    } else {
        keyboardRow.appendChild(enterButton);
        keyboardRow.insertBefore(backspaceButton, keyboardRow.firstChild);
    }
}

switchBtnsBtn.addEventListener("click", () => {
    // if the button was enbaled, enable data-bs-theme="dark" on the html element
    let checked = switchBtnsBtn.checked;

    if (checked) {
        switchEnterBackspace(!checked);
        Cookies.set('wordle2_switchEnterBackspace', 'true', { expires: 365, secure: true, sameSite: 'strict' });
    } else {
        switchEnterBackspace(!checked);
        Cookies.set('wordle2_switchEnterBackspace', 'false', { expires: 365, secure: true, sameSite: 'strict' });
    }
});