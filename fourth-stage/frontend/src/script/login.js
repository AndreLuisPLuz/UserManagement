const form = document.getElementById("login-form");
const button = document.getElementById("login-button");

const doLogin = async() => {
    const usernameField = document.getElementsByName("username")[0];
    const passwordField = document.getElementsByName("password")[0];

    const username = usernameField.value;
    const password = passwordField.value;

    const headers = new Headers();
    headers.append("Content-Type", "application/json");
    
    const response = await fetch("http://localhost:8080/auth", {
        method: 'POST',
        body: JSON.stringify({
            login: username,
            password: password
        }),
        headers: headers,
    });

    if (!response.ok) {
        const errorMessage = document.getElementById("error-message");
        errorMessage.style.display = "block";
    }

    const data = await response.json();
    localStorage.setItem("token", data.token);

    // if (username == password)
    window.location.href = "./password.html";
    // else
    //     window.location.href = "../";
}

form.addEventListener("submit", e => {
    e.preventDefault();
})

button.addEventListener("click", doLogin);



