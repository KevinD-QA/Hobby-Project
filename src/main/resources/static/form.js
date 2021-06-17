const form = document.getElementById('form');
const characterName = docment.getElementById('name');
const element = document.getElementById('element');
const weapon = document.getElementById('weapon');
const level = document.getElementById('level');

const getCharacters = async () => {
    const res = await axios.get("/characters/");
    output.innerHTML = "";
    res.data.forEach(character => renderCharacter(character));
}

card.className = "card";
column.appendChild(card);

const renderCharacter = ({ id, characterName, element, weapon, level }) => {
    const column = document.createElement("div");
    column.className = "col";

    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    const nameText = document.createElement("p");
    nameText.className = "card-text";
    nameText.innerText = `Name: ${characterName}`;
    cardBody.appendChild(nameText);

    const elementText = document.createElement("p");
    elementText.className = "card-text";
    elementText.innerText = `Element: ${element}`;
    cardBody.appendChild(elementText);

    const weaponText = document.createElement("p");
    weaponText.className = "card-text";
    weaponText.innerText = `Weapon: ${weapon}`;
    cardBody.appendChild(weaponText);

    const levelText = document.createElement("p");
    levelText.className = "card-text";
    levelText.innerText = `Level: ${level}`;
    cardBody.appendChild(levelText);

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

getCharacters();

document.getElementById("createForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const data = {
        characterName: this.characterName.value,
        element: this.element.value,
        weapon: this.weapon.value,
        level: this.level.value
    }

    axios.post("/characters/create", data)
        .then(res => {
            getCharacters();
            this.reset();
            this.make.focus();
        }).catch(err => console.log(err));

    console.log(this);
});

const deleteCharacter = async (id) => {
    const res = await axios.delete(`/characters/remove/${id}`);
    getCharacters();
};

form.addEventListener('submit', (e) => {
    e.preventDefault();

    checkInputs();
});
