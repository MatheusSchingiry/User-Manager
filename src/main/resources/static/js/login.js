document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    
    const res = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password})
    });

    if(res.ok){
        const data = await res.json();
        localStorage.setItem("token", data.token);
        window.location.href = "pages/manager.html";
    }
    else{
        alert("Login Failure");
    }

});