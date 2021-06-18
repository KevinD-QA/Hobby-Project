'use strict';

const form = document.getElementById("output");

const getTeams = async () => {
    console.log("GTTING TEAMS");
    const tableBody = document.querySelector("tbody");
    tableBody.innerHTML = "";
    const teams = await axios.get("/team/");
    console.log(teams.data);
    teams.data.forEach(team => {
        renderTeam(team);
    });
}
const renderTeam = team => {
    const tableBody = document.querySelector("tbody");

    const newRow = document.createElement("tr");

    newRow.appendChild(createTableCell(team.id));
    newRow.appendChild(createTableCell(team.name));
    newRow.appendChild(createOpenLink(team.id));

    newRow.appendChild(updateButton(team));
    newRow.appendChild(deleteButton(team.id));

    tableBody.appendChild(newRow);
}

const createTableCell = (data) => {
    const cell = document.createElement("td");
    cell.innerText = data;
    cell.className = "align-middle";
    return cell;
}

const createOpenLink = (teamID) => {
    const cell = document.createElement("td");
    const openLink = document.createElement("a");
    openLink.innerText = "View";
    openLink.className = "btn btn-success";
    openLink.href = `hero.html?id=${teamID}`

    cell.appendChild(openLink)
    return cell;
}

getTeams();

document.getElementById("teamForm").addEventListener("submit", function (event) {
    event.preventDefault();
    let createModal = document.getElementById('AddTeamModal');
    let modal = bootstrap.Modal.getInstance(createModal);
    modal.hide();

    const data = {
        name: this.teamName.value,
    }

    axios.post("/team/create", data)
        .then(res => {
            getTeams();
            this.reset();
        }).catch(err => console.log(err));

    console.log(this);
});

const deleteButton = (teamID) => {
    const cell = document.createElement("td");
    const deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.className = "btn btn-danger";
    deleteButton.id = "deleteBtn";
    deleteButton.setAttribute("type", "submit");
    deleteButton.addEventListener("click", function (event) {
        deleteTeam(teamID);
    });

    cell.appendChild(deleteButton);
    return cell;
}

const deleteTeam = async (id) => {
    const res = await axios.delete(`/team/remove/${id}`);
    getTeams(); //Can't create aysnc functions inside deleteButton so created separate method
};

const updateButton = (team) => {
    const updateCell = document.createElement("td");
    const updateTeamBtn = document.createElement("button");
    updateTeamBtn.innerText = "Update";
    updateTeamBtn.className = "btn btn-warning";
    updateTeamBtn.setAttribute("type", "button");
    updateTeamBtn.setAttribute("data-bs-toggle", "modal");
    updateTeamBtn.setAttribute("data-bs-target", "#UpdateTeamModal")
    updateTeamBtn.addEventListener("click", function (event) {
        document.getElementById("updateTeamName").value = team.name;
        document.getElementById("updateBtn").setAttribute("teamID", team.id);
    });
    updateCell.appendChild(updateTeamBtn);
    return updateCell;
}
document.getElementById("updateTeamForm").addEventListener("submit", function (event) {
    event.preventDefault();
    let updateModal = document.getElementById('UpdateTeamModal');
    let modal = bootstrap.Modal.getInstance(updateModal);
    modal.hide();

    const teamID = document.getElementById("updateBtn").getAttribute("teamID");
    const data = {
        name: this.updateTeamName.value,
    }

    axios.put(`/team/update/${teamID}`, data)
        .then(res => {
            getTeams();
            this.reset();
        }).catch(err => console.log(err));

    console.log(this);
});