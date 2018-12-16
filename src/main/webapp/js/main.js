var reportCounter = 1;
var inputElements = new Array();
function addInputFieldsForReport() {
    var newDiv = document.createElement("div");
    newDiv.setAttribute("id", "reportdata" + reportCounter);

    reportCounter++;
    newDiv.setAttribute("class", "row  report-input");
    var textInputDiv = document.createElement("div");
    textInputDiv.setAttribute("class","col-md-6 col-lg-6 col-sm-12");
    var reportInput = document.createElement("input");
    reportInput.setAttribute("name", "report-name");
    reportInput.setAttribute("type", "text");
    textInputDiv.appendChild(reportInput)
    var timeInputDiv = document.createElement("div");
    timeInputDiv.setAttribute("class", "col-md-6 col-lg-6 col-sm-12");
    var timeInput = document.createElement("input");
    timeInput.setAttribute("name", "report-time");
    timeInput.setAttribute("type", "time");
    timeInputDiv.appendChild(timeInput);
    newDiv.appendChild(textInputDiv);
    newDiv.appendChild(timeInputDiv);
    inputElements.push(newDiv);
    document.getElementById("report-inputs").appendChild(newDiv)

}

function removeLastReportInputsDiv() {
    if (reportCounter>0) {
        document.getElementById("report-inputs").removeChild(inputElements.pop())
        reportCounter--;


    }
}