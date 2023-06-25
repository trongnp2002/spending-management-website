var firstPageButton = document.getElementById("firstPage");

var previousPageButton = document.getElementById("previousPage");
var nextPageButton = document.getElementById("nextPage");
var lastPageButton = document.getElementById("lastPage");

var currentPageSpan = document.getElementById("goToPageInput").value;
var totalPages = document.getElementById("totalPages").value;
var isLockedUserPage = document.getElementById("isLockPage").value;
var isStatusUserPage = document.getElementById("isStatusPage").value;
var locked = document.getElementById("lock").value;
var active = document.getElementById("status").value;
var selection = document.getElementById("sltSearch").value;
var searchValue = document.getElementById("txtSearch").value;
function changeStatus(userId, currentStatus, page) {

    if (currentStatus === true) {
        if (confirm("are you sure to disable user: " + userId)) {
            // window.location = url;
            changeStatus1(userId)
        }
    } else {
        // window.location = url;
        changeStatus1(userId)
    }
}
function changeStatus1(userId){
        $.ajax({
          url:    "/admins/change-status/" + userId,
          type: "GET",
          success: function (data) {
            document.getElementById("user"+userId).innerHTML = data;
          },
    
        });
}
function goToPage() {
    let goToPageInput = document.getElementById("goToPageInput");
    let pageNumber = parseInt(goToPageInput.value);
    let url = "/admins/home/page" + pageNumber;
    if (isLockedUserPage == "true") {
        let isLockedList = (locked == "true");
        if (selection === "select") {
            url = "/admins/list-locked/page" + pageNumber + "/?nonlocked=" + !isLockedList;
        } else {
            window.location = "/admins/search/page" + pageNumber + "?select=" + selection + "&value=" + searchValue + "&nonlocked=" + isLockedList;
        }
    } else if (isStatusUserPage == "true") {
        let isActiveList = (active == "true");
        if (selection === "select") {
            url = "/admins/list-status/page" + pageNumber + "/?isactive=" + !isActiveList;
        } else {
            window.location = "/admins/search/page" + pageNumber + "/?select=" + selection + "&value=" + searchValue + "&isactive=" + isActiveList;
        }
    } else if (isLockedUserPage != "true" && isStatusUserPage != "true") {
        if (selection === "select") {

        } else {
            url = "/admins/search/page" + pageNumber + "/?select=" + selection + "&value=" + searchValue;
        }
    }

    window.location = url;
}

function renderPagination() {
    // Kiểm tra trang hiện tại để ẩn hoặc hiển thị các nút điều hướng phù hợp
    if (currentPageSpan === "1") {
        firstPageButton.disabled = true;
        previousPageButton.disabled = true;
    } else {
        firstPageButton.disabled = false;
        previousPageButton.disabled = false;
    }

    if (currentPageSpan === totalPages) {
        nextPageButton.disabled = true;
        lastPageButton.disabled = true;
    } else {
        nextPageButton.disabled = false;
        lastPageButton.disabled = false;
    }
}


firstPageButton.addEventListener("click", function (event) {
    if (isLockedUserPage == "true") {
        let isLockedList = (locked == "true");
        if (selection === "select") {
            window.location = "/admins/list-locked/page" + 1 + "/?nonlocked=" + !isLockedList;
        } else {
            window.location = "/admins/search/page" + 1 + "/?select=" + selection + "&value=" + searchValue + "&nonlocked=" + isLockedList;

        }
    } else if (isStatusUserPage == "true") {
        let isActiveList = (active == "true");
        if (selection === "select") {
            window.location = "/admins/list-status/page" + 1 + "/?isactive=" + !isActiveList;
        } else {
            window.location = "/admins/search/page" + 1 + "/?select=" + selection + "&value=" + searchValue + "&isactive=" + isActiveList;
        }
    } else if (isLockedUserPage != "true" && isStatusUserPage != "true") {
        if (selection === "select") {
            window.location = "/admins/home/page" + 1;
        } else {
            window.location = "/admins/search/page" + 1 + "/?select=" + selection + "&value=" + searchValue;
        }
    }
});

