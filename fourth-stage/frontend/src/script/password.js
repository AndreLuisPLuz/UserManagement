// Adicione isto ao seu arquivo password.js
document.addEventListener("DOMContentLoaded", function () {
    const passwordInput = document.getElementById("password");
    const repeatPasswordInput = document.getElementById("repeat_password");
    const matchText = document.getElementById("match-text");
    const errorMessage = document.getElementById("error-message");

    function isGood(password) {
        var password_strength = document.getElementById("password-text");

        if (password.length == 0) {
            password_strength.innerHTML = "";
            return;
        }

        var regex = new Array();
        regex.push("[A-Z]");
        regex.push("[a-z]");
        regex.push("[0-9]");
        regex.push("[$@$!%*#?&]");

        var passed = 0;

        if (password.length >= 8) {
            passed++;
        }

        for (var i = 0; i < regex.length; i++) {
            if (new RegExp(regex[i]).test(password)) {
                passed++;
            }
        }

        var strength = "";
        switch (passed) {
            case 0:
            case 1:
            case 2:
                strength = "<small class='progress-bar bg-danger' >Fraca</small>";
                break;
            case 3:
                strength = "<small class='progress-bar bg-warning' >Média</small>";
                break;
            case 4:
                strength = "<small class='progress-bar bg-success' >Forte</small>";
                break;
            case 5:
                strength = "<small class='progress-bar bg-success' >Muito Forte</small>";
                break;
            default:
                break;
        }
        password_strength.innerHTML = strength;
        return passed;
    }


    function checkPasswordMatch() {
        var password = passwordInput.value;
        var repeatPassword = repeatPasswordInput.value;

        if (password !== repeatPassword && repeatPassword != "") 
            return false;
        else 
            return true;
        
    }

    async function changePassword() {
        const password = passwordInput.value;

        if(isGood(passwordInput.value) < 4 && checkPasswordMatch()){
            errorMessage.style.display = "block";
            errorMessage.innerText = "Senha não é forte o suficiente.";
            return;
        }
        
        if(!checkPasswordMatch()){
            errorMessage.style.display = "block";
            errorMessage.innerText = "As senhas não coincidem.";
            
        }

        const token = localStorage.getItem("token");
        console.log("TOKEN");
        console.log(token);
        
        const headers = new Headers();
        headers.append("authorization", token);
        headers.append("Content-Type", "application/json");

        const id = 2;
        const response = await fetch(`http://localhost:8080/user/${id}`, {
            method: 'PATCH',
            body: JSON.stringify({
                password: password
            }),
            headers: headers,
        });

        if(!response.ok){
            errorMessage.style.display = "block";
            errorMessage.innerText = "Erro ao redefinir senha."
            console.log(response);
            return;
        }

        errorMessage.style.display = "block";
        errorMessage.style.backgroundColor = "rgb(43, 102, 58)";
        errorMessage.innerText = "Senha redefinida com sucesso."

        window.location.href = "http://localhost:5500/frontend/src/index.html";
    }

    

    passwordInput.addEventListener("input", function () {
        isGood(passwordInput.value);
    });

    repeatPasswordInput.addEventListener("input", function () {
        checkPasswordMatch();
    });




    const form = document.getElementById("password-form");
    const button = document.getElementById("change-button");

    form.addEventListener("submit", e => {
        e.preventDefault();
    })

    button.addEventListener("click", changePassword);

})

