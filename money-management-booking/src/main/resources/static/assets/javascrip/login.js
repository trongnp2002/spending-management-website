// Selecting necessary DOM elements
const captchaTextBox = document.querySelector(".captch_box input");
const refreshButton = document.querySelector(".captcha_refresh_button");
const captchaInputBox = document.querySelector(".captch_input input");
const message = document.querySelector(".login_mess");
const submitButton = document.querySelector(".captcha_button");

// Variable to store generated captcha
let captchaText = null;

// Function to generate captcha
const generateCaptcha = () => {
  const randomString = Math.random().toString(36).substring(2, 7);
  const randomStringArray = randomString.split("");
  const changeString = randomStringArray.map((char) => (Math.random() > 0.5 ? char.toUpperCase() : char));
  captchaText = changeString.join("   ");
  captchaTextBox.value = captchaText;
  console.log(captchaText);
};

const refreshBtnClick = () => {
  generateCaptcha();
  captchaInputBox.value = "";
  captchaKeyUpValidate();
};

const captchaKeyUpValidate = () => {
  //Toggle submit button disable class based on captcha input field.
  submitButton.classList.toggle("disabled", !captchaInputBox.value);

  if (!captchaInputBox.value) message.classList.remove("active");
};

// Function to validate the entered captcha
const submitBtnClick = () => {
  captchaText = captchaText
    .split("")
    .filter((char) => char !== " ")
    .join("");
  message.classList.add("active");
  // Check if the entered captcha text is correct or not
  if (captchaInputBox.value === captchaText) {
      document.getElementById("captcha_user_input").value="";
    document.getElementById("login_form").submit();


  } else {
    message.innerText = "Entered captcha is not correct";
    message.style.color = "#FF2525";
    generateCaptcha();
    document.getElementById("captcha_user_input").value = "";

  }
};

// Add event listeners for the refresh button, captchaInputBox, submit button
refreshButton.addEventListener("click", refreshBtnClick);
captchaInputBox.addEventListener("keyup", captchaKeyUpValidate);
submitButton.addEventListener("click", submitBtnClick);

// Generate a captcha when the page loads
generateCaptcha();

function report(error, turn) {
  if (error !== null) {
    if (error == "login-fail") {
      if(turn !== null){
        if (turn > 0) {
          $('#report').html("<p style='padding-left:20px; height: 100%; line-height:100%;' > Warning: Email or password not correct!!! </br> Your account has been locked due to "+turn+" failed attempts</p>");
  
        }else{
          $('#report').html("<p style='padding-left:20px; height: 100%; line-height:100%;' > Warning: Your account has been locked after 24 hours</p>");
        }
      }
     
    }
    else if (error == "disabled") {
      $('#report').html("<p style='padding-left:20px; height: 100%; line-height:100%;' > Warning: Your account has been disabled!!!</p>");
    }else{
      $('#report').html("<p style='padding-left:20px; height: 100%; line-height:100%;' > Warning: Email or password not correct!!!</p>");
    }
    $('.alert').addClass("show");
    $('.alert').removeClass("hide");
    $('.alert').addClass("showAlert");
    setTimeout(function () {
      $('.alert').removeClass("show");
      $('.alert').addClass("hide");
    }, 5000);

    $('.close-btn').click(function () {
      $('.alert').removeClass("show");
      $('.alert').addClass("hide");
    });
  }

}