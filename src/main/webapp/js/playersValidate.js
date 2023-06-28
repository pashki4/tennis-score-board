var frm = document.querySelector('form.playerNames');
var inputs = frm.querySelectorAll('input[type=text]');

frm.addEventListener('submit', function(e) {
    e.preventDefault();
    var classArr = [];
    
    for(var i = 0; i < inputs.length; i++) {
        if(classArr.indexOf(inputs[i].value) != -1) {
            inputs[i].style.backgroundColor = "red";
            return false;
        }
        else
            classArr.push(inputs[i].value);
    }
    frm.submit();
});

for(var j = 0; j < inputs.length; j++) {
    inputs[j].addEventListener('focus', function() {
        this.style.backgroundColor = "white";
    });
}