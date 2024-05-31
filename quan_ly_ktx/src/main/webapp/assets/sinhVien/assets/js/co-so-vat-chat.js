
function handleTable(data){
  var studentsTable = new Tabulator("#facility-table", {
    height: 400, // set height of table (in CSS or here), this enables the Virtual DOM and improves render speed dramatically (can be any valid css height value)
    data: data, //assign data to table
    virtualDom: true,
    layout: "fitColumns", //fit columns to width of table (optional)
    columns: [
      //Define Table Columns
      {
        title: "STT",
        field: "ID",
        width: 80,
        hozAlign: "center",
        sorter: "number",
      },
      { title: "Tên CSVC", field: "facility_name", sorter: "string" },
      { title: "Số lượng hiện tại", field: "quantity", sorter: "number" },
      { title: "Số lượng ban đầu", field: "default", sorter: "number" },
    ],
  });
  
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
      studentsTable.setFilter(filter, typeVal, valueEl.value);
    }
  }
  
  //Update filters on value change
  document
    .getElementById("filter-field")
    .addEventListener("change", updateFilter);
  document.getElementById("filter-type").addEventListener("change", updateFilter);
  document.getElementById("filter-value").addEventListener("keyup", updateFilter);
}





