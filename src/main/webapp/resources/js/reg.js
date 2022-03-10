const form = document.getElementsByTagName('form')[0];

const username = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm-password')
const firstname = document.getElementById('firstname');
const lastname = document.getElementById('lastname');

const usernameError = document.querySelector('#username + span.error');
const emailError = document.querySelector('#email + span.error');
const passwordError = document.querySelector('#password + span.error');
const confirmPasswordError = document.querySelector('#confirm-password + span.error');
const firstnameError = document.querySelector('#firstname + span.error');
const lastnameError = document.querySelector('#lastname + span.error');


username.addEventListener('input', function(event) {
  if (username.validity.valid) {
    usernameError.textContent = '';
    usernameError.className = 'error';
  } else {
    showUsernameError();
  }
});

email.addEventListener('input', function (event) {

  if (email.validity.valid) {
    emailError.textContent = '';
    emailError.className = 'error';
  } else {
    showEmailError();
  }
})

password.addEventListener('input', function(event) {
  if (password.validity.valid) {
    passwordError.textContent = '';
    passwordError.className = 'error';
  } else {
    showPasswordError();
  }
});

confirmPassword.addEventListener('input', function(event) {
  if (confirmPassword.value === password.value) {
    confirmPasswordError.textContent = '';
    confirmPasswordError.className = 'error';
  } else {
    showConfirmPasswordError();
  }
});

firstname.addEventListener('input', function(event) {
  if (firstname.validity.valid) {
    firstnameError.textContent = '';
    firstnameError.className = 'error';
  } else {
    showFirstnameError();
  }
});

lastname.addEventListener('input', function(event) {
  if (lastname.validity.valid) {
    lastnameError.textContent = '';
    lastnameError.className = 'error';
  } else {
    showLastnameError();
  }
});

form.addEventListener('submit', function (event) {
  if (!email.validity.valid) {
    showEmailError();
    event.preventDefault();
  }
  if (!username.validity.valid) {
    showUsernameError();
    event.preventDefault();
  }
  if (!password.validity.valid) {
    showPasswordError();
    event.preventDefault();
  }
  if (confirmPassword.value !== password.value) {
    showFirstnameError();
    event.preventDefault();
  }
  if (!firstname.validity.valid) {
    showPasswordError();
    event.preventDefault();
  }
  if (!lastname.validity.valid) {
    showLastnameError();
    event.preventDefault();
  }
});

function showUsernameError() {
  if (username.validity.valueMissing) {
    usernameError.textContent = 'You need to enter an username.';
  } else if (username.validity.tooShort) {
    usernameError.textContent = `Username should be at least ${username.minLength} characters; you entered ${username.value.length}.`;
  }
  usernameError.className = 'error active';
}

function showEmailError() {
  if (email.validity.valueMissing) {
    emailError.textContent = 'You need to enter an e-mail address.';
  } else if (email.validity.typeMismatch) {
    emailError.textContent = 'Entered value needs to be an e-mail address.';
  } else if (email.validity.tooShort) {
    emailError.textContent = `Email should be at least ${email.minLength} characters; you entered ${email.value.length}.`;
  }
  emailError.className = 'error active';
}

function showPasswordError() {
  if (password.validity.valueMissing) {
    passwordError.textContent = 'You need to enter a password.';
  } else if (password.validity.tooShort) {
    passwordError.textContent = `Password should be at least ${password.minLength} characters; you entered ${password.value.length}.`;
  }
  passwordError.className = 'error active';
}

function showConfirmPasswordError() {
  if (confirmPassword.value !== password.value) {
    confirmPasswordError.textContent = 'Confirm password not equals password.';
  }
  confirmPasswordError.className = 'error active';
}

function showFirstnameError() {
  if (firstname.validity.valueMissing) {
    firstnameError.textContent = 'You need to enter a firstname.';
  } else if (firstname.validity.tooShort) {
    firstnameError.textContent = `Fristname should be at least ${firstname.minLength} characters; you entered ${firstname.value.length}.`;
  }
  firstnameError.className = 'error active';
}

function showLastnameError() {
  if (lastname.validity.valueMissing) {
    lastnameError.textContent = 'You need to enter a lastname.';
  } else if (lastname.validity.tooShort) {
    lastnameError.textContent = `Lastname should be at least ${lastname.minLength} characters; you entered ${lastname.value.length}.`;
  }
  lastnameError.className = 'error active';
}