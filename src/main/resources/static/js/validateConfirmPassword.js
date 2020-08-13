window.addEventListener('load', async ()=>{

    const confirmPsw = document.getElementById('confirmPassword');
    confirmPsw.addEventListener('change', checkConfirmPassword)


    function checkConfirmPassword() {
        const psw = document.getElementById('password');
        const msgPassword = document.getElementById('messageConfirmPas');
        if (psw.value ===
            confirmPsw.value) {
            msgPassword.style.color = 'green';
            msgPassword.innerHTML = 'matching';
        } else {
            msgPassword.style.color = 'red';
            msgPassword.innerHTML = 'not matching';
        }
    }






});