'use strict';

const form = document.getElementById("output");

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
    newRow.appendChild(updateHero(hero));
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
        }).catch(err => console.log(err));

    console.log(this);
});

const updateHero = (hero) => {
    const updateCell = document.createElement("td");
    const updateHero = document.createElement("button");
    updateHero.innerText = "Update";
    updateHero.className = "btn btn-warning";
    updateHero.setAttribute("type", "button");
    updateHero.setAttribute("data-bs-toggle", "modal");
    updateHero.setAttribute("data-bs-target", "#AddTeamModal")
    updateHero.addEventListener("click", function (event) {
        document.getElementById("name").value = hero.name;
        document.getElementById("element").value = hero.element;
        document.getElementById("weapon").value - hero.weapon;
        document.getElementById("level").value = hero.level;
    });
    updateCell.appendChild(updateHero);
    return updateCell;
}

const deleteButton = (heroID) => {
    const cell = document.createElement("td");
    const deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.className = "btn btn-danger";
    deleteButton.setAttribute("type", "submit");
    deleteButton.addEventListener("click", function (event) {
        deleteHero(heroID);
    });

    cell.appendChild(deleteButton);
    return cell;
}

const deleteHero = async (id) => {
    const res = await axios.delete(`/heroes/remove/${id}`);
    getHeroes(); //Can't create aysnc functions inside deleteButton so created separate method
};