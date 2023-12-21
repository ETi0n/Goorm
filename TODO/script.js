// 오늘의 날짜
window.onload = function () {
    const date = new Date();

    const year = date.getFullYear();
    const month = date.getMonth() + 1; // 월은 0부터 시작
    const day = date.getDate();

    const today = `${year}년 ${month}월 ${day}일`;

    const todayDateElement = document.querySelector('.date');
    todayDateElement.textContent = today;
}


// Todo list 생성
const addBtn = document.querySelector('button.add-btn');
addBtn.addEventListener('click', (event) => {
    event.preventDefault();
    createList()
});

// check-box 변환 & Todo list 삭제
const delBtn = document.querySelector('ul.lists');
delBtn.addEventListener('click', (event) => {
    deleteList(event)
});


function createList(){
    const todoInput = document.getElementById('todo');
    const todoText = todoInput.value.trim();
    
    if (todoText !== '') {
        const newList = document.createElement('li');
        newList.className = 'list';
    
        const listForm = document.createElement('form');
        listForm.className = 'list_form';

        const listDiv = document.createElement('div');
        listDiv.className = 'list_div';
    
        const listCheckbox = document.createElement('input');
        listCheckbox.className = 'list_check';
        listCheckbox.setAttribute('type', 'checkbox');
        listCheckbox.id = 'checkbox_' + Date.now();
    
        const listLabel = document.createElement('label');
        listLabel.className = 'list_detail';
        listLabel.setAttribute('for', listCheckbox.id);
        listLabel.textContent = todoText;
    
        const deleteBtn = document.createElement('button');
        deleteBtn.className = 'delete-btn';
        deleteBtn.textContent = 'X';
    
        listDiv.appendChild(listCheckbox);
        listDiv.appendChild(listLabel);
        listForm.appendChild(listDiv);
        listForm.appendChild(deleteBtn);
    
        newList.appendChild(listForm);
        document.querySelector('ul.lists').appendChild(newList);
    
        todoInput.value = '';
        console.log('Create New List');
    }
    
}

function deleteList(e){
    const clickedElement = e.target;
    const listItem = clickedElement.closest('li.list');

    if (clickedElement.classList.contains('delete-btn')) {
        if (listItem) {
            const listParent = listItem.parentElement;
            listParent.removeChild(listItem);
            console.log('Delete List');
        }
        e.preventDefault();
    }

    if (clickedElement.classList.contains('list_check')) {
        const checkbox = listItem.querySelector('.list_check');

        console.log('Checkbox State Before Toggle:', checkbox.checked);
        checkbox.checked = !checkbox.checked;
        console.log('Checkbox State After Toggle:', checkbox.checked);
        console.log('Checkbox Clicked');
    }
}
