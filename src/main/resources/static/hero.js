'use strict';

const form = document.getElementById("output");

const getHeroes = async () => {
    const teams = await axios.get("/heroes/");
    document.getElementById("output").innerHTML = "";
    teams.data.forEach(hero => renderHero(hero));
    console.log(teams);
}

const renderHero = ({ id, name, element, weapon, level }) => {
    const column = document.createElement("div");
    column.className = "col";

    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    const nameInput = document.createElement("p");
    nameInput.className = "card-text";
    nameInput.innerText = `Name: ${name}`;
    cardBody.appendChild(nameInput);

    const elementInput = document.createElement("p");
    elementInput.className = "card-text";
    elementInput.innerText = `Element: ${element}`;
    cardBody.appendChild(elementInput);

    const weaponInput = document.createElement("p");
    weaponInput.className = "card-text";
    weaponInput.innerText = `Weapon: ${weapon}`;
    cardBody.appendChild(weaponInput);

    const levelInput = document.createElement("p");
    levelInput.className = "card-text";
    levelInput.innerText = `Level: ${level}`;
    cardBody.appendChild(levelInput);

    const cardFooter = document.createElement("div");
    cardFooter.className = "card-footer";
    card.appendChild(cardFooter);

    const deleteButton = document.createElement("a");
    deleteButton.innerText = "Delete";
    deleteButton.className = "card-link";
    deleteButton.addEventListener("click", function () {
        deleteCar(id);
    });
    cardFooter.appendChild(deleteButton);

    output.appendChild(column);
}

getHeroes();

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

const deleteHero = async (id) => {
    const res = await axios.delete(`/heroes/remove/${id}`);
    getHeroes();
};