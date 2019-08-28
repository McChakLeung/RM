function checkPassword(str) {
    var password = str;
    var reg =  /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)\S{8,}$/;
    if(!reg.test(password)){
        return false;
    }
}