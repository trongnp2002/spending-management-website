var classifyModeDetail = document.getElementById('classifyModeDetail');

btn.forEach(button => {
    button.addEventListener('click', () => {
        let addBtn = document.createElement("input"); // Tạo thẻ button mới
        addBtn.className = "btn btn-success disabled";
        addBtn.style.width = "100%";
        addBtn.style.margin = "auto";
        addBtn.innerHTML = "+";
        addBtn.name = "classify";
        addBtn.value = "+";
        let removeBtn = document.createElement("input"); // Tạo thẻ button mới
        removeBtn.className = "btn btn-danger disabled";
        removeBtn.style.width = "100%";
        removeBtn.style.margin = "auto";
        removeBtn.innerHTML = "-";
        removeBtn.name = "classify";
        removeBtn.value = "-";
        let classifyDetail;
        // Lấy phần tử có class là "classify" trong cùng một hàng
        const row = button.closest('tr');
        const classify = row.querySelector('td:nth-child(3)').textContent;
        const money = row.querySelector('td:nth-child(4)');
        const id = row.querySelector('td:nth-child(1)');
        temp=id.textContent;
        classify === '+' ? classifyDetail = 'true' : classifyDetail = 'false';
        if (classifyModeDetail.lastChild) {
            classifyModeDetail.removeChild(classifyModeDetail.lastChild);
        }
        if (classifyDetail === 'true') {
            classifyModeDetail.appendChild(removeBtn);
        } else {
            classifyModeDetail.appendChild(addBtn);
        }
        console.log(money.textContent);
        numberInput1.value = money.textContent;
        let noteElement = document.getElementsByName('note');
        noteElement[0].value = 'Debt Settlement For The Debt Note ' + id.textContent;
    });

});