

var firstPageButton = document.getElementById("firstPage");
var previousPageButton = document.getElementById("previousPage");
var nextPageButton = document.getElementById("nextPage");
var lastPageButton = document.getElementById("lastPage");

var currentPageSpan = document.getElementById("goToPageInput").value;
var totalPages = document.getElementById("totalPages").value;

function changeStatus(userId, currentStatus, page) {
    if (selection === null || selection.value === "/select" || selection.value === "") {
        if (currentStatus === true) {
            if (confirm("are you sure to disable user: " + userId)) {
                window.location = "/admins/change-status/page" + page + "/" + userId;
            }
        } else {
            window.location = "/admins/change-status/page" + page + "/" + userId;
        }
    } else {
        if (currentStatus === true) {
            if (confirm("are you sure to disable user: " + userId)) {
                window.location = "/admins/change-status/page" + page + "/" + userId + "/?select=" + selection + "&value=" + searchValue;
            }
        } else {
            window.location = "/admins/change-status/page" + page + "/" + userId+ "/?select=" + selection + "&value=" + searchValue;;
        }
    }

}

function goToPage() {
    var goToPageInput = document.getElementById("goToPageInput");
    var pageNumber = parseInt(goToPageInput.value);
    if (selection === "select") {
        window.location = "/admins/home/page" + pageNumber;
    } else {
        window.location = "/admins/search/page" + pageNumber + "/?select=" + selection + "&value=" + searchValue;
    }
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

var selection = document.getElementById("sltSearch").value;
var searchValue = document.getElementById("txtSearch").value;
firstPageButton.addEventListener("click", function (event) {
    if (selection.value === "select") {
        window.location = "/admins/home/page" + 1;
    } else {
        window.location = "/admins/search/page" + 1 + "/?select=" + selection + "&value=" + searchValue;
    }
});
lastPageButton.addEventListener("click", function (event) {
    if (selection === "select") {
        window.location = "/admins/home/page" + totalPages;
    } else {
        window.location = "/admins/search/page" + totalPages + "/?select=" + selection + "&value=" + searchValue;
    }
});

nextPageButton.addEventListener("click", function (event) {
    let nextPage = parseInt(currentPageSpan) + 1;
    if (selection === "select") {
        window.location = "/admins/home/page" + nextPage;
    } else {
        window.location = "/admins/search/page" + nextPage + "/?select=" + selection + "&value=" + searchValue;
    }
});

previousPageButton.addEventListener("click", function (event) {
    let previous = parseInt(currentPageSpan) - 1;
    if (selection === "select") {
        window.location = "/admins/home/page" + previous;
    } else {
        window.location = "/admins/search/page" + previous + "/?select=" + selection + "&value=" + searchValue;
    }
});


renderPagination();

var selectElement = document.getElementById("sltSearch");
var selectButton = document.getElementById("btnSearch");

selectButton.addEventListener("click", function (event) {
    var option = selectElement.value;
    let search = document.getElementById("txtSearch").value;
    window.location = "/admins/search/?select=" + option + "&value=" + search;

})

