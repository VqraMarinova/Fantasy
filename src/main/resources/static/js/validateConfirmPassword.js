window.addEventListener('load', async ()=>{

    const confirmPsw = document.getElementById('confirmPassword');
    confirmPsw.addEventListener('change', checkConfirmPassword)


    function checkConfirmPassword() {
        const psw = document.getElementById('password');
        const sub = document.getElementById('submit');
        const msgPassword = document.getElementById('messageConfirmPas');
        if (psw.value ===
            confirmPsw.value) {
            msgPassword.style.color = 'green';
            msgPassword.innerHTML = 'matching';
            sub.disabled = false;
        } else {
            msgPassword.style.color = 'red';
            msgPassword.innerHTML = 'not matching';
            sub.disabled = true;
        }
    }






});