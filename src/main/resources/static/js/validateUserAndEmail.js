
window.addEventListener('load', async ()=>{
    const users = await (await fetch('/api/check-users-validity')).json();

    const usernameField = document.getElementById('username');
    const emailField = document.getElementById('email');
    const msgUsername = document.getElementById('messageUsername');
    const msgEmail = document.getElementById('messageEmail');
    const sub = document.getElementById('submit');


    usernameField.addEventListener('change', checkUsernameValidity);
    emailField.addEventListener('change', checkEmailValidity);


    async function checkUsernameValidity() {
        let valid = true;


        for (const u of users) {
            if (u.username === await sha256(usernameField.value)) {
                valid = false;
            }
        }
        if (valid) {
            msgUsername.innerHTML = '';
            sub.disabled = false;
        } else {
            msgUsername.style.color = 'red';
            msgUsername.innerHTML = 'username already taken';
            sub.disabled = true;
        }
    }
    async function checkEmailValidity() {
        let valid = true;
        for (const u of users) {
            if (u.email === await sha256(emailField.value)) {
                valid = false;
            }
        }
        if (valid) {
            msgEmail.innerHTML = '';
            sub.disabled = false;
        } else {
            msgEmail.style.color = 'red';
            msgEmail.innerHTML = 'This email already exists';
            sub.disabled = true;
        }
    }

    async function sha256(message) {
        // encode as UTF-8
        const msgBuffer = new TextEncoder().encode(message);

        // hash the message
        const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);

        // convert ArrayBuffer to Array
        const hashArray = Array.from(new Uint8Array(hashBuffer));

        // convert bytes to hex string
        return hashArray.map(b => ('00' + b.toString(16)).slice(-2)).join('');
    }

});