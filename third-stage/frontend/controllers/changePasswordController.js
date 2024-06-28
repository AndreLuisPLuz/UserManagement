let strengthRequirementMet = false;
let passwordsMatch = false;

const evaluatePasswordStrength = () => {
    const passwordInput = document.getElementById("password-field");
    const password = passwordInput.value;

    if (password === null)
        return;

    let strength = 1;

    if (/[A-Z]+/.test(password)) strength++;
    if (/[a-z]+/.test(password)) strength++;
    if (/\d+/.test(password)) strength++;
    if (password.length >= 8) strength++;

    let backgroundClass;
    let fillValue;

    switch(strength) {
        case 1:
            backgroundClass = "bg-danger";
            fillValue = 20;
            break;
        case 2:
            backgroundClass = "bg-warning";
            fillValue = 40;
            break;
        case 3:
            backgroundClass = "bg-secondary";
            fillValue = 60;
            break;
        case 4:
            backgroundClass = "bg-info";
            fillValue = 80;
            break;
        case 5:
            backgroundClass = "bg-success";
            fillValue = 100;
            break;
    }

    const strengthBar = document.getElementById("password-strength");
    strengthBar.classList = `progress-bar ${backgroundClass}`;
    strengthBar.setAttribute("aria-valuenow", fillValue.toString());
    strengthBar.setAttribute("style", `height: 100%; width: ${fillValue}%;`);

    strengthRequirementMet = (strength == 5);
    updateWarning();
}

const evaluatePasswordsMatch = () => {
    const passwordField = document.getElementById("password-field");
    const confirmPasswordField = document.getElementById("confirm-password-field");

    passwordsMatch = (passwordField.value == confirmPasswordField.value);
    updateWarning();
}

const updateWarning = () => {
    const warning = document.getElementById("password-warning");
    warning.innerText = "";

    warning.innerText += strengthRequirementMet
        ? ""
        : "Sua senha não é forte o suficiente. "
    
    warning.innerText += passwordsMatch
        ? ""
        : "Senhas não se encaixam."
}

const verifyPassword = () => {
    evaluatePasswordStrength();
    evaluatePasswordsMatch();

    if (strengthRequirementMet && passwordsMatch) {
        // Código para mudança de senha...

        window.open("../views/service-list.html");
    }
}

const passwordField = document.getElementById("password-field");
passwordField.addEventListener("change", evaluatePasswordStrength);

const confirmPasswordField = document.getElementById("confirm-password-field");
confirmPasswordField.addEventListener("change", evaluatePasswordsMatch);

const button = document.getElementById("send");
button.addEventListener("click", verifyPassword);