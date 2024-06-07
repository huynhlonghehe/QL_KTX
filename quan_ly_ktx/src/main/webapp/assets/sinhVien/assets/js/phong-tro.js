

function handleTable() {
  let table = new Tabulator("#roomate-table", {
    height: "100%", // set height of table (in CSS or here), this enables the Virtual DOM and improves render speed dramatically (can be any valid css height value)
    data: tabledata, //assign data to table
    layout: "fitDataFill", //fit columns to width of table (optional)
    columns: [
      //Define Table Columns
      {
        title: "STT",
        field: "stt",
        width: 70,
        hozAlign: "center",
        headerSort: false,
      },
      { title: "Họ và tên", field: "name", headerSort: false, width: 200 },
      { title: "MSSV", field: "studentid", headerSort: false },
      { title: "Ngày sinh", field: "dob", headerSort: false },
      {
        title: "SĐT",
        field: "contact",
        headerSort: false,
      },
      { title: "Địa chỉ", field: "address", headerSort: false },
    ],
  });
}
