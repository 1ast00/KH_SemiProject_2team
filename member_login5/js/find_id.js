document.querySelector("#findIdBtn").onclick = function(e) {
    e.preventDefault();

    const name = document.querySelector("#member_name").value.trim();
    const email = document.querySelector("#member_email").value.trim();

    fetch("/MissingProject/memberFindIdAction.do", {  
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "member_name=" + encodeURIComponent(name) + "&member_email=" + encodeURIComponent(email)
    })
    .then(res => res.json())
    .then(data => {
        const result = document.querySelector("#resultMsg");
        if (data.result === "success") {
            result.style.color = "green";
            result.innerText = "찾은 아이디 : " + data.member_id;
        } else {
            result.style.color = "red";
            result.innerText = "일치하는 회원정보가 없습니다.";
        }
    });
};
