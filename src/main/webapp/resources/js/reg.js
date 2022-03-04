// validation

const form = document.getElementsByTagName('form')[0];

const username = document.getElementById('username');
const email = document.getElementById('email');


const usernameError = document.querySelector('#username + span.error');
const emailError = document.querySelector('#email + span.error');

username.addEventListener('input', function (event) {
  if (username.validity.valid) {
    usernameError.textContent = '';
    usernameError.className = 'error';
  } else if(email.validity.valid) {
    emailError.textContent = ''
    emailError.className = 'error';
  } else {
    showError();
  }
});

form.addEventListener('submit', function (event) {
  if (!username.validity.valid || !email.validity.valid) {
    showError();
    event.preventDefault();
  }
});

function showError() {
  if (username.validity.valueMissing) {
    usernameError.textContent = 'not empty';
  } else if (username.validity.rangeUnderflow) {
    usernameError.textContent = 'too short';
  } else if (username.validity.rangeOverflow)
  if (email.validity)

    usernameError.className = 'error active';
}