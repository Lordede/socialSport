/**
 * @author Hubertus Seitz
 **/

function checkForCookie (){

    let cookie = getCookie("JSESSIONID");

    if(cookie ===""){
        alert("Bitte aktivieren Sie Cookies in Ihrem Browser, da Sie sonst keinen Zugang haben!");
        window.location.replace("login.html");
        
    }
    else{
        console.log("Session-Cookies wurden gefunden:" + cookie);
    }
}

// https://www.w3schools.com/js/js_cookies.asp
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }
// --------------------------------------------