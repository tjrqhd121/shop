var idCheck = 0;
function checkId() {
    $.ajax({
        type : "POST",
        cache : false,
        data : "id="+$('#id').val(),
        url : "/checkid",
        success : function(data) {
                if (data.result == '0') {
                $("#id").css("background-color", "#ffffff");
                idCheck = 1;
                if(idCheck==1) {
                    $("#signupbtn").prop("disabled", false);
                    $("#signupbtn").css("background-color", "#ffe599");
                    signupCheck();
                } 
            } else if (data.result == '1') {
                $("#signupbtn").prop("disabled", true);
                $("#signupbtn").css("background-color", "#aaaaaa");
                $("#id").css("background-color", "#FFCECE");
                idCheck = 0;
            } 
        }
    });        
}
function signupCheck() {
    var id = $("#id").val();
    if(id=="") {
        $("#signupbtn").prop("disabled", true);
        $("#signupbtn").css("background-color", "#aaaaaa");
    }else{
        
    }
}
function DosignUp() {
    var id = $("#id").val();
    var pw = $("#pw").val();
    var checkpw = $("#checkpw").val();
    var name = $("#name").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var postadd = $("#postadd").val();
    var roadadd = $("#roadadd").val();
    var jibadd = $("#jibadd").val();
    var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;

if(id.length == 0 ){
    alert("아이디를 입력해 주세요"); 
    $("#id").focus();
    return false;
}

for (i = 0;i < id.length;i++) {
    if(!(id.charAt(i) >= '0' && id.charAt(i) <= '9') && !(id.charAt(i) >= 'a' && id.charAt(i) <= 'z')&&!(id.charAt(i) >= 'A' && id.charAt(i) <= 'Z'))
    {
        alert("아이디는 대소문자, 숫자만 입력가능합니다.");
        $("#id").focus();
        return false;   
    }
}

    if((id.length < 4) || (id.length > 12) ){
    alert("아이디를 4~12자까지 입력해주세요"); 
    $("#id").focus();
    return false;
}
    
if(pw.length == 0){
    alert("비밀번호를 입력해 주세요"); 
    $("#pw").focus();
    return false;
}

if((pw.length < 4) || (pw.length > 12)){
    alert("비밀번호는 4~12자리까지 입력해주세요"); 
    $("#pw").focus();
    return false;
}	

if(pw != checkpw){
    alert("비밀번호가 서로 다릅니다. 비밀번호를 확인해 주세요."); 
    $("#checkpw").focus();
    return false; 
}

if(name.length == 0){
    alert("이름을 입력해주세요");
    $("#name").focus();
    return false;
}

if(email.length == 0){
    alert("이메일을 입력해주세요");
    $("#email").focus();
    return false;
}

if(re2.test(email) == false){
    alert("잘못된 이메일 형식입니다");
    $("#email").focus();
    return false;
}
if(phone.length == 0){
    alert("폰번호를 입력해주세요");
    $("#phone").focus();
    return false;
}
if(regPhone.test(phone) == false){
    alert("휴대폰 번호를 올바르게 입력해 주세요");
    $("#phone").focus();
    return false;
}	
if(postadd.length == 0){
    alert("주소를 적어주세요");
    $("#postadd").focus();
    return false;
}		
else{
    alert("회원가입에 성공하셧습니다.");
}
$("#frm").submit();
}