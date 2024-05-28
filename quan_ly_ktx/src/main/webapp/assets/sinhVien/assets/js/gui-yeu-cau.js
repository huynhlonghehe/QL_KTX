

function checkInput() {
  var title = $("#title_yeu_cau").val();
  var content = $("#content_yeu_cau").val();
  if (title != "" && content != "") {
    $("#submit-yeu-cau").removeClass("disabled");
  } else {
    $("#submit-yeu-cau").addClass("disabled");
  }
}
setInterval(checkInput, 300);
