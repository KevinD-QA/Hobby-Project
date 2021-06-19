'use strict';

const form = document.getElementById("output");

var totalRowCount = 0;
var rowCount = 0;

function CountRows() {
    let modalActive = !(document.getElementById('AddTeamModal').style.display === "none");
    let table = document.getElementById("mainbody");
    let rows = table.getElementsByTagName("tr");

    console.log(totalRowCount + " row length");
    if (rows.length > 2) {
        document.getElementById('addCharcterBtn').classList.remove('btn', 'btn-primary');
        document.getElementById('addCharcterBtn').classList.add('btn', 'btn-primary', 'disabled');
        document.getElementById('submitBtn').classList.remove('btn', 'btn-outline-success');
        document.getElementById('submitBtn').classList.add('btn', 'btn-secondary', 'disabled');
    }
    if ((rows.length < 4) && !modalActive) {
        document.getElementById('addCharcterBtn').classList.remove('btn', 'btn-primary', 'disabled');
        document.getElementById('addCharcterBtn').classList.add('btn', 'btn-primary');
        document.getElementById('submitBtn').classList.remove('btn', 'btn-secondary', 'disabled');
        document.getElementById('submitBtn').classList.add('btn', 'btn-outline-success');

    }
}

const getHeroes = async () => {
    const tableBody = document.querySelector("tbody");
    document.getElementById("output").innerHTML = "";
    tableBody.innerHTML = "";
    const teams = await axios.get("/heroes/");
    teams.data.forEach(hero => {
        renderHero(hero);
    });
}
const renderHero = hero => {
    const tableBody = document.querySelector("tbody");

    const newRow = document.createElement("tr");

    newRow.appendChild(createTableCell(hero.id));
    newRow.appendChild(createTableCell(hero.name));
    newRow.appendChild(createTableCell(hero.element));
    newRow.appendChild(createTableCell(hero.weapon));
    newRow.appendChild(createTableCell(hero.level));
    newRow.appendChild(updateButton(hero));
    newRow.appendChild(deleteButton(hero.id));

    tableBody.appendChild(newRow);
}

getHeroes();

const createTableCell = (data) => {
    const cell = document.createElement("td");
    cell.innerText = data;
    cell.className = "align-middle";
    return cell;
}

document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault();
    let createModal = document.getElementById('AddTeamModal');
    let modal = bootstrap.Modal.getInstance(createModal);
    modal.hide();

    const data = {
        name: this.name.value,
        element: this.element.value,
        weapon: this.weapon.value,
        level: this.level.value
    }

    axios.post("/heroes/create", data)
        .then(res => {
            getHeroes();
            this.reset();
            this.name.focus();
            totalRowCount++;
        }).catch(err => console.log(err));

    console.log(this);
});

const deleteButton = (heroID) => {
    const cell = document.createElement("a");
    const deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.className = "btn btn-danger";
    deleteButton.id = "deleteBtn";
    deleteButton.setAttribute("type", "submit");
    deleteButton.addEventListener("click", function (event) {
        deleteHero(heroID);
        totalRowCount--;
    });

    cell.appendChild(deleteButton);
    return cell;
}

const deleteHero = async (id) => {
    const res = await axios.delete(`/heroes/remove/${id}`);
    getHeroes(); //Can't create aysnc functions inside deleteButton so created separate method
    CountRows();
};

const updateButton = (hero) => {
    const updateCell = document.createElement("a");
    const updateHeroBtn = document.createElement("button");
    updateHeroBtn.innerText = "Update";
    updateHeroBtn.className = "btn btn-warning";
    updateHeroBtn.setAttribute("type", "button");
    updateHeroBtn.setAttribute("data-bs-toggle", "modal");
    updateHeroBtn.setAttribute("data-bs-target", "#UpdateTeamModal")
    updateHeroBtn.addEventListener("click", function (event) {
        document.getElementById("updateName").value = hero.name;
        document.getElementById("updateElement").value = hero.element;
        document.getElementById("updateWeapon").value - hero.weapon;
        document.getElementById("updateLevel").value = hero.level;
        document.getElementById("updateBtn").setAttribute("heroID", hero.id);
    });
    updateCell.appendChild(updateHeroBtn);
    return updateCell;
}
getHeroes();

document.getElementById("updateForm").addEventListener("submit", function (event) {
    event.preventDefault();
    let updateModal = document.getElementById('UpdateTeamModal');
    let modal = bootstrap.Modal.getInstance(updateModal);
    modal.hide();

    const heroID = document.getElementById("updateBtn").getAttribute("heroID");
    const data = {
        name: this.updateName.value,
        element: this.updateElement.value,
        weapon: this.updateWeapon.value,
        level: this.updateLevel.value
    }

    axios.put(`/heroes/update/${heroID}`, data)
        .then(res => {
            getHeroes();
            this.reset();
            this.name.focus();
        }).catch(err => console.log(err));

    console.log(this);
});