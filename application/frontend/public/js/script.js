import { CalculService } from "./calcul-service.js";
var calculDisplay = ""
var display;
var currentCalculationId = null;
function run(){
    var calcul = ""
    var lastKey = 0
    const btn0 = document.getElementById("btn0")
    btn0.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "0"
        calculDisplay += "0"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn1 = document.getElementById("btn1")
    btn1.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "1"
        calculDisplay += "1"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn2 = document.getElementById("btn2")
    btn2.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "2"
        calculDisplay += "2"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn3 = document.getElementById("btn3")
    btn3.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "3"
        calculDisplay += "3"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn4 = document.getElementById("btn4")
    btn4.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "4"
        calculDisplay += "4"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn5 = document.getElementById("btn5")
    btn5.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "5"
        calculDisplay += "5"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn6 = document.getElementById("btn6")
    btn6.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "6"
        calculDisplay += "6"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn7 = document.getElementById("btn7")
    btn7.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "7"
        calculDisplay += "7"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn8 = document.getElementById("btn8")
    btn8.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "8"
        calculDisplay += "8"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const btn9 = document.getElementById("btn9")
    btn9.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "9"
        calculDisplay += "9"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const point = document.getElementById("point")
    point.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "."
        calculDisplay += "."
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const add = document.getElementById("add")
    add.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "+"
        calculDisplay += "+"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const min = document.getElementById("min")
    min.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "-"
        calculDisplay += "-"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const time = document.getElementById("time")
    time.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "*"
        calculDisplay += "*"
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const div = document.getElementById("div")
    div.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "div"
        calculDisplay += "/"
        lastKey = 1
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const parl = document.getElementById("parl")
    parl.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "parl"
        calculDisplay += "("
        lastKey = 2
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const pard = document.getElementById("pard")
    pard.addEventListener("click", ()=>{
        if (lastKey === 3){
            calculDisplay = ""
        }
        calcul += "pard"
        calculDisplay += ")"
        lastKey = 2
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const reset = document.getElementById("reset")
    reset.addEventListener("click", ()=>{
        calcul = ""
        calculDisplay = ""
        lastKey = 0
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const del = document.getElementById("del")
    del.addEventListener("click", ()=>{
        if (lastKey === 0){
            calcul = calcul.slice(0, -1)
            calculDisplay = calculDisplay.slice(0, -1)
        }
        else if (lastKey === 1){
            calculDisplay = calculDisplay.slice(0, -1)
        }
        else if (lastKey === 2){
            calcul = calcul.slice(0, -4)
            calculDisplay = calculDisplay.slice(0, -1)
        }
        //displayResult()
        display = document.getElementById("display")
        display.innerHTML = calculDisplay
    })

    const equal = document.getElementById("equal")
    equal.addEventListener("click", ()=>{
        lastKey = 3
        CalculService.sendCalcul(calcul).then((id) => {
            currentCalculationId = id;
            document.getElementById("current-calculation-id").textContent = id;
            
            CalculService.getResult(id).then((result) =>{
                calculDisplay = result
                calcul = ""
                display = document.getElementById("display")
                display.innerHTML = calculDisplay
            })
        })
    })

    const getResultButton = document.getElementById("get-result")
    getResultButton.addEventListener("click", () => {
        const idInput = document.getElementById("id-input");
        const searchId = idInput.value.trim();
        
        if (searchId) {
            CalculService.getResult(searchId).then((result) => {
                if (result !== null) {
                    calculDisplay = result;
                    display = document.getElementById("display");
                    display.innerHTML = calculDisplay;
                    lastKey = 3;
                } else {
                    display = document.getElementById("display");
                    display.innerHTML = "ID non trouvé";
                }
            });
        }
    });
}

function displayResult(){
    display = document.getElementById("display")
    display.innerHTML = calculDisplay
}

window.addEventListener("DOMContentLoaded", run)