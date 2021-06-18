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

    // newRow.appendChild(createOpenLink(hero.id));
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

const createOpenLink = (heroID) => {
    const cell = document.createElement("td");
    const openLink = document.createElement("a");
    openLink.innerText = "Open";
    openLink.className = "btn btn-success";
    openLink.href = `hero.html?id=${heroID}`

    cell.appendChild(openLink)
    return cell;
}

document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault();

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
    const cell = document.createElement("td");
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
    const updateCell = document.createElement("td");
    const updateHero = document.createElement("button");
    updateHero.innerText = "Update";
    updateHero.className = "btn btn-warning";
    updateHero.setAttribute("type", "button");
    updateHero.setAttribute("data-bs-toggle", "modal");
    updateHero.setAttribute("data-bs-target", "#UpdateTeamModal")
    updateHero.addEventListener("click", function (event) {
        document.getElementById("name").value = hero.name;
        document.getElementById("element").value = hero.element;
        document.getElementById("weapon").value - hero.weapon;
        document.getElementById("level").value = hero.level;
        document.getElementById("updateBtn").setAttribute("heroID", hero.id);

    });
    updateCell.appendChild(updateHero);
    return updateCell;
}

document.getElementById("updateForm").addEventListener("submit", function (event) {
    if (!this.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
        console.log("form was not valid");
        return;
    }
    this.classList.add('was-validated')
    console.log("form was valid");

    const heroID = document.getElementById("updateBtn").getAttribute("heroID");
    const data = {
        name: this.name.value,
        element: this.element.value,
        weapon: this.weapon.value,
        level: this.level.value
    }

    axios.put(`/hero/update/${heroID}`, data)
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
    console.log(this);
});