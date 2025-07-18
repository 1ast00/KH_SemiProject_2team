document.querySelector("#findPwBtn").onclick = function(e) {
    e.preventDefault();

    const id = document.querySelector("#member_id").value.trim();
    const email = document.querySelector("#member_email").value.trim();

	fetch("/MissingProjact/memberFindPwAction.do", {   
	    method: "POST",
	    headers: { "Content-Type": "application/x-www-form-urlencoded" },
	    body: "member_id=" + encodeURIComponent(id) + "&member_email=" + encodeURIComponent(email)
	})
	.then(res => res.json())
	.then(data => {
	    const result = document.querySelector("#resultMsg");
	    if (data.result === "success") {
	        result.style.color = "green";
	        result.innerText = "회원 확인 완료. 이메일을 확인하세요.";
	    } else {
	        result.style.color = "red";
	        result.innerText = "회원정보가 일치하지 않습니다.";
	    }
	})
	.catch(error => {
	    console.error("에러 발생:", error);
	    document.querySelector("#resultMsg").innerText = "서버 오류가 발생했습니다.";
		
	});
};