


// function convertNumber(input) {
//     var number = input.toString().replace(/[^0-9]/g, ""); // Remove non-digit characters
//     var convertedText = convertToVietnamese(number);
//     document.getElementById("convertedText").textContent = convertedText;
// }

// function convertToVietnamese(number) {
//     var units = ["", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"];
//     var tens = ["", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"];
//     var scales = ["", "nghìn", "triệu", "tỷ", "nghìn tỷ", "triệu tỷ", "tỷ tỷ"]; // Add more scales if needed

//     if (number === "") {
//         return "Không";
//     }

//     var result = "";
//     var scaleIndex = 0;
//     while (number.length > 0) {
//         var digits = number.slice(-3); // Get the last 3 digits
//         number = number.slice(0, -3); // Remove the last 3 digits

//         var converted = "";

// if (digits.length == 1 || (digits.length == 2 && number <= 10)) {
//     result = units[number];
//   }
//         if (digits.length === 3) {
//             converted += units[digits[0]] + " trăm ";
//             digits = digits.slice(1);
//         }

//         if (digits.length === 2) {
//             if (digits[0] === "0") {
//                 converted += units[digits[1]];
//             } else if (digits[0] === "1") {
//                 converted += tens[digits[1]];
//             } else {
//                 converted += tens[digits[0]] + " " + units[digits[1]];
//             }
//         } else if (digits.length === 1) {
//             converted += units[digits[0]];
//         }

//         if (converted !== "") {
//             result = converted + " " + scales[scaleIndex] + " " + result;
//         }

//         scaleIndex++;
//     }

//     return result.trim();
// }





//const numberInput = document.getElementById('numberInput');
//const convertedText = document.getElementById('convertedText');

function convertNumber(input) {
    var number = input.toString().replace(/[^0-9]/g, ""); // Remove non-digit characters
    var convertedText = convertToVietnameseText(number);
    document.getElementById("convertedText").textContent = convertedText;
}

function convertToVietnameseText(number) {
    const ones = ['', ' một ', ' hai ', ' ba ', 'bốn', 'năm', 'sáu', 'bảy', 'tám', 'chín'];
    const tens = ['', 'mười', 'hai mươi', 'ba mươi', 'bốn mươi', 'năm mươi', 'sáu mươi', 'bảy mươi', 'tám mươi', 'chín mươi'];
    const scales = ['', ' ngàn', ' triệu', ' tỷ'];
  
    let numArray = number.toString().split('').reverse();
    let result = [];
  
    if (numArray.length > 12) {
     // throw new Error('Number too large');
    return  document.getElementById("convertedText").textContent = "Number too large!";
    
    }
  
    for (let i = 0; i < numArray.length; i += 3) {
      let chunk = parseInt(numArray.slice(i, i + 3).reverse().join(''));
  
      if (chunk !== 0 || i === 0) {
        let scale = scales[Math.floor(i / 3)];
        let text = ' ';
  
        if (chunk >= 100) {
          text += ones[Math.floor(chunk / 100)] + ' trăm ';
          chunk %= 100;
        }
  
        if (chunk >= 10 && chunk <= 19) {
          text += ' mười ';
          chunk %= 10;
        } else if (chunk >= 20 || chunk === 0) {
          text += tens[Math.floor(chunk / 10)] + ' ';
          chunk %= 10;
        }
  
        if (chunk !== 0) {
          if (text.endsWith('mươi ')) {
            text = text.slice(0, -6);
          }
          if (chunk === 1 && i === 0 && scale === '') {
            text += 'một ';
          } else if (chunk === 4 && i === 0 && scale === '') {
            text += 'tư';
          } else if (chunk === 5 && i === 0 && scale === '') {
            text += 'năm';
          } else {
            text += ones[chunk];
          }
        }
  
        if (scale !== ' ') {
          text += scale;
        }
  
        result.push(text);
      }
    }
  
    return result.reverse().join(' ');
  }





