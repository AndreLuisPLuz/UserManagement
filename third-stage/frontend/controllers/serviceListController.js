import data from  "../views/resources/temp/services.json";
import departmentsData from "../views/resources/temp/departments.json";
import usersData from "../views/resources/temp/users.json";

const renderServices = () => {
    const cardDeck = document.getElementById("service-card-deck");

    data.data.forEach(service => {
        const manager = usersData.data.find((m) => m.id = service.managerId);
        const department = departmentsData.data.find((d) => d.id = service.departmentId);

        cardDeck.insertAdjacentHTML(
            "beforeend",
            `<div class=" card card-dash border-light">
                <div class="card-header">
                <h4 class="m-0 pt-1 pb-1">${service.name}</h4>
                </div>
                <div class="card-body">
                <div class="card-content-wrapper">
                    <span>${service.description}</span>
                    <span class="poppins-medium">${manager.username}</span>
                    <span class="poppins-medium">${department.name}</span>
                </div>
                </div>
                <div class="card-footer">
                <a class="btn btn-primary" href="#" role="button">Editar</a>
                <a class="btn btn-danger" href="#" role="button">Excluir</a>
                </div>
            </div>`
        );
    });
}

renderServices();