const API_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

if (!token) {
    alert("Do login first!");
    window.location.href = "../index.html";
}

const headers = {
    "Authorization": "Bearer " + token,
    "Content-Type": "application/json"
};

async function loadUsers(filter = {}) {
    let url = `${API_URL}/user/list_user`;
    // If you have filter endpoints, adjust here
    if (filter.nameCpf || filter.status) {
        url += `?`;
        if (filter.nameCpf) url += `nameCpf=${encodeURIComponent(filter.nameCpf)}&`;
        if (filter.status && filter.status !== "Selecione") url += `status=${encodeURIComponent(filter.status)}`;
    }

    const res = await fetch(url, { headers });
    if (!res.ok) {
        alert("Erro ao carregar usuários.");
        return;
    }
    const users = await res.json();
    renderTable(users);
}

function renderTable(users) {
    const tbody = document.getElementById("userTable");
    tbody.innerHTML = "";
    users.forEach(user => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td class="${user.status === "Ativo" ? "status-ativo" : ""}">${user.status}</td>
            <td class="action-btns">
                <button title="Editar" class="btn-edit" onclick="editUser('${user.id}', '${user.name}', '${user.cpf}', '${user.status}')">&#9998;</button>
                <button title="Excluir" class="btn-delete" onclick="deleteUser('${user.id}')">&#128465;</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

window.deleteUser = async function(id) {
    if (!confirm("Do you really want to delete this user?")) return;
    const res = await fetch(`${API_URL}/user/delete_user/${id}`, {
        method: "DELETE",
        headers
    });
    if (!res.ok) {
        alert("Erro ao deletar usuário.");
        return;
    }
    loadUsers();
};

window.editUser = function(id, name, cpf, status) {
    // Implement your edit modal or form logic here
    alert(`Edit user: ${name} (${cpf})`);
    // Example: openEditModal(id, name, cpf, status);
};

//document.getElementById("filterForm").addEventListener("submit", function(e) {
//    e.preventDefault();
//    const nameCpf = document.getElementById("filterNameCpf").value;
//    const status = document.getElementById("filterStatus").value;
//    loadUsers({ nameCpf, status });
//});

loadUsers();