const slidePage = document.querySelector(".register_slide-page");
const nextBtnFirst = document.querySelector(".firstNext");
const prevBtnSec = document.querySelector(".prev-1");
const nextBtnSec = document.querySelector(".next-1");
const prevBtnThird = document.querySelector(".prev-2");
const nextBtnThird = document.querySelector(".next-2");
const prevBtnFourth = document.querySelector(".prev-3");
const submitBtn = document.querySelector(".register_submit");
const progressText = document.querySelectorAll(".register_step p");
const progressCheck = document.querySelectorAll(".register_step .register_check");
const bullet = document.querySelectorAll(".register_step .register_bullet");
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

nextBtnSec.addEventListener("click", function (event) {
  event.preventDefault();
  slidePage.style.marginLeft = "-50%";
  bullet[current - 1].classList.add("active");
  progressCheck[current - 1].classList.add("active");
  progressText[current - 1].classList.add("active");
  current += 1;
});
nextBtnThird.addEventListener("click", function (event) {
  event.preventDefault();
  slidePage.style.marginLeft = "-75%";
  bullet[current - 1].classList.add("active");
  progressCheck[current - 1].classList.add("active");
  progressText[current - 1].classList.add("active");
  current += 1;
});
// submitBtn.addEventListener("click", function () {
//   bullet[current - 1].classList.add("active");
//   progressCheck[current - 1].classList.add("active");
//   progressText[current - 1].classList.add("active");
//   current += 1;
//   setTimeout(function () {
//     alert("Your Form Successfully Signed up");
//     location.reload();
//   }, 800);
// });

prevBtnSec.addEventListener("click", function (event) {
  event.preventDefault();
  slidePage.style.marginLeft = "0%";
  bullet[current - 2].classList.remove("active");
  progressCheck[current - 2].classList.remove("active");
  progressText[current - 2].classList.remove("active");
  current -= 1;
});
prevBtnThird.addEventListener("click", function (event) {
  event.preventDefault();
  slidePage.style.marginLeft = "-25%";
  bullet[current - 2].classList.remove("active");
  progressCheck[current - 2].classList.remove("active");
  progressText[current - 2].classList.remove("active");
  current -= 1;
});
prevBtnFourth.addEventListener("click", function (event) {
  event.preventDefault();
  slidePage.style.marginLeft = "-50%";
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

function checkEmail(email) {
  var emailUser = email.value;

  $.ajax({
    url: "/check/email",
    type: "GET",
    data: {
      userEmail: emailUser
    },
    success: function (data) {
      var mess = document.getElementById("emailMess");
      if (data !== null && data !== "") {
        mess.innerHTML = data;
      }
    },
    error: function (xhr) {
      var errorData = JSON.parse(xhr.responseText);
      var errorMessage = errorData.error.message;
      mess.innerHTML = "<h5 style='color:red;'>a</h5>";
    }
  });
}

function checkPhone(phone) {
  var phoneUser = phone.value;

  $.ajax({
    url: "/check/phone",
    type: "GET",
    data: {
      userPhone: phoneUser
    },
    success: function (data) {
      var mess = document.getElementById("phoneMess");
      if (data !== null && data !== "") {
        mess.innerHTML = data;

      }
    },

  });
}
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

