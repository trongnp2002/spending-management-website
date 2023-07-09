

const slidePage = document.querySelector(".forgot-password_slide-page");
const nextBtnFirst = document.querySelector(".firstNext");
const submitBtn = document.querySelector(".forgot-password_submit");
const prevBtnFourth = document.querySelector(".prev-3");
const progressText = document.querySelectorAll(".forgot-password_step p");
const progressCheck = document.querySelectorAll(".forgot-password_step .forgot-password_check");
const bullet = document.querySelectorAll(".forgot-password_step .forgot-password_bullet");

let current = 1;


function sendOTP() {
  var email = document.getElementById("userEmailInput").value;
  if (email === "") {
    alert("Email cannot be empty!!!");
  } else {
    $.ajax({
      url: "/otps/sendOTP",
      type: "GET",
      data: {
        userInput: email
      },
      success: function (data) {
        if (data !== null && data !== "") {
          alert(data)
        }
      },

    });
    alert("OTP already send to your email! You have 60sec to confirm OTP!")
  }

}


nextBtnFirst.addEventListener("click", function (event) {
  var userOTPInput = document.getElementById("userOTPInput").value;
  var email = document.getElementById("userEmailInput").value;
  if (email !== "") {
    $.ajax({
      url: "/otps/confirmOTP",
      type: "GET",
      data: {
        userInput: userOTPInput,
        email: email
      },
      success: function (data) {
        event.preventDefault();
        if (data !== null && data === "") {
          slidePage.style.marginLeft = "-25%";
          bullet[current - 1].classList.add("active");
          progressCheck[current - 1].classList.add("active");
          progressText[current - 1].classList.add("active");
          current += 1;
        } else {
          alert(data)
        }

      },
      error: function (data) {
        alert(data)
      }
    })
  } else {
    alert("email cannot be empty!!!");
  }


});




prevBtnFourth.addEventListener("click", function (event) {
    event.preventDefault();
    slidePage.style.marginLeft = "0%";
    bullet[current - 2].classList.remove("active");
    progressCheck[current - 2].classList.remove("active");
    progressText[current - 2].classList.remove("active");
    current -= 1;
  });



$('#password, #repeatPassword').on('keyup', function () {
  if ($('#password').val() == $('#repeatPassword').val()) {
    $('#message').html('Re-password is matching').css('color', 'green');
  } else
    $('#message').html('Re-password is not Matching').css('color', 'red');
});



function report(data) {
  if (data != null && data !== "") {
    $('#report').html(data);
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

