import usersData from "../views/resources/temp/users.json" with { type: "json" };

let userMatches = false;
let passwordMatches = false;

function checkLogin(){

    const userInput = document.getElementById("login-input").value;
    const passwordInput = document.getElementById("password").value;

    const validateUser = usersData.data.find((m) => m.username == userInput);
    const validatePassword = usersData.data.find((u) => u.password == passwordInput);

    userMatches = (validateUser != null);
    passwordMatches = (validatePassword != null);

    updateMessageState();
}

function updateMessageState() {
    const alert = document.getElementById("alert"); 
    const message = document.getElementById("message");

    message.innerText = "";
    message.innerText += userMatches
        ? ""
        : "Usuário não encontrado. ";

    message.innerText  += passwordMatches
        ? ""
        : "Senha inválida.";
    
    alert.style.display = (userMatches && passwordMatches)
        ? "none"
        : "block";
}

const submitButton = document.getElementById("change-button");
submitButton.addEventListener("click", checkLogin);

const form = document.getElementById("form-submit");
form.addEventListener("submit", e => {
    e.preventDefault();
});