lastPageButton.addEventListener("click", function (event) {
    if (isLockedUserPage == "true") {
        let isLockedList = (locked == "true");
        if (selection === "select") {
            window.location = "/admins/list-locked/page" + totalPages + "/?nonlocked=" + !isLockedList;
        } else {
            window.location = "/admins/search/page" + totalPages + "/?select=" + selection + "&value=" + searchValue + "&nonlocked=" + isLockedList;

        }
    } else if (isStatusUserPage == "true") {
        let isActiveList = (active == "true");
        if (selection === "select") {
            window.location = "/admins/list-status/page" + totalPages + "/?isactive=" + !isActiveList;
        } else {
            window.location = "/admins/search/page" + totalPages + "/?select=" + selection + "&value=" + searchValue + "&isactive=" + isActiveList;
        }
    } else if (isLockedUserPage != "true" && isStatusUserPage != "true") {
        if (selection === "select") {
            window.location = "/admins/home/page" + totalPages;
        } else {
            window.location = "/admins/search/page" + totalPages + "/?select=" + selection + "&value=" + searchValue;
        }
    }
});

nextPageButton.addEventListener("click", function (event) {
    let nextPage = parseInt(currentPageSpan) + 1;
    if (isLockedUserPage == "true") {
        let isLockedList = (locked == "true");
        if (selection === "select") {
            window.location = "/admins/list-locked/page" + nextPage + "/?nonlocked=" + !isLockedList;
        } else {
            window.location = "/admins/search/page" + nextPage + "/?select=" + selection + "&value=" + searchValue + "&nonlocked=" + isLockedList;
        }
    } else if (isStatusUserPage == "true") {
        let isActiveList = (active == "true");
        if (selection === "select") {
            window.location = "/admins/list-status/page" + nextPage + "/?isactive=" + !isActiveList;
        } else {
            window.location = "/admins/search/page" + nextPage + "/?select=" + selection + "&value=" + searchValue + "&isactive=" + isActiveList;
        }
    } else if (isLockedUserPage != "true" && isStatusUserPage != "true") {
        if (selection === "select") {
            window.location = "/admins/home/page" + nextPage;
        } else {
            window.location = "/admins/search/page" + nextPage + "/?select=" + selection + "&value=" + searchValue;
        }
    }
});

previousPageButton.addEventListener("click", function (event) {
    let previous = parseInt(currentPageSpan) - 1;
    if (isLockedUserPage == "true") {
        let isLockedList = (locked == "true");
        if (selection === "select") {
            window.location = "/admins/list-locked/page" + previous + "/?nonlocked=" + !isLockedList;
        } else {
            window.location = "/admins/search/page" + previous + "/?select=" + selection + "&value=" + searchValue + "&nonlocked=" + isLockedList;

        }
    } else if (isStatusUserPage == "true") {
        let isActiveList = (active == "true");
        if (selection === "select") {
            window.location = "/admins/list-status/page" + previous + "/?isactive=" + !isActiveList;
        } else {
            window.location = "/admins/search/page" + previous + "/?select=" + selection + "&value=" + searchValue + "&isactive=" + isActiveList;
        }
    } else if (isLockedUserPage != "true" && isStatusUserPage != "true") {
        if (selection === "select") {
            window.location = "/admins/home/page" + previous;
        } else {
            window.location = "/admins/search/page" + previous + "/?select=" + selection + "&value=" + searchValue;
        }
    }
});


renderPagination();

var selectElement = document.getElementById("sltSearch");
var searchButton = document.getElementById("btnSearch");

searchButton.addEventListener("click", function (event) {
    var searchBy = selectElement.value;
    let value = document.getElementById("txtSearch").value;
    if (isLockedUserPage == "true") {
        let isLockedList = (locked == "true");
        if (searchBy == "select") {
            window.location = "/admins/list-locked/?nonlocked=" + !isLockedList;
        } else {
            window.location = "/admins/search/?select=" + searchBy + "&value=" + value + "&nonlocked=" + isLockedList;
        }
    } else if (isStatusUserPage == "true") {
        let isActiveList = (active == "true");
        if (searchBy == "select") {
            window.location = "/admins/list-status/?isactive=" + !isActiveList;
        } else {
            window.location = "/admins/search/?select=" + searchBy + "&value=" + value + "&isactive=" + isActiveList;
        }
    } else if (isLockedUserPage != "true" && isStatusUserPage != "true") {
        if (searchBy == "select") {
            window.location = "/admins/home";
        }
        window.location = "/admins/search/?select=" + searchBy + "&value=" + value;
    }
})
var btnActiveList = document.getElementById("btnActiveList");
var btnLockedList = document.getElementById("btnLockedList");
var btnReload = document.getElementById("btnReload");
btnReload.addEventListener("click", function () {
    window.location = "/admins/home";

})

btnLockedList.addEventListener("click", function () {
    let isLockedList = (locked == "true");
    window.location = "/admins" + "/list-locked/?nonlocked=" + isLockedList;
})

btnActiveList.addEventListener("click", function () {
    let isActiveList = (active == "true");
    window.location = "/admins" + "/list-status/?isactive=" + isActiveList;
})



