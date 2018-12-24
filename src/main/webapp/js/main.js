var reportCounter = 1;
var inputElements = new Array();
function addInputFieldsForReport() {
    var newDiv = document.createElement("div");
    newDiv.setAttribute("id", "reportdata" + reportCounter);

    reportCounter++;
    newDiv.setAttribute("class", "row  report-input");
    var textInputDiv = document.createElement("div");
    textInputDiv.setAttribute("class","col-md-4 col-lg-4 col-sm-12");
    var reportInput = document.createElement("input");
    reportInput.setAttribute("name", "report-name");
    reportInput.setAttribute("type", "text");
    reportInput.setAttribute("required","");
    textInputDiv.appendChild(reportInput)

    var timeInputDiv = document.createElement("div");
    timeInputDiv.setAttribute("class", "col-md-4 col-lg-4 col-sm-12");
    var timeInput = document.createElement("input");
    timeInput.setAttribute("name", "report-date-time");
    timeInput.setAttribute("type", "datetime-local");
    timeInput.setAttribute("required","");
    timeInputDiv.appendChild(timeInput);

    var userSelectorDiv = document.createElement("div");
    userSelectorDiv.setAttribute("class", "col-md-4 col-lg-4 col-sm-12");
    var options= document.getElementById("report-speaker0").innerHTML;
    var userSelector = document.createElement("select");
    userSelector.setAttribute("class","selectpicker");
    userSelector.setAttribute("name","report-speaker")
    userSelector.innerHTML = options;
    userSelectorDiv.appendChild(userSelector);

    newDiv.appendChild(textInputDiv);
    newDiv.appendChild(timeInputDiv);
    newDiv.appendChild(userSelectorDiv);
    inputElements.push(newDiv);
    document.getElementById("first-inputs").appendChild(newDiv)

}

function removeLastReportInputsDiv() {
    if (reportCounter>0) {
        document.getElementById("first-inputs").removeChild(inputElements.pop())
        reportCounter--;


    }
}
function getScrollPosition( id, elementForSavingScrollId) {
    var el = document.getElementById(id);
    var element = document.getElementById(elementForSavingScrollId);
    element.setAttribute("value",window.scrollY.toString());
    console.log("done" +el.scrollTop.toString() );
    return window.scrollY
}
