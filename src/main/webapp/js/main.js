var reportCounter = 1;
var inputElements = [];



function addInputFieldsForReport() {
    var newDiv = document.createElement("div");
    newDiv.setAttribute("id", "reportdata" + reportCounter);

    newDiv.setAttribute("class", "row  report-input");
    var textInputDiv = document.createElement("div");
    textInputDiv.setAttribute("class", "col-md-4 col-lg-4 col-sm-12");
    var rowEn = createDivRow();
    var labelEn = document.createElement("label");
    labelEn.setAttribute("for", "report-name-en" + reportCounter);
    labelEn.innerHTML = "EN";
    rowEn.appendChild(labelEn);
    var reportInputEn = createInput("en");
   rowEn.appendChild(reportInputEn);

    var rowUa = createDivRow();
    var labelUa = document.createElement("label");
    labelUa.setAttribute("for", "report-name-ua" + reportCounter);
    labelUa.innerHTML = "UA";
    rowUa.appendChild(labelUa);

    var reportInputUa = createInput("ua");
    rowUa.appendChild(reportInputUa);
    textInputDiv.appendChild(rowEn);
    textInputDiv.appendChild(rowUa);
    var timeInputDiv = document.createElement("div");
    timeInputDiv.setAttribute("class", "col-md-4 col-lg-4 col-sm-12");
    var timeInput = document.createElement("input");
    timeInput.setAttribute("name", "report-date-time");
    timeInput.setAttribute("type", "datetime-local");
    timeInput.setAttribute("required", "");
    timeInputDiv.appendChild(timeInput);

    var userSelectorDiv = document.createElement("div");
    userSelectorDiv.setAttribute("class", "col-md-4 col-lg-4 col-sm-12");
    var options = document.getElementById("report-speaker0").innerHTML;
    var userSelector = document.createElement("select");
    userSelector.setAttribute("class", "selectpicker");
    userSelector.setAttribute("name", "report-speaker");
    userSelector.innerHTML = options;
    userSelectorDiv.appendChild(userSelector);

    newDiv.appendChild(textInputDiv);
    newDiv.appendChild(timeInputDiv);
    newDiv.appendChild(userSelectorDiv);
    inputElements.push(newDiv);
    document.getElementById("first-inputs").appendChild(newDiv);
    reportCounter++;


}
function createDivRow() {
    var newDiv = document.createElement("div");

    newDiv.setAttribute("class", "row");
    return newDiv
}
function createInput(lang) {
    var reportInput = document.createElement("input");
    reportInput.setAttribute("name", "report-name-"+lang);
    reportInput.setAttribute("id", "report-name-"+lang + reportCounter);
    reportInput.setAttribute("type", "text");
    reportInput.setAttribute("required", "");
    return reportInput;
}
function removeLastReportInputsDiv() {
    if (reportCounter > 0) {
        document.getElementById("first-inputs").removeChild(inputElements.pop());
        reportCounter--;


    }
}

function getScrollPosition(elementForSavingScrollId) {
    var element = document.getElementById(elementForSavingScrollId);
    element.setAttribute("value", window.scrollY.toString());
    return window.scrollY;
}

