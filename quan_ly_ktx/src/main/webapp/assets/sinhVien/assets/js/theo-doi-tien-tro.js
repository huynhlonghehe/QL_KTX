
let table;
function handleTable(data) {
  table = new Tabulator("#table", {
    height: 205,
    data: data,
    layout: "fitColumns",
    columns: [
      { title: "Tháng", field: "month", sorter: "number" },
      { title: "Năm", field: "year", sorter: "number" },
      { title: "Số tiền", field: "money", sorter: "number" },
      { title: "Trạng thái", field: "status", sorter: "string" },
    ],
  });
}
var fieldEl = document.getElementById("filter-field");
var typeEl = document.getElementById("filter-type");
var valueEl = document.getElementById("filter-value");

//Custom filter example
function customFilter(data) {
  return data.car && data.rating < 3;
}

//Trigger setFilter function with correct parameters
function updateFilter() {
  var filterVal = fieldEl.options[fieldEl.selectedIndex].value;
  var typeVal = typeEl.options[typeEl.selectedIndex].value;

  var filter = filterVal == "function" ? customFilter : filterVal;

  if (filterVal == "function") {
    typeEl.disabled = true;
    valueEl.disabled = true;
  } else {
    typeEl.disabled = false;
    valueEl.disabled = false;
  }

  if (filterVal) {
    table.setFilter(filter, typeVal, valueEl.value);
  }
}

document
  .getElementById("filter-field")
  .addEventListener("change", updateFilter);
document.getElementById("filter-type").addEventListener("change", updateFilter);
document.getElementById("filter-value").addEventListener("keyup", updateFilter);

