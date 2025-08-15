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

async function loadUsers() {
    const res = await fetch(`${API_URL}/user/list_user`, { headers });

    if (!res.ok) {
        alert("Erro ao carregar usuários.");
        return;
    }

    const users = await res.json();
    const list = document.getElementById("userList");
    list.innerHTML = "";

    users.forEach(user => {
        const li = document.createElement("li");
        li.textContent = `${user.name} (${user.email}) `;

        const btnEdit = document.createElement("button");
        btnEdit.textContent = "Editar";
        btnEdit.addEventListener("click", () => loadEditUser(user.id, user.name, user.email));

        const btnDelete = document.createElement("button");
        btnDelete.textContent = "Deletar";
        btnDelete.addEventListener("click", () => deleteUser(user.id));

        li.appendChild(btnEdit);
        li.appendChild(document.createTextNode(" "));
        li.appendChild(btnDelete);

        list.appendChild(li);
    });
}

async function deleteUser(id) {
    console.log("Passou aqui");
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
}

function loadEditUser(id, name, email) {
    document.getElementById("editId").value = id;
    document.getElementById("editName").value = name;
    document.getElementById("editEmail").value = email;
}

document.getElementById("editForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("editId").value;
    const name = document.getElementById("editName").value;
    const email = document.getElementById("editEmail").value;

    const res = await fetch(`${API_URL}/user/edit_user/${id}`, {
        method: "PUT",
        headers,
        body: JSON.stringify({ name, email })
    });

    if (!res.ok) {
        alert("Erro ao editar usuário.");
        return;
    }

    loadUsers();
});

document.getElementById("searchForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("searchEmail").value;

    const res = await fetch(`${API_URL}/user/list_user/${id}`, { headers });

    if (res.ok) {
        const user = await res.json();
        alert(`User found: ${user.name} (${user.email})`);
    } else {
        alert("User not found.");
    }
});

loadUsers();