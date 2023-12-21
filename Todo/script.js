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

// Todo list 삭제
const delBtn = document.querySelector('ul.lists');
delBtn.addEventListener('click', (event) => {
    event.preventDefault();
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

        const listCheckbox = document.createElement('input');
        listCheckbox.className = 'list_check';
        listCheckbox.setAttribute('type', 'checkbox');

        const listLabel = document.createElement('label');
        listLabel.className = 'list_detail';
        listLabel.appendChild(document.createTextNode(todoText));

        const deleteBtn = document.createElement('button');
        deleteBtn.className = 'delete-btn';
        deleteBtn.appendChild(document.createTextNode('DEL'));

        newList.appendChild(listForm);
        listForm.appendChild(listCheckbox);
        listForm.appendChild(listLabel);
        listForm.appendChild(deleteBtn);
        document.querySelector('ul.lists').appendChild(newList);

        todoInput.value = '';
        console.log('Creat New List');
    }
}

function deleteList(e){
    const clickedElement = e.target;
    const listItem = clickedElement.closest('li.list');

    if (clickedElement.classList.contains('delete-btn')) {
        const listItem = clickedElement.closest('li.list');
        if (listItem) {
            const listParent = listItem.parentElement;
            listParent.removeChild(listItem);
            console.log('Delete List');
        }
    }

    if (clickedElement.classList.contains('list_check')) {
        const checkbox = listItem.querySelector('.list_check');
        checkbox.checked = !checkbox.checked;
    }
}